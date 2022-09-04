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

public class prof_create_exam extends AppCompatActivity {

    public static final String TAG = "TAG";
    Button create_exam_button;
    Button delete_exam_button;
    Button update_exam_button;
    TextView exam_code_text;
    TextView date_created_text;

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
    TextInputEditText Q21_txt;
    TextInputEditText A21_txt;
    TextInputEditText Q22_txt;
    TextInputEditText A22_txt;
    TextInputEditText Q23_txt;
    TextInputEditText A23_txt;
    TextInputEditText Q24_txt;
    TextInputEditText A24_txt;
    TextInputEditText Q25_txt;
    TextInputEditText A25_txt;
    TextInputEditText Q26_txt;
    TextInputEditText A26_txt;
    TextInputEditText Q27_txt;
    TextInputEditText A27_txt;
    TextInputEditText Q28_txt;
    TextInputEditText A28_txt;
    TextInputEditText Q29_txt;
    TextInputEditText A29_txt;
    TextInputEditText Q30_txt;
    TextInputEditText A30_txt;
    TextInputEditText Q31_txt;
    TextInputEditText A31_txt;
    TextInputEditText Q32_txt;
    TextInputEditText A32_txt;
    TextInputEditText Q33_txt;
    TextInputEditText A33_txt;
    TextInputEditText Q34_txt;
    TextInputEditText A34_txt;
    TextInputEditText Q35_txt;
    TextInputEditText A35_txt;
    TextInputEditText Q36_txt;
    TextInputEditText A36_txt;
    TextInputEditText Q37_txt;
    TextInputEditText A37_txt;
    TextInputEditText Q38_txt;
    TextInputEditText A38_txt;
    TextInputEditText Q39_txt;
    TextInputEditText A39_txt;
    TextInputEditText Q40_txt;
    TextInputEditText A40_txt;
    TextInputEditText code_txt;
    EditText Instruction;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_create_exam);

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
        Q21_txt = findViewById(R.id.Q21_txt);
        A21_txt = findViewById(R.id.A21_txt);
        Q22_txt = findViewById(R.id.Q22_txt);
        A22_txt = findViewById(R.id.A22_txt);
        Q23_txt = findViewById(R.id.Q23_txt);
        A23_txt = findViewById(R.id.A23_txt);
        Q24_txt = findViewById(R.id.Q24_txt);
        A24_txt = findViewById(R.id.A24_txt);
        Q25_txt = findViewById(R.id.Q25_txt);
        A25_txt = findViewById(R.id.A25_txt);
        Q26_txt = findViewById(R.id.Q26_txt);
        A26_txt = findViewById(R.id.A26_txt);
        Q27_txt = findViewById(R.id.Q27_txt);
        A27_txt = findViewById(R.id.A27_txt);
        Q28_txt = findViewById(R.id.Q28_txt);
        A28_txt = findViewById(R.id.A28_txt);
        Q29_txt = findViewById(R.id.Q29_txt);
        A29_txt = findViewById(R.id.A29_txt);
        Q30_txt = findViewById(R.id.Q30_txt);
        A30_txt = findViewById(R.id.A30_txt);
        Q31_txt = findViewById(R.id.Q31_txt);
        A31_txt = findViewById(R.id.A31_txt);
        Q32_txt = findViewById(R.id.Q32_txt);
        A32_txt = findViewById(R.id.A32_txt);
        Q33_txt = findViewById(R.id.Q33_txt);
        A33_txt = findViewById(R.id.A33_txt);
        Q34_txt = findViewById(R.id.Q34_txt);
        A34_txt = findViewById(R.id.A34_txt);
        Q35_txt = findViewById(R.id.Q35_txt);
        A35_txt = findViewById(R.id.A35_txt);
        Q36_txt = findViewById(R.id.Q36_txt);
        A36_txt = findViewById(R.id.A36_txt);
        Q37_txt = findViewById(R.id.Q37_txt);
        A37_txt = findViewById(R.id.A37_txt);
        Q38_txt = findViewById(R.id.Q38_txt);
        A38_txt = findViewById(R.id.A38_txt);
        Q39_txt = findViewById(R.id.Q39_txt);
        A39_txt = findViewById(R.id.A39_txt);
        Q40_txt = findViewById(R.id.Q40_txt);
        A40_txt = findViewById(R.id.A40_txt);
        code_txt = findViewById(R.id.code_txt);




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
                fStore.collection("exams_40").document(user.getUid()).delete()
                                .addOnSuccessListener(unused -> {


                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection(subject_code + "_examID_40")
                                            .get()
                                            .addOnCompleteListener(task -> {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                                        fStore.collection("stud_exam_info_40").document(document.getId()).collection(subject_code).document(exam_code_text.getText().toString()).delete();
                                                        fStore.collection(exam_code_text.getText().toString()).document(document.getId()).delete();
                                                    }

                                                    //out of looping above

                                                    Toast.makeText(getApplicationContext(), "Exam Removed succesfully!",Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(prof_create_exam.this, prof_home.class));

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
        String q21 = Objects.requireNonNull(Q21_txt.getText()).toString();
        String a21 = Objects.requireNonNull(A21_txt.getText()).toString();
        String q22 = Objects.requireNonNull(Q22_txt.getText()).toString();
        String a22 = Objects.requireNonNull(A22_txt.getText()).toString();
        String q23 = Objects.requireNonNull(Q23_txt.getText()).toString();
        String a23 = Objects.requireNonNull(A23_txt.getText()).toString();
        String q24 = Objects.requireNonNull(Q24_txt.getText()).toString();
        String a24 = Objects.requireNonNull(A24_txt.getText()).toString();
        String q25 = Objects.requireNonNull(Q25_txt.getText()).toString();
        String a25 = Objects.requireNonNull(A25_txt.getText()).toString();
        String q26 = Objects.requireNonNull(Q26_txt.getText()).toString();
        String a26 = Objects.requireNonNull(A26_txt.getText()).toString();
        String q27 = Objects.requireNonNull(Q27_txt.getText()).toString();
        String a27 = Objects.requireNonNull(A27_txt.getText()).toString();
        String q28 = Objects.requireNonNull(Q28_txt.getText()).toString();
        String a28 = Objects.requireNonNull(A28_txt.getText()).toString();
        String q29 = Objects.requireNonNull(Q29_txt.getText()).toString();
        String a29 = Objects.requireNonNull(A29_txt.getText()).toString();
        String q30 = Objects.requireNonNull(Q30_txt.getText()).toString();
        String a30 = Objects.requireNonNull(A30_txt.getText()).toString();
        String q31 = Objects.requireNonNull(Q31_txt.getText()).toString();
        String a31 = Objects.requireNonNull(A31_txt.getText()).toString();
        String q32 = Objects.requireNonNull(Q32_txt.getText()).toString();
        String a32 = Objects.requireNonNull(A32_txt.getText()).toString();
        String q33 = Objects.requireNonNull(Q33_txt.getText()).toString();
        String a33 = Objects.requireNonNull(A33_txt.getText()).toString();
        String q34 = Objects.requireNonNull(Q34_txt.getText()).toString();
        String a34 = Objects.requireNonNull(A34_txt.getText()).toString();
        String q35 = Objects.requireNonNull(Q35_txt.getText()).toString();
        String a35 = Objects.requireNonNull(A35_txt.getText()).toString();
        String q36 = Objects.requireNonNull(Q36_txt.getText()).toString();
        String a36 = Objects.requireNonNull(A36_txt.getText()).toString();
        String q37 = Objects.requireNonNull(Q37_txt.getText()).toString();
        String a37 = Objects.requireNonNull(A37_txt.getText()).toString();
        String q38 = Objects.requireNonNull(Q38_txt.getText()).toString();
        String a38 = Objects.requireNonNull(A38_txt.getText()).toString();
        String q39 = Objects.requireNonNull(Q39_txt.getText()).toString();
        String a39 = Objects.requireNonNull(A39_txt.getText()).toString();
        String q40 = Objects.requireNonNull(Q40_txt.getText()).toString();
        String a40 = Objects.requireNonNull(A40_txt.getText()).toString();
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
                || TextUtils.isEmpty(q21) || TextUtils.isEmpty(a21)
                || TextUtils.isEmpty(q22) || TextUtils.isEmpty(a22)
                || TextUtils.isEmpty(q23) || TextUtils.isEmpty(a23)
                || TextUtils.isEmpty(q24) || TextUtils.isEmpty(a24)
                || TextUtils.isEmpty(q25) || TextUtils.isEmpty(a25)
                || TextUtils.isEmpty(q26) || TextUtils.isEmpty(a26)
                || TextUtils.isEmpty(q27) || TextUtils.isEmpty(a27)
                || TextUtils.isEmpty(q28) || TextUtils.isEmpty(a28)
                || TextUtils.isEmpty(q29) || TextUtils.isEmpty(a29)
                || TextUtils.isEmpty(q30) || TextUtils.isEmpty(a30)
                || TextUtils.isEmpty(q31) || TextUtils.isEmpty(a31)
                || TextUtils.isEmpty(q32) || TextUtils.isEmpty(a32)
                || TextUtils.isEmpty(q33) || TextUtils.isEmpty(a33)
                || TextUtils.isEmpty(q34) || TextUtils.isEmpty(a34)
                || TextUtils.isEmpty(q35) || TextUtils.isEmpty(a35)
                || TextUtils.isEmpty(q36) || TextUtils.isEmpty(a36)
                || TextUtils.isEmpty(q37) || TextUtils.isEmpty(a37)
                || TextUtils.isEmpty(q38) || TextUtils.isEmpty(a38)
                || TextUtils.isEmpty(q39) || TextUtils.isEmpty(a39)
                || TextUtils.isEmpty(q40) || TextUtils.isEmpty(a40)
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

                    DocumentReference documentReference2 = fStore.collection("exams_40").document(user.getUid());
                    documentReference2.addSnapshotListener(this,(documentSnapshot3,g)->{

                        String exam_code1 = Objects.requireNonNull(documentSnapshot3).getString("exam_code");

                        if(documentSnapshot3.exists()){
                            if(exam_code.equals(exam_code1)){

                                //
                                assert subject_code != null;
                                DocumentReference documentReference1 = fStore.collection("exams_40").document(user.getUid());
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
                                exam.put("ques21", q21);
                                exam.put("ans21", a21);
                                exam.put("ques22", q22);
                                exam.put("ans22", a22);
                                exam.put("ques23", q23);
                                exam.put("ans23", a23);
                                exam.put("ques24", q24);
                                exam.put("ans24", a24);
                                exam.put("ques25", q25);
                                exam.put("ans25", a25);
                                exam.put("ques26", q26);
                                exam.put("ans26", a26);
                                exam.put("ques27", q27);
                                exam.put("ans27", a27);
                                exam.put("ques28", q28);
                                exam.put("ans28", a28);
                                exam.put("ques29", q29);
                                exam.put("ans29", a29);
                                exam.put("ques30", q30);
                                exam.put("ans30", a30);
                                exam.put("ques31", q31);
                                exam.put("ans31", a31);
                                exam.put("ques32", q32);
                                exam.put("ans32", a32);
                                exam.put("ques33", q33);
                                exam.put("ans33", a33);
                                exam.put("ques34", q34);
                                exam.put("ans34", a34);
                                exam.put("ques35", q35);
                                exam.put("ans35", a35);
                                exam.put("ques36", q36);
                                exam.put("ans36", a36);
                                exam.put("ques37", q37);
                                exam.put("ans37", a37);
                                exam.put("ques38", q38);
                                exam.put("ans38", a38);
                                exam.put("ques39", q39);
                                exam.put("ans39", a39);
                                exam.put("ques40", q40);
                                exam.put("ans40", a40);
                                exam.put("user_type", "Professor");
                                exam.put("instructor", fname + " " + Lname);
                                exam.put("email", email);
                                exam.put("date_created", date);
                                exam.put("subject", subject_code);
                                exam.put("instruction", instruction);
                                documentReference1.set(exam).addOnSuccessListener(unused -> Log.d(TAG, "Exam added succesfully"));
                                Toast.makeText(getApplicationContext(), "Exam updated succesfully!",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(prof_create_exam.this, prof_home.class));

                            }
                            else{

                                FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                                db1.collection("exams_20").whereEqualTo("exam_code",exam_code)
                                        .get().addOnSuccessListener(queryDocumentSnapshots1 -> {

                                            //checking if the document exist
                                            if(!queryDocumentSnapshots1.isEmpty()) {

                                                code_txt.setError("Exam code already exist. Please try different code!");
                                                code_txt.requestFocus();

                                            }
                                            else {

                                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                                db.collection("exams_40").whereEqualTo("exam_code",exam_code)
                                                        .get().addOnSuccessListener(queryDocumentSnapshots -> {

                                                            if(!queryDocumentSnapshots.isEmpty()){
                                                                code_txt.setError("Exam code already exist. Please try different code!");
                                                                code_txt.requestFocus();
                                                            }
                                                            else {
                                                                //
                                                                assert subject_code != null;
                                                                DocumentReference documentReference1 = fStore.collection("exams_40").document(user.getUid());
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
                                                                exam.put("ques21", q21);
                                                                exam.put("ans21", a21);
                                                                exam.put("ques22", q22);
                                                                exam.put("ans22", a22);
                                                                exam.put("ques23", q23);
                                                                exam.put("ans23", a23);
                                                                exam.put("ques24", q24);
                                                                exam.put("ans24", a24);
                                                                exam.put("ques25", q25);
                                                                exam.put("ans25", a25);
                                                                exam.put("ques26", q26);
                                                                exam.put("ans26", a26);
                                                                exam.put("ques27", q27);
                                                                exam.put("ans27", a27);
                                                                exam.put("ques28", q28);
                                                                exam.put("ans28", a28);
                                                                exam.put("ques29", q29);
                                                                exam.put("ans29", a29);
                                                                exam.put("ques30", q30);
                                                                exam.put("ans30", a30);
                                                                exam.put("ques31", q31);
                                                                exam.put("ans31", a31);
                                                                exam.put("ques32", q32);
                                                                exam.put("ans32", a32);
                                                                exam.put("ques33", q33);
                                                                exam.put("ans33", a33);
                                                                exam.put("ques34", q34);
                                                                exam.put("ans34", a34);
                                                                exam.put("ques35", q35);
                                                                exam.put("ans35", a35);
                                                                exam.put("ques36", q36);
                                                                exam.put("ans36", a36);
                                                                exam.put("ques37", q37);
                                                                exam.put("ans37", a37);
                                                                exam.put("ques38", q38);
                                                                exam.put("ans38", a38);
                                                                exam.put("ques39", q39);
                                                                exam.put("ans39", a39);
                                                                exam.put("ques40", q40);
                                                                exam.put("ans40", a40);
                                                                exam.put("user_type", "Professor");
                                                                exam.put("instructor", fname + " " + Lname);
                                                                exam.put("email", email);
                                                                exam.put("date_created", date);
                                                                exam.put("subject", subject_code);
                                                                exam.put("instruction", instruction);
                                                                documentReference1.set(exam).addOnSuccessListener(unused -> Log.d(TAG, "Exam added succesfully"));
                                                                Toast.makeText(getApplicationContext(), "Exam updated succesfully!",Toast.LENGTH_LONG).show();
                                                                startActivity(new Intent(prof_create_exam.this, prof_home.class));
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
        String q21 = Objects.requireNonNull(Q21_txt.getText()).toString();
        String a21 = Objects.requireNonNull(A21_txt.getText()).toString();
        String q22 = Objects.requireNonNull(Q22_txt.getText()).toString();
        String a22 = Objects.requireNonNull(A22_txt.getText()).toString();
        String q23 = Objects.requireNonNull(Q23_txt.getText()).toString();
        String a23 = Objects.requireNonNull(A23_txt.getText()).toString();
        String q24 = Objects.requireNonNull(Q24_txt.getText()).toString();
        String a24 = Objects.requireNonNull(A24_txt.getText()).toString();
        String q25 = Objects.requireNonNull(Q25_txt.getText()).toString();
        String a25 = Objects.requireNonNull(A25_txt.getText()).toString();
        String q26 = Objects.requireNonNull(Q26_txt.getText()).toString();
        String a26 = Objects.requireNonNull(A26_txt.getText()).toString();
        String q27 = Objects.requireNonNull(Q27_txt.getText()).toString();
        String a27 = Objects.requireNonNull(A27_txt.getText()).toString();
        String q28 = Objects.requireNonNull(Q28_txt.getText()).toString();
        String a28 = Objects.requireNonNull(A28_txt.getText()).toString();
        String q29 = Objects.requireNonNull(Q29_txt.getText()).toString();
        String a29 = Objects.requireNonNull(A29_txt.getText()).toString();
        String q30 = Objects.requireNonNull(Q30_txt.getText()).toString();
        String a30 = Objects.requireNonNull(A30_txt.getText()).toString();
        String q31 = Objects.requireNonNull(Q31_txt.getText()).toString();
        String a31 = Objects.requireNonNull(A31_txt.getText()).toString();
        String q32 = Objects.requireNonNull(Q32_txt.getText()).toString();
        String a32 = Objects.requireNonNull(A32_txt.getText()).toString();
        String q33 = Objects.requireNonNull(Q33_txt.getText()).toString();
        String a33 = Objects.requireNonNull(A33_txt.getText()).toString();
        String q34 = Objects.requireNonNull(Q34_txt.getText()).toString();
        String a34 = Objects.requireNonNull(A34_txt.getText()).toString();
        String q35 = Objects.requireNonNull(Q35_txt.getText()).toString();
        String a35 = Objects.requireNonNull(A35_txt.getText()).toString();
        String q36 = Objects.requireNonNull(Q36_txt.getText()).toString();
        String a36 = Objects.requireNonNull(A36_txt.getText()).toString();
        String q37 = Objects.requireNonNull(Q37_txt.getText()).toString();
        String a37 = Objects.requireNonNull(A37_txt.getText()).toString();
        String q38 = Objects.requireNonNull(Q38_txt.getText()).toString();
        String a38 = Objects.requireNonNull(A38_txt.getText()).toString();
        String q39 = Objects.requireNonNull(Q39_txt.getText()).toString();
        String a39 = Objects.requireNonNull(A39_txt.getText()).toString();
        String q40 = Objects.requireNonNull(Q40_txt.getText()).toString();
        String a40 = Objects.requireNonNull(A40_txt.getText()).toString();
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
                || TextUtils.isEmpty(q21) || TextUtils.isEmpty(a21)
                || TextUtils.isEmpty(q22) || TextUtils.isEmpty(a22)
                || TextUtils.isEmpty(q23) || TextUtils.isEmpty(a23)
                || TextUtils.isEmpty(q24) || TextUtils.isEmpty(a24)
                || TextUtils.isEmpty(q25) || TextUtils.isEmpty(a25)
                || TextUtils.isEmpty(q26) || TextUtils.isEmpty(a26)
                || TextUtils.isEmpty(q27) || TextUtils.isEmpty(a27)
                || TextUtils.isEmpty(q28) || TextUtils.isEmpty(a28)
                || TextUtils.isEmpty(q29) || TextUtils.isEmpty(a29)
                || TextUtils.isEmpty(q30) || TextUtils.isEmpty(a30)
                || TextUtils.isEmpty(q31) || TextUtils.isEmpty(a31)
                || TextUtils.isEmpty(q32) || TextUtils.isEmpty(a32)
                || TextUtils.isEmpty(q33) || TextUtils.isEmpty(a33)
                || TextUtils.isEmpty(q34) || TextUtils.isEmpty(a34)
                || TextUtils.isEmpty(q35) || TextUtils.isEmpty(a35)
                || TextUtils.isEmpty(q36) || TextUtils.isEmpty(a36)
                || TextUtils.isEmpty(q37) || TextUtils.isEmpty(a37)
                || TextUtils.isEmpty(q38) || TextUtils.isEmpty(a38)
                || TextUtils.isEmpty(q39) || TextUtils.isEmpty(a39)
                || TextUtils.isEmpty(q40) || TextUtils.isEmpty(a40)
                || TextUtils.isEmpty(exam_code)){

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

            db1.collection("exams_20").whereEqualTo("exam_code",exam_code)
                    .get().addOnSuccessListener(queryDocumentSnapshots1 -> {

                        //checking if the document exist
                        if(!queryDocumentSnapshots1.isEmpty()) {

                            code_txt.setError("Exam code already exist. Please try different code!");
                            code_txt.requestFocus();

                        }
                        else {

                            db.collection("exams_40").whereEqualTo("exam_code",exam_code)
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
                                                    DocumentReference documentReference1 = fStore.collection("exams_40").document(user.getUid());
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
                                                    exam.put("ques21", q21);
                                                    exam.put("ans21", a21);
                                                    exam.put("ques22", q22);
                                                    exam.put("ans22", a22);
                                                    exam.put("ques23", q23);
                                                    exam.put("ans23", a23);
                                                    exam.put("ques24", q24);
                                                    exam.put("ans24", a24);
                                                    exam.put("ques25", q25);
                                                    exam.put("ans25", a25);
                                                    exam.put("ques26", q26);
                                                    exam.put("ans26", a26);
                                                    exam.put("ques27", q27);
                                                    exam.put("ans27", a27);
                                                    exam.put("ques28", q28);
                                                    exam.put("ans28", a28);
                                                    exam.put("ques29", q29);
                                                    exam.put("ans29", a29);
                                                    exam.put("ques30", q30);
                                                    exam.put("ans30", a30);
                                                    exam.put("ques31", q31);
                                                    exam.put("ans31", a31);
                                                    exam.put("ques32", q32);
                                                    exam.put("ans32", a32);
                                                    exam.put("ques33", q33);
                                                    exam.put("ans33", a33);
                                                    exam.put("ques34", q34);
                                                    exam.put("ans34", a34);
                                                    exam.put("ques35", q35);
                                                    exam.put("ans35", a35);
                                                    exam.put("ques36", q36);
                                                    exam.put("ans36", a36);
                                                    exam.put("ques37", q37);
                                                    exam.put("ans37", a37);
                                                    exam.put("ques38", q38);
                                                    exam.put("ans38", a38);
                                                    exam.put("ques39", q39);
                                                    exam.put("ans39", a39);
                                                    exam.put("ques40", q40);
                                                    exam.put("ans40", a40);
                                                    exam.put("user_type", "Professor");
                                                    exam.put("instructor", fname + " " + Lname);
                                                    exam.put("email", email);
                                                    exam.put("date_created", date);
                                                    exam.put("subject", subject_code);
                                                    exam.put("instruction", instruction);
                                                    documentReference1.set(exam).addOnSuccessListener(unused -> Log.d(TAG, "Exam added succesfully"));
                                                    Toast.makeText(getApplicationContext(), "Exam succesfully created!",Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(prof_create_exam.this, prof_home.class));


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
                DocumentReference documentReference1 = fStore.collection("exams_40").document(user.getUid());
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
                        Q21_txt.setText(documentSnapshot1.getString(("ques21")));
                        A21_txt.setText(documentSnapshot1.getString(("ans21")));
                        Q22_txt.setText(documentSnapshot1.getString(("ques22")));
                        A22_txt.setText(documentSnapshot1.getString(("ans22")));
                        Q23_txt.setText(documentSnapshot1.getString(("ques23")));
                        A23_txt.setText(documentSnapshot1.getString(("ans23")));
                        Q24_txt.setText(documentSnapshot1.getString(("ques24")));
                        A24_txt.setText(documentSnapshot1.getString(("ans24")));
                        Q25_txt.setText(documentSnapshot1.getString(("ques25")));
                        A25_txt.setText(documentSnapshot1.getString(("ans25")));
                        Q26_txt.setText(documentSnapshot1.getString(("ques26")));
                        A26_txt.setText(documentSnapshot1.getString(("ans26")));
                        Q27_txt.setText(documentSnapshot1.getString(("ques27")));
                        A27_txt.setText(documentSnapshot1.getString(("ans27")));
                        Q28_txt.setText(documentSnapshot1.getString(("ques28")));
                        A28_txt.setText(documentSnapshot1.getString(("ans28")));
                        Q29_txt.setText(documentSnapshot1.getString(("ques29")));
                        A29_txt.setText(documentSnapshot1.getString(("ans29")));
                        Q30_txt.setText(documentSnapshot1.getString(("ques30")));
                        A30_txt.setText(documentSnapshot1.getString(("ans30")));
                        Q31_txt.setText(documentSnapshot1.getString(("ques31")));
                        A31_txt.setText(documentSnapshot1.getString(("ans31")));
                        Q32_txt.setText(documentSnapshot1.getString(("ques32")));
                        A32_txt.setText(documentSnapshot1.getString(("ans32")));
                        Q33_txt.setText(documentSnapshot1.getString(("ques33")));
                        A33_txt.setText(documentSnapshot1.getString(("ans33")));
                        Q34_txt.setText(documentSnapshot1.getString(("ques34")));
                        A34_txt.setText(documentSnapshot1.getString(("ans34")));
                        Q35_txt.setText(documentSnapshot1.getString(("ques35")));
                        A35_txt.setText(documentSnapshot1.getString(("ans35")));
                        Q36_txt.setText(documentSnapshot1.getString(("ques36")));
                        A36_txt.setText(documentSnapshot1.getString(("ans36")));
                        Q37_txt.setText(documentSnapshot1.getString(("ques37")));
                        A37_txt.setText(documentSnapshot1.getString(("ans37")));
                        Q38_txt.setText(documentSnapshot1.getString(("ques38")));
                        A38_txt.setText(documentSnapshot1.getString(("ans38")));
                        Q39_txt.setText(documentSnapshot1.getString(("ques39")));
                        A39_txt.setText(documentSnapshot1.getString(("ans39")));
                        Q40_txt.setText(documentSnapshot1.getString(("ques40")));
                        A40_txt.setText(documentSnapshot1.getString(("ans40")));
                        code_txt.setText(documentSnapshot1.getString(("exam_code")));
                        Instruction.setText(documentSnapshot1.getString(("instruction")));
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