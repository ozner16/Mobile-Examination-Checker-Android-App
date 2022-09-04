package com.mobileexaminationchecker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class prof_home extends AppCompatActivity {

    Button logout_btn;
    Button scanner_btn;
    Button profile_btn;
    Button prof_scores_btn;
    Button create_exam_btn;
    FirebaseAuth mAuth;
    TextView textUsername;

    //firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageReference;

    AlertDialog dialog;

    //progress bar
    ProgressBar progressBar;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_home);

        profile_btn = findViewById(R.id.profilebutton);
        logout_btn = findViewById(R.id.logout_btnProf);
        create_exam_btn = findViewById(R.id.create_exam_btn);
        prof_scores_btn = findViewById(R.id.prof_scores_btn);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        textUsername = findViewById(R.id.textUsername);
        scanner_btn = findViewById(R.id.scanner_btn);

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        String userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        //alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Number of Items: ");
        //Inflate the prof_exam_dialog view
        @SuppressLint("InflateParams") View view10 = getLayoutInflater().inflate(R.layout.prof_exam_dialog,null);
        Button twenty_items = view10.findViewById(R.id.twenty_items);
        Button fourty_items= view10.findViewById(R.id.fourty_items);

        twenty_items.setOnClickListener(view -> startActivity(new Intent(prof_home.this, prof_create_exam_two.class)));

        fourty_items.setOnClickListener(view -> startActivity(new Intent(prof_home.this, prof_create_exam.class)));

        //set this view to dialog
        builder.setView(view10);
        //create dialog now
        dialog = builder.create();


        //set text to textviews
        DocumentReference documentReference = fStore.collection("user_professors").document(userId);
        documentReference.addSnapshotListener(this,(documentSnapshot,e)->{

            String first_name = Objects.requireNonNull(documentSnapshot).getString("first_name");


            if(documentSnapshot.exists()){
                textUsername.setText(first_name);
            }
            else {

                Log.d("tag","Document do not exist");
            }
        });

        logout_btn.setOnClickListener(view -> {
            mAuth.signOut();
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(prof_home.this,MainActivity.class));

        });
        profile_btn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(prof_home.this,prof_profile.class));

        });

        prof_scores_btn.setOnClickListener(view -> {
            AlertDialog dialog1;

            //alert dialog
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("Exam records for:");
            //Inflate the prof_exam_dialog view
            @SuppressLint("InflateParams") View view11 = getLayoutInflater().inflate(R.layout.prof_exam_dialog,null);
            Button twenty_items1 = view11.findViewById(R.id.twenty_items);
            Button fourty_items1= view11.findViewById(R.id.fourty_items);

            twenty_items1.setBackgroundColor(getColor(R.color.colorPrimary));
            fourty_items1.setBackgroundColor(getColor(R.color.colorPrimary));


            twenty_items1.setOnClickListener(view1 -> startActivity(new Intent(getApplicationContext(), prof_exam_records_20.class)));

            fourty_items1.setOnClickListener(view2 -> startActivity(new Intent(getApplicationContext(), prof_exam_records_40.class)));

            //set this view to dialog
            builder1.setView(view11);
            //create dialog now
            dialog1 = builder1.create();

            dialog1.show();

        });


        create_exam_btn.setOnClickListener(view -> dialog.show());

        scanner_btn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(prof_home.this,scanner.class));

        });

    }

    @Override
    public void onStart() {
        super.onStart();
        progressBar.setVisibility(View.GONE);

    }
}