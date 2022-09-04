package com.mobileexaminationchecker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.Objects;
public class prof_profile extends AppCompatActivity {

    ImageButton back_btn;
    TextView email_prof_txt;
    TextView name_prof_txt;
    TextView gender_prof_txt;
    TextView subCode_prof_txt;
    TextView dept_prof_txt;
    TextView prof_name;
    TextView user_type;
    Button prof_editProfile_btn;
    Button prof_changePass_btn;
    ImageView imageview_profile;


    //firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    FirebaseUser user;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_profile);

        email_prof_txt = findViewById(R.id.email_profile_text);
        name_prof_txt = findViewById(R.id.name_profile_text);
       gender_prof_txt = findViewById(R.id.gender_profile_text);
        subCode_prof_txt = findViewById(R.id.SubCode_profile_text);
        dept_prof_txt = findViewById(R.id.dept_profile_text);
        prof_name = findViewById(R.id.name);
        user_type = findViewById(R.id.user_type);
        prof_editProfile_btn = findViewById(R.id.prof_editProfile_btn);
        prof_changePass_btn = findViewById(R.id.prof_changePass_btn);
        imageview_profile = findViewById(R.id.imageview_profile);

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        String userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        back_btn = findViewById(R.id.back_btn_prof);
        back_btn.setOnClickListener(view -> {

            back_btn.setBackgroundColor(getColor(R.color.Light_gray));
            startActivity(new Intent(prof_profile.this, prof_home.class));

        });

        DocumentReference documentReference = fStore.collection("user_professors").document(userId);
        documentReference.addSnapshotListener(this,(documentSnapshot,e)->{

            String fname = Objects.requireNonNull(documentSnapshot).getString("first_name");
            String Lname = documentSnapshot.getString("last_name");

                if(documentSnapshot.exists()){
                    prof_name.setText(fname + " " + Lname);
                    user_type.setText(documentSnapshot.getString(("user_type")));
                    email_prof_txt.setText(documentSnapshot.getString(("email")));
                    name_prof_txt.setText(fname + " " + Lname);
                    gender_prof_txt.setText(documentSnapshot.getString(("gender")));
                    subCode_prof_txt.setText(documentSnapshot.getString(("subject_code")));
                    dept_prof_txt.setText(documentSnapshot.getString(("department")));
                }
                else {

                    Log.d("tag","Document do not exist");
                }
        });

        prof_editProfile_btn.setOnClickListener(view -> startActivity(new Intent(prof_profile.this, prof_edit_profile.class)));
        prof_changePass_btn.setOnClickListener(view1 -> startActivity(new Intent(prof_profile.this, prof_change_pass.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();

        //display profile picture
        StorageReference profileRef = storageReference;
        profileRef.child("images/"+ Objects.requireNonNull(fAuth.getCurrentUser()).getUid()).getDownloadUrl()
                .addOnSuccessListener(uri -> Picasso.get()
                        .load(uri).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).centerCrop()
                        .fit().into(imageview_profile));//.addOnFailureListener(exception -> Toast.makeText(prof_profile.this, "Failed to load picture", Toast.LENGTH_SHORT).show());


    }
}
