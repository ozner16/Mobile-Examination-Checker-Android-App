package com.mobileexaminationchecker;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class prof_edit_profile extends AppCompatActivity {
    
    TextView prof_name;
    TextView email_prof_txt;
    TextView name_prof_txt;
    TextView gender_prof_txt;
    TextView user_type;
    AutoCompleteTextView prof_reg_subjCode;
    AutoCompleteTextView prof_reg_dept;
    Button back_btn;
    Button save_btn;
    TextInputLayout prof_subjCode_dropdown;
    TextInputLayout dept_profile_drowdown;
    ImageView imageview_profile;



    // Uri indicates, where the image will be picked from
    private Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    //firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageReference;

    //subject code dropdown
    String[] subjCode_items = {"COSC 110", "GNED 07","GNED 10"};
    ArrayAdapter<String> adapter_subjCode;

    //department dropdown
    String[] dept_items = {"Computer Studies", "Hospitality Management"};
    ArrayAdapter<String> adapter_dept;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_edit_profile);

        prof_name = findViewById(R.id.name);
        email_prof_txt = findViewById(R.id.email_profile_text);
        name_prof_txt = findViewById(R.id.name_profile_text);
        gender_prof_txt = findViewById(R.id.gender_profile_text);
        prof_reg_subjCode = findViewById(R.id.prof_subjCode_text);
        prof_reg_dept = findViewById(R.id.dept_profile_text);
        user_type = findViewById(R.id.user_type);
        save_btn = findViewById(R.id.save_btn_prof);
        prof_subjCode_dropdown = findViewById(R.id.prof_subjCode_dropdown);
        dept_profile_drowdown = findViewById(R.id.dept_profile_drowdown);
        imageview_profile = findViewById(R.id.imageview_profile);


        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        String userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        //display profile picture
        StorageReference profileRef = storageReference;
        profileRef.child("images/"+fAuth.getCurrentUser().getUid()).getDownloadUrl()
                .addOnSuccessListener(uri -> Picasso.get()
                        .load(uri).centerCrop()
                        .fit().into(imageview_profile));


        // handle the Choose Image button to trigger
        // the image chooser function
        imageview_profile.setOnClickListener(v ->  SelectImage());

        //set text to textviews
        DocumentReference documentReference = fStore.collection("user_professors").document(userId);
        documentReference.addSnapshotListener(this,(documentSnapshot,e)->{

            String fname = Objects.requireNonNull(documentSnapshot).getString("first_name");
            String Lname = documentSnapshot.getString("last_name");
            String subject_code = documentSnapshot.getString("subject_code");
            String department = documentSnapshot.getString("department");

            if(documentSnapshot.exists()){

                prof_name.setText(fname + " " + Lname);
                user_type.setText(documentSnapshot.getString(("user_type")));
                email_prof_txt.setText(documentSnapshot.getString(("email")));
                name_prof_txt.setText(fname + " " + Lname);
                gender_prof_txt.setText(documentSnapshot.getString(("gender")));
                prof_reg_subjCode.setText(subject_code);
                prof_reg_dept.setText(department);
                //subjCode dropdown
                adapter_subjCode = new ArrayAdapter<>(this,R.layout.list_item,subjCode_items);
                prof_reg_subjCode.setAdapter(adapter_subjCode);
                //department dropdown
                adapter_dept = new ArrayAdapter<>(this,R.layout.list_item,dept_items);
                prof_reg_dept.setAdapter(adapter_dept);

            }
            else {

                Log.d("tag","Document do not exist");
            }
        });


        //back button
        back_btn = findViewById(R.id.back_btn_prof);
        back_btn.setOnClickListener(view -> onBackPressed());

        //save button
       save_btn.setOnClickListener(view1 -> {

           if(prof_reg_subjCode.getText().toString().isEmpty() || prof_reg_dept.getText().toString().isEmpty()){

               Toast.makeText(this, "One or more fields are empty!", Toast.LENGTH_SHORT).show();
           }
           else {
               prof_subjCode_dropdown.setEnabled(false);
               dept_profile_drowdown.setEnabled(false);
               DocumentReference docRef = fStore.collection("user_professors").document(userId);
               Map<String,Object> edited = new HashMap<>();
               edited.put("subject_code",prof_reg_subjCode.getText().toString());
               edited.put("department",prof_reg_dept.getText().toString());
               docRef.update(edited).addOnSuccessListener(unused -> {
                   //upload image to firebase
                   uploadImage();
                   Toast.makeText(prof_edit_profile.this, "Profile Updated Succesfully!", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(prof_edit_profile.this, prof_home.class));
                   finish();
               });
           }

       });





    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                int dimension = getSquareCropDimensionForBitmap(bitmap);
                bitmap = ThumbnailUtils.extractThumbnail(bitmap, dimension, dimension);
                imageview_profile.setImageBitmap(bitmap);
                imageview_profile.setBackground(null);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private int getSquareCropDimensionForBitmap(Bitmap bitmap) {
        //use the smallest dimension of the image to crop to
        return Math.min(bitmap.getWidth(), bitmap.getHeight());
    }

    private void uploadImage() {

        if (filePath != null) {


            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + user.getUid());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath);
                    /*.addOnSuccessListener(
                            taskSnapshot -> Toast
                                    .makeText(prof_edit_profile.this,
                                            "Image Uploaded!!",
                                            Toast.LENGTH_SHORT)
                                    .show())

                    .addOnFailureListener(e -> Toast
                            .makeText(prof_edit_profile.this,
                                    "Failed " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show());*/
        }
    }


    private void SelectImage() {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

}