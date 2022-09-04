package com.mobileexaminationchecker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class prof_create_exam_two extends AppCompatActivity {

    public static final String TAG = "TAG";
    Button create_exam_button;
    Button delete_exam_button;
    Button update_exam_button;
    TextView exam_code_text;
    TextView date_created_text;
    EditText Instruction;

    //current date
    DateFormat df;
    String date;

    //firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageReference;

    TextInputEditText Q1_txt;
    TextInputEditText A1_txt;
    TextInputEditText Q2_txt;
    TextInputEditText A2_txt;
    TextInputEditText Q3_txt;
    TextInputEditText A3_txt;
    TextInputEditText Q4_txt;
    TextInputEditText A4_txt;
    TextInputEditText Q5_txt;
    TextInputEditText A5_txt;
    TextInputEditText Q6_txt;
    TextInputEditText A6_txt;
    TextInputEditText Q7_txt;
    TextInputEditText A7_txt;
    TextInputEditText Q8_txt;
    TextInputEditText A8_txt;
    TextInputEditText Q9_txt;
    TextInputEditText A9_txt;
    TextInputEditText Q10_txt;
    TextInputEditText A10_txt;
    TextInputEditText Q11_txt;
    TextInputEditText A11_txt;
    TextInputEditText Q12_txt;
    TextInputEditText A12_txt;
    TextInputEditText Q13_txt;
    TextInputEditText A13_txt;
    TextInputEditText Q14_txt;
    TextInputEditText A14_txt;
    TextInputEditText Q15_txt;
    TextInputEditText A15_txt;
    TextInputEditText Q16_txt;
    TextInputEditText A16_txt;
    TextInputEditText Q17_txt;
    TextInputEditText A17_txt;
    TextInputEditText Q18_txt;
    TextInputEditText A18_txt;
    TextInputEditText Q19_txt;
    TextInputEditText A19_txt;
    TextInputEditText Q20_txt;
    TextInputEditText A20_txt;

    TextInputEditText code_txt;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_create_exam_two);

        //current date
        df = new SimpleDateFormat("MMM d, yyyy , hh:mm a");
        date = df.format(Calendar.getInstance().getTime());

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        Instruction = findViewById(R.id.Instruction);
        date_created_text = findViewById(R.id.date_created_date);
        exam_code_text = findViewById(R.id.exam_code);
        create_exam_button = findViewById(R.id.create_exam_button);
        delete_exam_button = findViewById(R.id.delete_exam_button);
        update_exam_button = findViewById(R.id.update_exam_button);
        Q1_txt = findViewById(R.id.Q1_txt);
        A1_txt = findViewById(R.id.A1_txt);
        Q2_txt = findViewById(R.id.Q2_txt);
        A2_txt = findViewById(R.id.A2_txt);
        Q3_txt = findViewById(R.id.Q3_txt);
        A3_txt = findViewById(R.id.A3_txt);
        Q4_txt = findViewById(R.id.Q4_txt);
        A4_txt = findViewById(R.id.A4_txt);
        Q5_txt = findViewById(R.id.Q5_txt);
        A5_txt = findViewById(R.id.A5_txt);
        Q6_txt = findViewById(R.id.Q6_txt);
        A6_txt = findViewById(R.id.A6_txt);
        Q7_txt = findViewById(R.id.Q7_txt);
        A7_txt = findViewById(R.id.A7_txt);
        Q8_txt = findViewById(R.id.Q8_txt);
        A8_txt = findViewById(R.id.A8_txt);
        Q9_txt = findViewById(R.id.Q9_txt);
        A9_txt = findViewById(R.id.A9_txt);
        Q10_txt = findViewById(R.id.Q10_txt);
        A10_txt = findViewById(R.id.A10_txt);
        Q11_txt = findViewById(R.id.Q11_txt);
        A11_txt = findViewById(R.id.A11_txt);
        Q12_txt = findViewById(R.id.Q12_txt);
        A12_txt = findViewById(R.id.A12_txt);
        Q13_txt = findViewById(R.id.Q13_txt);
        A13_txt = findViewById(R.id.A13_txt);
        Q14_txt = findViewById(R.id.Q14_txt);
        A14_txt = findViewById(R.id.A14_txt);
        Q15_txt = findViewById(R.id.Q15_txt);
        A15_txt = findViewById(R.id.A15_txt);
        Q16_txt = findViewById(R.id.Q16_txt);
        A16_txt = findViewById(R.id.A16_txt);
        Q17_txt = findViewById(R.id.Q17_txt);
        A17_txt = findViewById(R.id.A17_txt);
        Q18_txt = findViewById(R.id.Q18_txt);
        A18_txt = findViewById(R.id.A18_txt);
        Q19_txt = findViewById(R.id.Q19_txt);
        A19_txt = findViewById(R.id.A19_txt);
        Q20_txt = findViewById(R.id.Q20_txt);
        A20_txt = findViewById(R.id.A20_txt);
        code_txt = findViewById(R.id.code_txt);



        //create exam button
        create_exam_button.setOnClickListener(view -> create_exam());
        update_exam_button.setOnClickListener(view -> update_exam());
        delete_exam_button.setOnClickListener(view -> remove_exam());


    }

    private void remove_exam() {

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //set text to textviews
        DocumentReference documentReference = fStore.collection("user_professors").document(user.getUid());
        documentReference.addSnapshotListener(this,(documentSnapshot,e)->{
            assert documentSnapshot != null;
            String subject_code = documentSnapshot.getString("subject_code");

            if(documentSnapshot.exists()){
                assert subject_code != null;
                fStore.collection("exams_20").document(user.getUid()).delete()
                        .addOnSuccessListener(unused -> {

                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            db.collection(subject_code + "_examID_20")
                                    .get()
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {

                                                fStore.collection("stud_exam_info_20").document(document.getId()).collection(subject_code).document(exam_code_text.getText().toString()).delete();
                                                fStore.collection(exam_code_text.getText().toString()).document(document.getId()).delete();
                                                
                                            }

                                            //out of looping above
                                            Toast.makeText(getApplicationContext(), "Exam Removed succesfully!",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(prof_create_exam_two.this, prof_home.class));
                                        } else {
                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                        }
                                    });

                        });

            }
            else {

                Log.d("tag","Document do not exist");
            }
        });




    }

    private void update_exam() {
        String q1 = Objects.requireNonNull(Q1_txt.getText()).toString();
        String a1 = Objects.requireNonNull(A1_txt.getText()).toString();
        String q2 = Objects.requireNonNull(Q2_txt.getText()).toString();
        String a2 = Objects.requireNonNull(A2_txt.getText()).toString();
        String q3 = Objects.requireNonNull(Q3_txt.getText()).toString();
        String a3 = Objects.requireNonNull(A3_txt.getText()).toString();
        String q4 = Objects.requireNonNull(Q4_txt.getText()).toString();
        String a4 = Objects.requireNonNull(A4_txt.getText()).toString();
        String q5 = Objects.requireNonNull(Q5_txt.getText()).toString();
        String a5 = Objects.requireNonNull(A5_txt.getText()).toString();
        String q6 = Objects.requireNonNull(Q6_txt.getText()).toString();
        String a6 = Objects.requireNonNull(A6_txt.getText()).toString();
        String q7 = Objects.requireNonNull(Q7_txt.getText()).toString();
        String a7 = Objects.requireNonNull(A7_txt.getText()).toString();
        String q8 = Objects.requireNonNull(Q8_txt.getText()).toString();
        String a8 = Objects.requireNonNull(A8_txt.getText()).toString();
        String q9 = Objects.requireNonNull(Q9_txt.getText()).toString();
        String a9 = Objects.requireNonNull(A9_txt.getText()).toString();
        String q10 = Objects.requireNonNull(Q10_txt.getText()).toString();
        String a10 = Objects.requireNonNull(A10_txt.getText()).toString();
        String q11 = Objects.requireNonNull(Q11_txt.getText()).toString();
        String a11 = Objects.requireNonNull(A11_txt.getText()).toString();
        String q12 = Objects.requireNonNull(Q12_txt.getText()).toString();
        String a12 = Objects.requireNonNull(A12_txt.getText()).toString();
        String q13 = Objects.requireNonNull(Q13_txt.getText()).toString();
        String a13 = Objects.requireNonNull(A13_txt.getText()).toString();
        String q14 = Objects.requireNonNull(Q14_txt.getText()).toString();
        String a14 = Objects.requireNonNull(A14_txt.getText()).toString();
        String q15 = Objects.requireNonNull(Q15_txt.getText()).toString();
        String a15 = Objects.requireNonNull(A15_txt.getText()).toString();
        String q16 = Objects.requireNonNull(Q16_txt.getText()).toString();
        String a16 = Objects.requireNonNull(A16_txt.getText()).toString();
        String q17 = Objects.requireNonNull(Q17_txt.getText()).toString();
        String a17 = Objects.requireNonNull(A17_txt.getText()).toString();
        String q18 = Objects.requireNonNull(Q18_txt.getText()).toString();
        String a18 = Objects.requireNonNull(A18_txt.getText()).toString();
        String q19 = Objects.requireNonNull(Q19_txt.getText()).toString();
        String a19 = Objects.requireNonNull(A19_txt.getText()).toString();
        String q20 = Objects.requireNonNull(Q20_txt.getText()).toString();
        String a20 = Objects.requireNonNull(A20_txt.getText()).toString();
        String instruction = Instruction.getText().toString();
        String exam_code = Objects.requireNonNull(code_txt.getText()).toString();

        if(TextUtils.isEmpty(q1) || TextUtils.isEmpty(a1) || TextUtils.isEmpty(q2) || TextUtils.isEmpty(a2) ||
                TextUtils.isEmpty(q3) || TextUtils.isEmpty(a3) || TextUtils.isEmpty(q4) || TextUtils.isEmpty(a4)
                || TextUtils.isEmpty(q5) || TextUtils.isEmpty(a5)|| TextUtils.isEmpty(q6) || TextUtils.isEmpty(a6)
                || TextUtils.isEmpty(q7) || TextUtils.isEmpty(a7)
                || TextUtils.isEmpty(q8) || TextUtils.isEmpty(a8)
                || TextUtils.isEmpty(q9) || TextUtils.isEmpty(a9)
                || TextUtils.isEmpty(q10) || TextUtils.isEmpty(a10)
                || TextUtils.isEmpty(q11) || TextUtils.isEmpty(a11)
                || TextUtils.isEmpty(q12) || TextUtils.isEmpty(a12)
                || TextUtils.isEmpty(q13) || TextUtils.isEmpty(a13)
                || TextUtils.isEmpty(q14) || TextUtils.isEmpty(a14)
                || TextUtils.isEmpty(q15) || TextUtils.isEmpty(a15)
                || TextUtils.isEmpty(q16) || TextUtils.isEmpty(a16)
                || TextUtils.isEmpty(q16) || TextUtils.isEmpty(a16)
                || TextUtils.isEmpty(q17) || TextUtils.isEmpty(a17)
                || TextUtils.isEmpty(q18) || TextUtils.isEmpty(a18)
                || TextUtils.isEmpty(q19) || TextUtils.isEmpty(a19)
                || TextUtils.isEmpty(q20) || TextUtils.isEmpty(a20)
                || TextUtils.isEmpty(exam_code) || TextUtils.isEmpty(instruction)){

            Toast.makeText(getApplicationContext(), "All fields should be filled!",Toast.LENGTH_LONG).show();
        }

        else {


            //firebase
            fAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();
            storageReference = FirebaseStorage.getInstance().getReference();
            user = fAuth.getCurrentUser();
            storage= FirebaseStorage.getInstance();
            storageReference = storage.getReference();

            //set text to textviews
            DocumentReference documentReference = fStore.collection("user_professors").document(user.getUid());
            documentReference.addSnapshotListener(this,(documentSnapshot,e)->{
                assert documentSnapshot != null;
                String subject_code = documentSnapshot.getString("subject_code");
                String fname = Objects.requireNonNull(documentSnapshot).getString("first_name");
                String Lname = documentSnapshot.getString("last_name");
                String email = documentSnapshot.getString("email");
                if(documentSnapshot.exists()){

                    DocumentReference documentReference2 = fStore.collection("exams_20").document(user.getUid());
                    documentReference2.addSnapshotListener(this,(documentSnapshot3,g)->{

                        String exam_code1 = Objects.requireNonNull(documentSnapshot3).getString("exam_code");

                        if(documentSnapshot3.exists()){
                            if(exam_code.equals(exam_code1)){

                                //
                                assert subject_code != null;
                                DocumentReference documentReference1 = fStore.collection("exams_20").document(user.getUid());
                                Map<String,Object> exam = new HashMap<>();
                                exam.put("exam_code", exam_code);
                                exam.put("ques1", q1);
                                exam.put("ans1", a1);
                                exam.put("ques2", q2);
                                exam.put("ans2", a2);
                                exam.put("ques3", q3);
                                exam.put("ans3", a3);
                                exam.put("ques4", q4);
                                exam.put("ans4", a4);
                                exam.put("ques5", q5);
                                exam.put("ans5", a5);
                                exam.put("ques6", q6);
                                exam.put("ans6", a6);
                                exam.put("ques7", q7);
                                exam.put("ans7", a7);
                                exam.put("ques8", q8);
                                exam.put("ans8", a8);
                                exam.put("ques9", q9);
                                exam.put("ans9", a9);
                                exam.put("ques10", q10);
                                exam.put("ans10", a10);
                                exam.put("ques11", q11);
                                exam.put("ans11", a11);
                                exam.put("ques12", q12);
                                exam.put("ans12", a12);
                                exam.put("ques13", q13);
                                exam.put("ans13", a13);
                                exam.put("ques14", q14);
                                exam.put("ans14", a14);
                                exam.put("ques15", q15);
                                exam.put("ans15", a15);
                                exam.put("ques16", q16);
                                exam.put("ans16", a16);
                                exam.put("ques17", q17);
                                exam.put("ans17", a17);
                                exam.put("ques18", q18);
                                exam.put("ans18", a18);
                                exam.put("ques19", q19);
                                exam.put("ans19", a19);
                                exam.put("ques20", q20);
                                exam.put("ans20", a20);
                                exam.put("user_type", "Professor");
                                exam.put("instructor", fname + " " + Lname);
                                exam.put("email", email);
                                exam.put("date_created", date);
                                exam.put("subject", subject_code);
                                exam.put("instruction", instruction);
                                documentReference1.set(exam).addOnSuccessListener(unused -> Log.d(TAG, "Exam added succesfully"));
                                Toast.makeText(getApplicationContext(), "Exam updated succesfully!",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(prof_create_exam_two.this, prof_home.class));

                            }
                            else{
                                FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                                db1.collection("exams_40").whereEqualTo("exam_code",exam_code)
                                        .get().addOnSuccessListener(queryDocumentSnapshots1 -> {

                                            //checking if the document exist
                                            if(!queryDocumentSnapshots1.isEmpty()) {

                                                code_txt.setError("Exam code already exist. Please try different code!");
                                                code_txt.requestFocus();

                                            }
                                            else {

                                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                                db.collection("exams_20").whereEqualTo("exam_code",exam_code)
                                                        .get().addOnSuccessListener(queryDocumentSnapshots -> {

                                                            if(!queryDocumentSnapshots.isEmpty()){
                                                                code_txt.setError("Exam code already exist. Please try different code!");
                                                                code_txt.requestFocus();
                                                            }
                                                            else {
                                                                //
                                                                assert subject_code != null;
                                                                DocumentReference documentReference1 = fStore.collection("exams_20").document(user.getUid());
                                                                Map<String,Object> exam = new HashMap<>();
                                                                exam.put("exam_code", exam_code);
                                                                exam.put("ques1", q1);
                                                                exam.put("ans1", a1);
                                                                exam.put("ques2", q2);
                                                                exam.put("ans2", a2);
                                                                exam.put("ques3", q3);
                                                                exam.put("ans3", a3);
                                                                exam.put("ques4", q4);
                                                                exam.put("ans4", a4);
                                                                exam.put("ques5", q5);
                                                                exam.put("ans5", a5);
                                                                exam.put("ques6", q6);
                                                                exam.put("ans6", a6);
                                                                exam.put("ques7", q7);
                                                                exam.put("ans7", a7);
                                                                exam.put("ques8", q8);
                                                                exam.put("ans8", a8);
                                                                exam.put("ques9", q9);
                                                                exam.put("ans9", a9);
                                                                exam.put("ques10", q10);
                                                                exam.put("ans10", a10);
                                                                exam.put("ques11", q11);
                                                                exam.put("ans11", a11);
                                                                exam.put("ques12", q12);
                                                                exam.put("ans12", a12);
                                                                exam.put("ques13", q13);
                                                                exam.put("ans13", a13);
                                                                exam.put("ques14", q14);
                                                                exam.put("ans14", a14);
                                                                exam.put("ques15", q15);
                                                                exam.put("ans15", a15);
                                                                exam.put("ques16", q16);
                                                                exam.put("ans16", a16);
                                                                exam.put("ques17", q17);
                                                                exam.put("ans17", a17);
                                                                exam.put("ques18", q18);
                                                                exam.put("ans18", a18);
                                                                exam.put("ques19", q19);
                                                                exam.put("ans19", a19);
                                                                exam.put("ques20", q20);
                                                                exam.put("ans20", a20);
                                                                exam.put("user_type", "Professor");
                                                                exam.put("instructor", fname + " " + Lname);
                                                                exam.put("email", email);
                                                                exam.put("date_created", date);
                                                                exam.put("subject", subject_code);
                                                                exam.put("instruction", instruction);
                                                                documentReference1.set(exam).addOnSuccessListener(unused -> Log.d(TAG, "Exam added succesfully"));
                                                                Toast.makeText(getApplicationContext(), "Exam updated succesfully!",Toast.LENGTH_LONG).show();
                                                                startActivity(new Intent(prof_create_exam_two.this, prof_home.class));
                                                            }

                                                        });
                                            }


                                        });



                            }





                        }
                        else {

                            Log.d("tag","Document do not exist");
                        }
                    });




                }
                else {

                    Log.d("tag","Document do not exist");
                }
            });





        }

    }

    public void create_exam(){

        String q1 = Objects.requireNonNull(Q1_txt.getText()).toString();
        String a1 = Objects.requireNonNull(A1_txt.getText()).toString();
        String q2 = Objects.requireNonNull(Q2_txt.getText()).toString();
        String a2 = Objects.requireNonNull(A2_txt.getText()).toString();
        String q3 = Objects.requireNonNull(Q3_txt.getText()).toString();
        String a3 = Objects.requireNonNull(A3_txt.getText()).toString();
        String q4 = Objects.requireNonNull(Q4_txt.getText()).toString();
        String a4 = Objects.requireNonNull(A4_txt.getText()).toString();
        String q5 = Objects.requireNonNull(Q5_txt.getText()).toString();
        String a5 = Objects.requireNonNull(A5_txt.getText()).toString();
        String q6 = Objects.requireNonNull(Q6_txt.getText()).toString();
        String a6 = Objects.requireNonNull(A6_txt.getText()).toString();
        String q7 = Objects.requireNonNull(Q7_txt.getText()).toString();
        String a7 = Objects.requireNonNull(A7_txt.getText()).toString();
        String q8 = Objects.requireNonNull(Q8_txt.getText()).toString();
        String a8 = Objects.requireNonNull(A8_txt.getText()).toString();
        String q9 = Objects.requireNonNull(Q9_txt.getText()).toString();
        String a9 = Objects.requireNonNull(A9_txt.getText()).toString();
        String q10 = Objects.requireNonNull(Q10_txt.getText()).toString();
        String a10 = Objects.requireNonNull(A10_txt.getText()).toString();
        String q11 = Objects.requireNonNull(Q11_txt.getText()).toString();
        String a11 = Objects.requireNonNull(A11_txt.getText()).toString();
        String q12 = Objects.requireNonNull(Q12_txt.getText()).toString();
        String a12 = Objects.requireNonNull(A12_txt.getText()).toString();
        String q13 = Objects.requireNonNull(Q13_txt.getText()).toString();
        String a13 = Objects.requireNonNull(A13_txt.getText()).toString();
        String q14 = Objects.requireNonNull(Q14_txt.getText()).toString();
        String a14 = Objects.requireNonNull(A14_txt.getText()).toString();
        String q15 = Objects.requireNonNull(Q15_txt.getText()).toString();
        String a15 = Objects.requireNonNull(A15_txt.getText()).toString();
        String q16 = Objects.requireNonNull(Q16_txt.getText()).toString();
        String a16 = Objects.requireNonNull(A16_txt.getText()).toString();
        String q17 = Objects.requireNonNull(Q17_txt.getText()).toString();
        String a17 = Objects.requireNonNull(A17_txt.getText()).toString();
        String q18 = Objects.requireNonNull(Q18_txt.getText()).toString();
        String a18 = Objects.requireNonNull(A18_txt.getText()).toString();
        String q19 = Objects.requireNonNull(Q19_txt.getText()).toString();
        String a19 = Objects.requireNonNull(A19_txt.getText()).toString();
        String q20 = Objects.requireNonNull(Q20_txt.getText()).toString();
        String a20 = Objects.requireNonNull(A20_txt.getText()).toString();
        String exam_code = Objects.requireNonNull(code_txt.getText()).toString();
        String instruction = Instruction.getText().toString();

        if(TextUtils.isEmpty(q1) || TextUtils.isEmpty(a1) || TextUtils.isEmpty(q2) || TextUtils.isEmpty(a2) ||
                TextUtils.isEmpty(q3) || TextUtils.isEmpty(a3) || TextUtils.isEmpty(q4) || TextUtils.isEmpty(a4)
                || TextUtils.isEmpty(q5) || TextUtils.isEmpty(a5)|| TextUtils.isEmpty(q6) || TextUtils.isEmpty(a6)
                || TextUtils.isEmpty(q7) || TextUtils.isEmpty(a7)
                || TextUtils.isEmpty(q8) || TextUtils.isEmpty(a8)
                || TextUtils.isEmpty(q9) || TextUtils.isEmpty(a9)
                || TextUtils.isEmpty(q10) || TextUtils.isEmpty(a10)
                || TextUtils.isEmpty(q11) || TextUtils.isEmpty(a11)
                || TextUtils.isEmpty(q12) || TextUtils.isEmpty(a12)
                || TextUtils.isEmpty(q13) || TextUtils.isEmpty(a13)
                || TextUtils.isEmpty(q14) || TextUtils.isEmpty(a14)
                || TextUtils.isEmpty(q15) || TextUtils.isEmpty(a15)
                || TextUtils.isEmpty(q16) || TextUtils.isEmpty(a16)
                || TextUtils.isEmpty(q16) || TextUtils.isEmpty(a16)
                || TextUtils.isEmpty(q17) || TextUtils.isEmpty(a17)
                || TextUtils.isEmpty(q18) || TextUtils.isEmpty(a18)
                || TextUtils.isEmpty(q19) || TextUtils.isEmpty(a19)
                || TextUtils.isEmpty(q20) || TextUtils.isEmpty(a20)
                || TextUtils.isEmpty(exam_code)  || TextUtils.isEmpty(instruction)){

            Toast.makeText(getApplicationContext(), "All fields should be filled!",Toast.LENGTH_LONG).show();
        }

        else {


            //firebase
            fAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();
            storageReference = FirebaseStorage.getInstance().getReference();
            user = fAuth.getCurrentUser();
            storage= FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseFirestore db1 = FirebaseFirestore.getInstance();

            db1.collection("exams_40").whereEqualTo("exam_code",exam_code)
                    .get().addOnSuccessListener(queryDocumentSnapshots1 -> {

                        //checking if the document exist
                        if(!queryDocumentSnapshots1.isEmpty()) {

                            code_txt.setError("Exam code already exist. Please try different code!");
                            code_txt.requestFocus();

                        }
                        else {

                            db.collection("exams_20").whereEqualTo("exam_code",exam_code)
                                    .get().addOnSuccessListener(queryDocumentSnapshots -> {

                                        //checking if the document exist
                                        if(!queryDocumentSnapshots.isEmpty()){

                                            code_txt.setError("Exam code already exist. Please try different code!");
                                            code_txt.requestFocus();

                                        }
                                        else {

                                            //set text to textviews
                                            DocumentReference documentReference = fStore.collection("user_professors").document(user.getUid());
                                            documentReference.addSnapshotListener(this,(documentSnapshot,e)->{
                                                assert documentSnapshot != null;
                                                String subject_code = documentSnapshot.getString("subject_code");
                                                String fname = Objects.requireNonNull(documentSnapshot).getString("first_name");
                                                String Lname = documentSnapshot.getString("last_name");
                                                String email = documentSnapshot.getString("email");
                                                if(documentSnapshot.exists()){

                                                    assert subject_code != null;
                                                    DocumentReference documentReference1 = fStore.collection("exams_20").document(user.getUid());
                                                    Map<String,Object> exam = new HashMap<>();
                                                    exam.put("exam_code", exam_code);
                                                    exam.put("ques1", q1);
                                                    exam.put("ans1", a1);
                                                    exam.put("ques2", q2);
                                                    exam.put("ans2", a2);
                                                    exam.put("ques3", q3);
                                                    exam.put("ans3", a3);
                                                    exam.put("ques4", q4);
                                                    exam.put("ans4", a4);
                                                    exam.put("ques5", q5);
                                                    exam.put("ans5", a5);
                                                    exam.put("ques6", q6);
                                                    exam.put("ans6", a6);
                                                    exam.put("ques7", q7);
                                                    exam.put("ans7", a7);
                                                    exam.put("ques8", q8);
                                                    exam.put("ans8", a8);
                                                    exam.put("ques9", q9);
                                                    exam.put("ans9", a9);
                                                    exam.put("ques10", q10);
                                                    exam.put("ans10", a10);
                                                    exam.put("ques11", q11);
                                                    exam.put("ans11", a11);
                                                    exam.put("ques12", q12);
                                                    exam.put("ans12", a12);
                                                    exam.put("ques13", q13);
                                                    exam.put("ans13", a13);
                                                    exam.put("ques14", q14);
                                                    exam.put("ans14", a14);
                                                    exam.put("ques15", q15);
                                                    exam.put("ans15", a15);
                                                    exam.put("ques16", q16);
                                                    exam.put("ans16", a16);
                                                    exam.put("ques17", q17);
                                                    exam.put("ans17", a17);
                                                    exam.put("ques18", q18);
                                                    exam.put("ans18", a18);
                                                    exam.put("ques19", q19);
                                                    exam.put("ans19", a19);
                                                    exam.put("ques20", q20);
                                                    exam.put("ans20", a20);
                                                    exam.put("user_type", "Professor");
                                                    exam.put("instructor", fname + " " + Lname);
                                                    exam.put("email", email);
                                                    exam.put("date_created", date);
                                                    exam.put("subject", subject_code);
                                                    exam.put("instruction", instruction);
                                                    documentReference1.set(exam).addOnSuccessListener(unused -> Log.d(TAG, "Exam added succesfully"));
                                                    Toast.makeText(getApplicationContext(), "Exam succesfully created!",Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(prof_create_exam_two.this, prof_home.class));


                                                }
                                                else {

                                                    Log.d("tag","Document do not exist");
                                                }
                                            });

                                        }

                                    });
                        }



                    });








        }



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        //set text to textviews
        DocumentReference documentReference = fStore.collection("user_professors").document(user.getUid());
        documentReference.addSnapshotListener(this,(documentSnapshot,e)->{

            assert documentSnapshot != null;
            String subject_code = documentSnapshot.getString("subject_code");
            if(documentSnapshot.exists()){

                assert subject_code != null;
                DocumentReference documentReference1 = fStore.collection("exams_20").document(user.getUid());
                documentReference1.addSnapshotListener(this,(documentSnapshot1,f)->{

                    String exam_code = Objects.requireNonNull(documentSnapshot1).getString("exam_code");
                    String date_created = Objects.requireNonNull(documentSnapshot1).getString("date_created");

                    if(documentSnapshot1.exists()){

                        exam_code_text.setText(exam_code);
                        date_created_text.setText(date_created);
                        Q1_txt.setText(documentSnapshot1.getString(("ques1")));
                        A1_txt.setText(documentSnapshot1.getString(("ans1")));
                        Q2_txt.setText(documentSnapshot1.getString(("ques2")));
                        A2_txt.setText(documentSnapshot1.getString(("ans2")));
                        Q3_txt.setText(documentSnapshot1.getString(("ques3")));
                        A3_txt.setText(documentSnapshot1.getString(("ans3")));
                        Q4_txt.setText(documentSnapshot1.getString(("ques4")));
                        A4_txt.setText(documentSnapshot1.getString(("ans4")));
                        Q5_txt.setText(documentSnapshot1.getString(("ques5")));
                        A5_txt.setText(documentSnapshot1.getString(("ans5")));
                        Q6_txt.setText(documentSnapshot1.getString(("ques6")));
                        A6_txt.setText(documentSnapshot1.getString(("ans6")));
                        Q7_txt.setText(documentSnapshot1.getString(("ques7")));
                        A7_txt.setText(documentSnapshot1.getString(("ans7")));
                        Q8_txt.setText(documentSnapshot1.getString(("ques8")));
                        A8_txt.setText(documentSnapshot1.getString(("ans8")));
                        Q9_txt.setText(documentSnapshot1.getString(("ques9")));
                        A9_txt.setText(documentSnapshot1.getString(("ans9")));
                        Q10_txt.setText(documentSnapshot1.getString(("ques10")));
                        A10_txt.setText(documentSnapshot1.getString(("ans10")));
                        Q11_txt.setText(documentSnapshot1.getString(("ques11")));
                        A11_txt.setText(documentSnapshot1.getString(("ans11")));
                        Q12_txt.setText(documentSnapshot1.getString(("ques12")));
                        A12_txt.setText(documentSnapshot1.getString(("ans12")));
                        Q13_txt.setText(documentSnapshot1.getString(("ques13")));
                        A13_txt.setText(documentSnapshot1.getString(("ans13")));
                        Q14_txt.setText(documentSnapshot1.getString(("ques14")));
                        A14_txt.setText(documentSnapshot1.getString(("ans14")));
                        Q15_txt.setText(documentSnapshot1.getString(("ques15")));
                        A15_txt.setText(documentSnapshot1.getString(("ans15")));
                        Q16_txt.setText(documentSnapshot1.getString(("ques16")));
                        A16_txt.setText(documentSnapshot1.getString(("ans16")));
                        Q17_txt.setText(documentSnapshot1.getString(("ques17")));
                        A17_txt.setText(documentSnapshot1.getString(("ans17")));
                        Q18_txt.setText(documentSnapshot1.getString(("ques18")));
                        A18_txt.setText(documentSnapshot1.getString(("ans18")));
                        Q19_txt.setText(documentSnapshot1.getString(("ques19")));
                        A19_txt.setText(documentSnapshot1.getString(("ans19")));
                        Q20_txt.setText(documentSnapshot1.getString(("ques20")));
                        A20_txt.setText(documentSnapshot1.getString(("ans20")));
                        Instruction.setText(documentSnapshot1.getString(("instruction")));
                        code_txt.setText(documentSnapshot1.getString(("exam_code")));
                        create_exam_button.setEnabled(false);
                        create_exam_button.setBackgroundColor(getColor(R.color.Light_gray));


                    }
                    else {

                        Log.d("tag","Document do not exist");
                        delete_exam_button.setEnabled(false);
                        delete_exam_button.setBackgroundColor(getColor(R.color.Light_gray));
                        update_exam_button.setEnabled(false);
                        update_exam_button.setBackgroundColor(getColor(R.color.Light_gray));

                    }
                });



            }
            else {

                Log.d("tag","Document do not exist");
            }
        });

    }


}