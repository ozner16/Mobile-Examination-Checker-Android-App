package com.mobileexaminationchecker;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class prof_reg extends AppCompatActivity {

    public void openDialog(){

        email_verify_dialog custom_dialog = new email_verify_dialog();
        custom_dialog.show(getSupportFragmentManager(),"Custom dialog");


    }

    public static final String TAG = "TAG";
    //gender dropdown
    String[] gender_items = {"Male", "Female"};
    ArrayAdapter<String> adapter_gender;

    //subject code dropdown
    String[] subjCode_items = {"COSC 110", "GNED 07","GNED 10"};
    ArrayAdapter<String> adapter_subjCode;

    //department dropdown
    String[] dept_items = {"Computer Studies", "Hospitality Management"};
    ArrayAdapter<String> adapter_dept;

    Button reg_btn;
    Button back_btn;
    EditText prof_reg_email;
    EditText prof_reg_pass;
    EditText prof_reg_name;
    EditText prof_reg_Lname;
    AutoCompleteTextView prof_reg_gender;
    AutoCompleteTextView prof_reg_subjCode;
    AutoCompleteTextView prof_reg_dept;

    //progress bar
    ProgressBar progressBar;

    //firebase
    FirebaseAuth mAuth;

    //firebase firestore
    FirebaseFirestore fstore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_reg);

        reg_btn = findViewById(R.id.reg_btn);
        back_btn = findViewById(R.id.reg_back_btn);
        prof_reg_email = findViewById(R.id.email);
        prof_reg_pass = findViewById(R.id.reg_pass_edittx);
        prof_reg_name = findViewById(R.id.name);
        prof_reg_Lname = findViewById(R.id.last_name);
        prof_reg_gender = findViewById(R.id.prof_gender_reg);
        prof_reg_subjCode = findViewById(R.id.subjCode_prof);
        prof_reg_dept = findViewById(R.id.prof_dept_reg);
        progressBar = findViewById(R.id.progressBar);


        //gender dropdown
        adapter_gender = new ArrayAdapter<>(this,R.layout.list_item,gender_items);
        prof_reg_gender.setAdapter(adapter_gender);

        prof_reg_gender.setOnItemClickListener((parent, view, position, id) -> {
          /*  String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(),"Item: " + item, Toast.LENGTH_SHORT).show();*/
        });

        //subjCode dropdown
        adapter_subjCode = new ArrayAdapter<>(this,R.layout.list_item,subjCode_items);
        prof_reg_subjCode.setAdapter(adapter_subjCode);

        prof_reg_subjCode.setOnItemClickListener((parent, view, position, id) -> {
           /* String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(),"Item: " + item, Toast.LENGTH_SHORT).show();*/
        });

        //department dropdown
        adapter_dept = new ArrayAdapter<>(this,R.layout.list_item,dept_items);
        prof_reg_dept.setAdapter(adapter_dept);

        prof_reg_dept.setOnItemClickListener((parent, view, position, id) -> {
           /* String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(),"Item: " + item, Toast.LENGTH_SHORT).show();*/
        });

        //firebase
        mAuth = FirebaseAuth.getInstance();

        //firebase Firestore
        fstore = FirebaseFirestore.getInstance();

        //register button click
        reg_btn.setOnClickListener(view -> createUser());

        //back button
        back_btn.setOnClickListener(view -> startActivity(new Intent(prof_reg.this, MainActivity.class)));

    }

    private void createUser(){

        String email = prof_reg_email.getText().toString();
        String pass = prof_reg_pass.getText().toString();
        String name = prof_reg_name.getText().toString();
        String last_name = prof_reg_Lname.getText().toString();
        String gender = prof_reg_gender.getText().toString();
        String subject_code = prof_reg_subjCode.getText().toString();
        String dept = prof_reg_dept.getText().toString();

        if(TextUtils.isEmpty(email)){
            prof_reg_email.setError("Email cannot be empty");
            prof_reg_email.requestFocus();
        }
        else if(TextUtils.isEmpty(pass)){
            prof_reg_pass.setError("Password cannot be empty");
            prof_reg_pass.requestFocus();
        }
        else if(TextUtils.isEmpty(name)){
            prof_reg_name.setError("Name cannot be empty");
            prof_reg_name.requestFocus();
        }
        else if(TextUtils.isEmpty(last_name)){
            prof_reg_Lname.setError("Last name cannot be empty");
            prof_reg_Lname.requestFocus();
        }
        else if(TextUtils.isEmpty(gender)){
            prof_reg_gender.setError("Gender cannot be empty");
            prof_reg_gender.requestFocus();
        }
        else if(TextUtils.isEmpty(subject_code)){
            prof_reg_subjCode.setError("Subject code cannot be empty");
            prof_reg_subjCode.requestFocus();
        }
        else if(TextUtils.isEmpty(dept)){
            prof_reg_dept.setError("Department cannot be empty");
            prof_reg_dept.requestFocus();
        }
        else{

            progressBar.setVisibility(View.VISIBLE);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("cvsu_imus_instructors").whereEqualTo("email",email)
                    .get().addOnSuccessListener(queryDocumentSnapshots -> {

                        if(!queryDocumentSnapshots.isEmpty()){
                            ///
                            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful()){
                                    progressBar.setVisibility(View.GONE);
                                    FirebaseUser userAuth = FirebaseAuth.getInstance().getCurrentUser();

                                    userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                                    DocumentReference documentReference = fstore.collection("user_professors").document(userID);
                                    Map<String,Object> user = new HashMap<>();
                                    user.put("email", email);
                                    user.put("password", pass);
                                    user.put("first_name", name);
                                    user.put("last_name", last_name);
                                    user.put("gender", gender);
                                    user.put("subject_code", subject_code);
                                    user.put("department", dept);
                                    user.put("user_type", "Instructor");
                                    documentReference.set(user).addOnSuccessListener(unused -> Log.d(TAG, "onSuccess: user profile is created for " + userID));
                                    assert userAuth != null;
                                    userAuth.sendEmailVerification();
                                    mAuth = FirebaseAuth.getInstance();
                                    mAuth.signOut();
                                    openDialog();

                                }
                                else{
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(prof_reg.this, "Registration Error: " + Objects.requireNonNull(task1.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            prof_reg_email.setError("You are not Instructor of CvSU-Imus!");
                            prof_reg_email.requestFocus();
                        }

                    });






        }
    }

    @Override
    public void onStart() {
        super.onStart();
        progressBar.setVisibility(View.GONE);
    }
}