package com.mobileexaminationchecker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class stud_take_exam extends AppCompatActivity {

    TextInputEditText examCode_txt;
    Button enter_examCode_btn;
    String text_code;

    //firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stud_take_exam);

        examCode_txt = findViewById(R.id.examCode_txt);
        enter_examCode_btn = findViewById(R.id.enter_examCode_btn);

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        enter_examCode_btn.setOnClickListener(view -> checkExam());


    }


    private void checkExam(){

        String exam_code= Objects.requireNonNull(examCode_txt.getText()).toString().trim();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if(!TextUtils.isEmpty(exam_code)){
            db.collection("exams_20").whereEqualTo("exam_code",exam_code)
                    .get().addOnSuccessListener(queryDocumentSnapshots -> {

                        if(!queryDocumentSnapshots.isEmpty()){


                            FirebaseFirestore db4 = FirebaseFirestore.getInstance();
                            db4.collection("stud_exam_info_20").document(user.getUid()).collection("GNED 10").whereEqualTo("exam_code",exam_code)
                                    .get().addOnSuccessListener(queryDocumentSnapshots4 -> {
                                        if(!queryDocumentSnapshots4.isEmpty()){

                                            AlertDialog dialog;
                                            //alert dialog
                                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                            //Inflate the prof_exam_dialog view
                                            @SuppressLint("InflateParams") View view10 = getLayoutInflater().inflate(R.layout.already_take_exam_dialog,null);
                                            Button okay_btn = view10.findViewById(R.id.okay_btn);

                                            //set this view to dialog
                                            builder.setView(view10);
                                            //create dialog now
                                            dialog = builder.create();
                                            dialog.show();
                                            okay_btn.setOnClickListener(view -> dialog.dismiss());

                                        }
                                        else{

                                            FirebaseFirestore db5 = FirebaseFirestore.getInstance();
                                            db5.collection("stud_exam_info_20").document(user.getUid()).collection("GNED 07").whereEqualTo("exam_code",exam_code)
                                                    .get().addOnSuccessListener(queryDocumentSnapshots5 -> {
                                                        if(!queryDocumentSnapshots5.isEmpty()){

                                                            AlertDialog dialog;
                                                            //alert dialog
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                                            //Inflate the prof_exam_dialog view
                                                            @SuppressLint("InflateParams") View view10 = getLayoutInflater().inflate(R.layout.already_take_exam_dialog,null);
                                                            Button okay_btn = view10.findViewById(R.id.okay_btn);

                                                            //set this view to dialog
                                                            builder.setView(view10);
                                                            //create dialog now
                                                            dialog = builder.create();
                                                            dialog.show();
                                                            okay_btn.setOnClickListener(view -> dialog.dismiss());

                                                        }
                                                        else{

                                                            FirebaseFirestore db6 = FirebaseFirestore.getInstance();
                                                            db6.collection("stud_exam_info_20").document(user.getUid()).collection("COSC 110").whereEqualTo("exam_code",exam_code)
                                                                    .get().addOnSuccessListener(queryDocumentSnapshots6 -> {
                                                                        if(!queryDocumentSnapshots6.isEmpty()){

                                                                            AlertDialog dialog;
                                                                            //alert dialog
                                                                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                                                            //Inflate the prof_exam_dialog view
                                                                            @SuppressLint("InflateParams") View view10 = getLayoutInflater().inflate(R.layout.already_take_exam_dialog,null);
                                                                            Button okay_btn = view10.findViewById(R.id.okay_btn);

                                                                            //set this view to dialog
                                                                            builder.setView(view10);
                                                                            //create dialog now
                                                                            dialog = builder.create();
                                                                            dialog.show();
                                                                            okay_btn.setOnClickListener(view -> dialog.dismiss());

                                                                        }
                                                                        else{

                                                                            Intent i = new Intent(stud_take_exam.this,stud_exam_form_two.class);
                                                                            text_code = examCode_txt.getText().toString();
                                                                            i.putExtra("Value",text_code);
                                                                            startActivity(i);
                                                                            finish();

                                                                        }


                                                                    });

                                                        }


                                                    });

                                        }


                                    });

                        }
                        else { //
                            String exam_code1= Objects.requireNonNull(examCode_txt.getText()).toString().trim();
                            FirebaseFirestore db1 = FirebaseFirestore.getInstance();

                            if(!TextUtils.isEmpty(exam_code1)){
                                db1.collection("exams_40").whereEqualTo("exam_code",exam_code1)
                                        .get().addOnSuccessListener(queryDocumentSnapshots2 -> {

                                            if(!queryDocumentSnapshots2.isEmpty()){

                                                FirebaseFirestore db4 = FirebaseFirestore.getInstance();
                                                db4.collection("stud_exam_info_40").document(user.getUid()).collection("GNED 10").whereEqualTo("exam_code",exam_code)
                                                        .get().addOnSuccessListener(queryDocumentSnapshots4 -> {
                                                            if(!queryDocumentSnapshots4.isEmpty()){

                                                                AlertDialog dialog;
                                                                //alert dialog
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                                                //Inflate the prof_exam_dialog view
                                                                @SuppressLint("InflateParams") View view10 = getLayoutInflater().inflate(R.layout.already_take_exam_dialog,null);
                                                                Button okay_btn = view10.findViewById(R.id.okay_btn);

                                                                //set this view to dialog
                                                                builder.setView(view10);
                                                                //create dialog now
                                                                dialog = builder.create();
                                                                dialog.show();
                                                                okay_btn.setOnClickListener(view -> dialog.dismiss());

                                                            }
                                                            else{

                                                                FirebaseFirestore db5 = FirebaseFirestore.getInstance();
                                                                db5.collection("stud_exam_info_40").document(user.getUid()).collection("GNED 07").whereEqualTo("exam_code",exam_code)
                                                                        .get().addOnSuccessListener(queryDocumentSnapshots5 -> {
                                                                            if(!queryDocumentSnapshots5.isEmpty()){

                                                                                AlertDialog dialog;
                                                                                //alert dialog
                                                                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                                                                //Inflate the prof_exam_dialog view
                                                                                @SuppressLint("InflateParams") View view10 = getLayoutInflater().inflate(R.layout.already_take_exam_dialog,null);
                                                                                Button okay_btn = view10.findViewById(R.id.okay_btn);

                                                                                //set this view to dialog
                                                                                builder.setView(view10);
                                                                                //create dialog now
                                                                                dialog = builder.create();
                                                                                dialog.show();
                                                                                okay_btn.setOnClickListener(view -> dialog.dismiss());

                                                                            }
                                                                            else{

                                                                                FirebaseFirestore db6 = FirebaseFirestore.getInstance();
                                                                                db6.collection("stud_exam_info_40").document(user.getUid()).collection("COSC 110").whereEqualTo("exam_code",exam_code)
                                                                                        .get().addOnSuccessListener(queryDocumentSnapshots6 -> {
                                                                                            if(!queryDocumentSnapshots6.isEmpty()){

                                                                                                AlertDialog dialog;
                                                                                                //alert dialog
                                                                                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                                                                                //Inflate the prof_exam_dialog view
                                                                                                @SuppressLint("InflateParams") View view10 = getLayoutInflater().inflate(R.layout.already_take_exam_dialog,null);
                                                                                                Button okay_btn = view10.findViewById(R.id.okay_btn);

                                                                                                //set this view to dialog
                                                                                                builder.setView(view10);
                                                                                                //create dialog now
                                                                                                dialog = builder.create();
                                                                                                dialog.show();
                                                                                                okay_btn.setOnClickListener(view -> dialog.dismiss());

                                                                                            }
                                                                                            else{

                                                                                                Intent i = new Intent(stud_take_exam.this,stud_exam_form.class);
                                                                                                text_code = examCode_txt.getText().toString();
                                                                                                i.putExtra("Value",text_code);
                                                                                                startActivity(i);
                                                                                                finish();

                                                                                            }


                                                                                        });

                                                                            }


                                                                        });

                                                            }


                                                        });

                                            }
                                            else {
                                                Toast.makeText(getApplicationContext(), "Exam code does not exist!",Toast.LENGTH_LONG).show();
                                            }

                                        });
                            }
                        }

                    });
        }

    }



}