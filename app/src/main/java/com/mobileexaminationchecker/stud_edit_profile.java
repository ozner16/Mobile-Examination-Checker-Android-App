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

public class stud_edit_profile extends AppCompatActivity {

    TextView prof_name;
    TextView email_prof_txt;
    TextView name_prof_txt;
    TextView gender_prof_txt;
    TextView user_type;
    AutoCompleteTextView stud_course_text;
    AutoCompleteTextView stud_year_lvl_text;
    AutoCompleteTextView stud_section_text;
    Button back_btn;
    Button save_btn;
    TextInputLayout stud_course_dropdown;
    TextInputLayout stud_year_lvl_dropdown;
    TextInputLayout section_layout;
    ImageView imageview_profile;

    //section dropdown
    String[] section_items_1st = {"A", "B","C","D","E"};
    String[] section_items_2nd = {"A", "B","C","D"};
    String[] section_items_3rd = {"A", "B","C","D","E","F"};
    String[] section_items_4th = {"A", "B","C"};
    ArrayAdapter<String> adapterItems_2;

    //year dropdown
    String[] year_items = {"1st", "2nd","3rd","4th"};
    ArrayAdapter<String> adapterItems_4;

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

    //course dropdown
    String[] course_items = {"BSCS", "BSIT"};
    ArrayAdapter<String> adapterItems;


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stud_edit_profile);

        prof_name = findViewById(R.id.name);
        email_prof_txt = findViewById(R.id.email_profile_text);
        name_prof_txt = findViewById(R.id.name_profile_text);
        gender_prof_txt = findViewById(R.id.gender_profile_text);
        stud_course_text = findViewById(R.id.stud_course_text);
        stud_year_lvl_text = findViewById(R.id.stud_year_lvl_text);
        stud_section_text = findViewById(R.id.stud_section_text);
        user_type = findViewById(R.id.user_type);
        save_btn = findViewById(R.id.save_btn_prof);
        stud_course_dropdown = findViewById(R.id.stud_course_dropdown);
        stud_year_lvl_dropdown = findViewById(R.id.stud_year_lvl_dropdown);
        imageview_profile = findViewById(R.id.imageview_profile);
        section_layout = findViewById(R.id.stud_section_dropdown);


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
        DocumentReference documentReference = fStore.collection("user_students").document(userId);
        documentReference.addSnapshotListener(this,(documentSnapshot,e)->{

            String fname = Objects.requireNonNull(documentSnapshot).getString("first_name");
            String Lname = documentSnapshot.getString("last_name");

            if(documentSnapshot.exists()){

                prof_name.setText(fname + " " + Lname);
                user_type.setText(documentSnapshot.getString(("user_type")));
                email_prof_txt.setText(documentSnapshot.getString(("email")));
                name_prof_txt.setText(fname + " " + Lname);
                gender_prof_txt.setText(documentSnapshot.getString(("gender")));
                stud_course_text.setText(documentSnapshot.getString(("course")));
                stud_year_lvl_text.setText(documentSnapshot.getString(("year_lvl")));
                stud_section_text.setText(documentSnapshot.getString(("section")));

                //course dropdown
                adapterItems = new ArrayAdapter<>(this,R.layout.list_item,course_items);
                stud_course_text.setAdapter(adapterItems);

                //year level dropdown
                adapterItems_4 = new ArrayAdapter<>(this,R.layout.list_item,year_items);
                stud_year_lvl_text.setAdapter(adapterItems_4);

                stud_year_lvl_text.setOnItemClickListener((parent, view, position, id) -> {
                    String item = parent.getItemAtPosition(position).toString();

                    stud_section_text = findViewById(R.id.stud_section_text);

                    switch (item) {
                        case "1st":
                            section_layout.setEnabled(true);
                            stud_section_text.setText("");

                            //section dropdown 1st yr
                            adapterItems_2 = new ArrayAdapter<>(this, R.layout.list_item, section_items_1st);
                            stud_section_text.setAdapter(adapterItems_2);

                            break;
                        case "2nd":
                            section_layout.setEnabled(true);
                            stud_section_text.setText("");

                            //section dropdown 2nd yr
                            adapterItems_2 = new ArrayAdapter<>(this, R.layout.list_item, section_items_2nd);
                            stud_section_text.setAdapter(adapterItems_2);
                            break;
                        case "3rd":
                            section_layout.setEnabled(true);
                            stud_section_text.setText("");

                            //section dropdown 3rd yr
                            adapterItems_2 = new ArrayAdapter<>(this, R.layout.list_item, section_items_3rd);
                            stud_section_text.setAdapter(adapterItems_2);
                            break;
                        case "4th":
                            section_layout.setEnabled(true);
                            stud_section_text.setText("");

                            //section dropdown 4th yr
                            adapterItems_2 = new ArrayAdapter<>(this, R.layout.list_item, section_items_4th);
                            stud_section_text.setAdapter(adapterItems_2);
                            break;
                        default:
                            stud_section_text.setText("");
                            section_layout.setEnabled(false);
                            break;
                    }

                });

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


            if(stud_course_text.getText().toString().isEmpty() || stud_year_lvl_text.getText().toString().isEmpty() || stud_section_text.getText().toString().isEmpty()){

                Toast.makeText(this, "One or more fields are empty!", Toast.LENGTH_SHORT).show();
            }
            else {
                stud_course_dropdown.setEnabled(false);
                stud_year_lvl_dropdown.setEnabled(false);
                section_layout.setEnabled(false);
                DocumentReference docRef = fStore.collection("user_students").document(userId);
                Map<String,Object> edited = new HashMap<>();
                edited.put("course",stud_course_text.getText().toString());
                edited.put("year_lvl",stud_year_lvl_text.getText().toString());
                edited.put("section",stud_section_text.getText().toString());
                docRef.update(edited).addOnSuccessListener(unused -> {
                    //upload image to firebase
                    uploadImage();
                    Toast.makeText(stud_edit_profile.this, "Profile Updated Succesfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(stud_edit_profile.this, stud_home.class));
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