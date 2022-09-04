package com.mobileexaminationchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class stud_home extends AppCompatActivity {

    Button logout_btn;
    Button scores_btn;
    Button profile_btn;
    Button take_exam_btn;
    Button item_analysis_btn;
    TextView text_Username1;
    ImageView cvsu_logo;

    //firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageReference;

    //progress bar
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_home);

        profile_btn = findViewById(R.id.profilebutton);
        scores_btn = findViewById(R.id.scores_btn);
        logout_btn = findViewById(R.id.logout_btnProf);
        take_exam_btn = findViewById(R.id.take_exam_btn);
        progressBar = findViewById(R.id.progressBar);
        text_Username1 = findViewById(R.id.text_Username1);
        cvsu_logo = findViewById(R.id.cvsu_logo);
        item_analysis_btn =findViewById(R.id.item_analysis_btn);

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        String userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();


        item_analysis_btn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(stud_home.this,bar_graph.class));

        });

        scores_btn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(stud_home.this,stud_scores.class));

        });

        logout_btn.setOnClickListener(view -> {
            fAuth.signOut();
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(stud_home.this,MainActivity.class));

        });
        profile_btn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(stud_home.this,stud_profile.class));

        });

        take_exam_btn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(stud_home.this,stud_take_exam.class));

        });

        cvsu_logo.setOnClickListener(view -> Toast.makeText(stud_home.this, "Cavite State University - Imus Campus", Toast.LENGTH_SHORT).show());

        DocumentReference documentReference = fStore.collection("user_students").document(userId);
        documentReference.addSnapshotListener(this,(documentSnapshot,e)->{

            assert documentSnapshot != null;
            if(documentSnapshot.exists()){
                text_Username1.setText(documentSnapshot.getString(("first_name")));
            }
            else {

                Log.d("tag","Document do not exist");
            }
        });


    }



    @Override
    public void onStart() {
        super.onStart();
        progressBar.setVisibility(View.GONE);

    }
}