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

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class student_reg extends AppCompatActivity {

    public void openDialog(){

        email_verify_dialog custom_dialog = new email_verify_dialog();
        custom_dialog.show(getSupportFragmentManager(),"Custom dialog");


    }

    public static final String TAG = "TAG";

    //course dropdown
    String[] course_items = {"BSCS", "BSIT"};
    ArrayAdapter<String> adapterItems;

    //section dropdown
    String[] section_items_1st = {"A", "B","C","D","E"};
    String[] section_items_2nd = {"A", "B","C","D"};
    String[] section_items_3rd = {"A", "B","C","D","E","F"};
    String[] section_items_4th = {"A", "B","C"};
    ArrayAdapter<String> adapterItems_2;

    //gender dropdown
    String[] gender_items = {"Male", "Female"};
    ArrayAdapter<String> adapterItems_3;

    //year dropdown
    String[] year_items = {"1st", "2nd","3rd","4th"};
    ArrayAdapter<String> adapterItems_4;

    Button reg_btn;
    Button back_btn;
    EditText stud_reg_email;
    EditText stud_reg_pass;
    EditText stud_reg_name;
    EditText stud_reg_Lname;
    AutoCompleteTextView stud_reg_gender;
    AutoCompleteTextView stud_reg_course;
    AutoCompleteTextView stud_reg_year;
    AutoCompleteTextView stud_reg_section;

    //progress bar
    ProgressBar progressBar;

    //firebase authentication
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;


    //firebase firestore
    FirebaseFirestore fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reg);

        reg_btn = findViewById(R.id.reg_btn);
        back_btn = findViewById(R.id.reg_back_btn);
        stud_reg_email = findViewById(R.id.stud_reg_email);
        stud_reg_pass = findViewById(R.id.reg_pass_edittx);
        stud_reg_name = findViewById(R.id.name);
        stud_reg_Lname = findViewById(R.id.last_name);
        stud_reg_gender= findViewById(R.id.gender_txt);
        stud_reg_course= findViewById(R.id.course_txt);
        stud_reg_year= findViewById(R.id.year_txt);
        stud_reg_section= findViewById(R.id.section_txt);
        progressBar = findViewById(R.id.progressBar);

        //firebase
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        //course dropdown
        adapterItems = new ArrayAdapter<>(this,R.layout.list_item,course_items);
        stud_reg_course.setAdapter(adapterItems);

        stud_reg_course.setOnItemClickListener((parent, view, position, id) -> {
           /* String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(),"Item: " + item, Toast.LENGTH_SHORT).show();*/
        });


        //gender dropdown
        adapterItems_3 = new ArrayAdapter<>(this,R.layout.list_item,gender_items);
        stud_reg_gender.setAdapter(adapterItems_3);

        stud_reg_gender.setOnItemClickListener((parent, view, position, id) -> {
           /* String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(),"Item: " + item, Toast.LENGTH_SHORT).show();*/
        });


        //year level dropdown

        adapterItems_4 = new ArrayAdapter<>(this,R.layout.list_item,year_items);
        stud_reg_year.setAdapter(adapterItems_4);

        stud_reg_year.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            TextInputLayout section_layout = findViewById(R.id.section_layout);
            stud_reg_section= findViewById(R.id.section_txt);

            switch (item) {
                case "1st":
                    section_layout.setEnabled(true);
                    stud_reg_section.setText("");

                    //section dropdown 1st yr
                    adapterItems_2 = new ArrayAdapter<>(this, R.layout.list_item, section_items_1st);
                    stud_reg_section.setAdapter(adapterItems_2);

                    break;
                case "2nd":
                    section_layout.setEnabled(true);
                    stud_reg_section.setText("");

                    //section dropdown 2nd yr
                    adapterItems_2 = new ArrayAdapter<>(this, R.layout.list_item, section_items_2nd);
                    stud_reg_section.setAdapter(adapterItems_2);
                    break;
                case "3rd":
                    section_layout.setEnabled(true);
                    stud_reg_section.setText("");

                    //section dropdown 3rd yr
                    adapterItems_2 = new ArrayAdapter<>(this, R.layout.list_item, section_items_3rd);
                    stud_reg_section.setAdapter(adapterItems_2);
                    break;
                case "4th":
                    section_layout.setEnabled(true);
                    stud_reg_section.setText("");

                    //section dropdown 4th yr
                    adapterItems_2 = new ArrayAdapter<>(this, R.layout.list_item, section_items_4th);
                    stud_reg_section.setAdapter(adapterItems_2);
                    break;
                default:
                    stud_reg_section.setText("");
                    section_layout.setEnabled(false);
                    break;
            }

        });

        //firebase authentication
        mAuth = FirebaseAuth.getInstance();

        //firebase Firestore
        fstore = FirebaseFirestore.getInstance();

        //register button click
        reg_btn.setOnClickListener(view -> createUser());

        //back button click
        back_btn.setOnClickListener(view -> startActivity(new Intent(student_reg.this, MainActivity.class)));

    }

    private void createUser(){

        String email = stud_reg_email.getText().toString();
        String pass = stud_reg_pass.getText().toString();
        String name = stud_reg_name.getText().toString();
        String last_name = stud_reg_Lname.getText().toString();
        String gender = stud_reg_gender.getText().toString();
        String course = stud_reg_course.getText().toString();
        String year_lvl = stud_reg_year.getText().toString();
        String section = stud_reg_section.getText().toString();


        if(TextUtils.isEmpty(email)){
            stud_reg_email.setError("Email cannot be empty");
            stud_reg_email.requestFocus();
        }
        else if(TextUtils.isEmpty(pass)){
            stud_reg_pass.setError("Password cannot be empty");
            stud_reg_pass.requestFocus();
        }
        else if(TextUtils.isEmpty(name)){
            stud_reg_name.setError("Name cannot be empty");
            stud_reg_name.requestFocus();
        }
        else if(TextUtils.isEmpty(last_name)){
            stud_reg_Lname.setError("Last name cannot be empty");
            stud_reg_Lname.requestFocus();
        }
        else if(TextUtils.isEmpty(gender)){
            stud_reg_gender.setError("Gender cannot be empty");
            stud_reg_gender.requestFocus();
        }
        else if(TextUtils.isEmpty(course)){
            stud_reg_course.setError("Course cannot be empty");
            stud_reg_course.requestFocus();
        }
        else if(TextUtils.isEmpty(year_lvl)){
            stud_reg_year.setError("Year level cannot be empty");
            stud_reg_year.requestFocus();
        }
        else if(TextUtils.isEmpty(section)){
            stud_reg_section.setError("Section cannot be empty");
            stud_reg_section.requestFocus();
        }
        else {

            progressBar.setVisibility(View.VISIBLE);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("cvsu_imus_students").whereEqualTo("email",email)
                    .get().addOnSuccessListener(queryDocumentSnapshots -> {

                        if(!queryDocumentSnapshots.isEmpty()){
                            /////
                            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful()){
                                    progressBar.setVisibility(View.GONE);
                                    FirebaseUser userAuth = FirebaseAuth.getInstance().getCurrentUser();

                                    userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                                    DocumentReference documentReference = fstore.collection("user_students").document(userID);
                                    Map<String,Object> user = new HashMap<>();
                                    user.put("email", email);
                                    user.put("password", pass);
                                    user.put("first_name", name);
                                    user.put("last_name", last_name);
                                    user.put("gender", gender);
                                    user.put("course", course);
                                    user.put("year_lvl", year_lvl);
                                    user.put("section", section);
                                    user.put("user_type", "Student");
                                    documentReference.set(user).addOnSuccessListener(unused -> Log.d(TAG, "onSuccess: user profile is created for " + userID));
                                    assert userAuth != null;
                                    userAuth.sendEmailVerification();
                                    mAuth = FirebaseAuth.getInstance();
                                    mAuth.signOut();
                                    openDialog();

                                }
                                else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(student_reg.this, "Registration Error: " + Objects.requireNonNull(task1.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {

                            progressBar.setVisibility(View.GONE);
                            stud_reg_email.setError("You are not a student of CvSU-Imus!");
                            stud_reg_email.requestFocus();
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