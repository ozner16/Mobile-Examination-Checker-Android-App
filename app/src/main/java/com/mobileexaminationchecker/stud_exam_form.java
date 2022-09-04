package com.mobileexaminationchecker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class stud_exam_form extends AppCompatActivity {


    public static final String TAG ="tag";
    String text_code;
    TextView code_textview;
    Button stud_exam_form_back_btn;
    Button submit_btn;

    TextView Q1_txt;
    EditText A1_txt;
    TextView Q2_txt;
    EditText A2_txt;
    TextView Q3_txt;
    EditText A3_txt;
    TextView Q4_txt;
    EditText A4_txt;
    TextView Q5_txt;
    EditText A5_txt;
    TextView Q6_txt;
    EditText A6_txt;
    TextView Q7_txt;
    EditText A7_txt;
    TextView Q8_txt;
    EditText A8_txt;
    TextView Q9_txt;
    EditText A9_txt;
    TextView Q10_txt;
    EditText A10_txt;
    TextView Q11_txt;
    EditText A11_txt;
    TextView Q12_txt;
    EditText A12_txt;
    TextView Q13_txt;
    EditText A13_txt;
    TextView Q14_txt;
    EditText A14_txt;
    TextView Q15_txt;
    EditText A15_txt;
    TextView Q16_txt;
    EditText A16_txt;
    TextView Q17_txt;
    EditText A17_txt;
    TextView Q18_txt;
    EditText A18_txt;
    TextView Q19_txt;
    EditText A19_txt;
    TextView Q20_txt;
    EditText A20_txt;
    TextView Q21_txt;
    EditText A21_txt;
    TextView Q22_txt;
    EditText A22_txt;
    TextView Q23_txt;
    EditText A23_txt;
    TextView Q24_txt;
    EditText A24_txt;
    TextView Q25_txt;
    EditText A25_txt;
    TextView Q26_txt;
    EditText A26_txt;
    TextView Q27_txt;
    EditText A27_txt;
    TextView Q28_txt;
    EditText A28_txt;
    TextView Q29_txt;
    EditText A29_txt;
    TextView Q30_txt;
    EditText A30_txt;
    TextView Q31_txt;
    EditText A31_txt;
    TextView Q32_txt;
    EditText A32_txt;
    TextView Q33_txt;
    EditText A33_txt;
    TextView Q34_txt;
    EditText A34_txt;
    TextView Q35_txt;
    EditText A35_txt;
    TextView Q36_txt;
    EditText A36_txt;
    TextView Q37_txt;
    EditText A37_txt;
    TextView Q38_txt;
    EditText A38_txt;
    TextView Q39_txt;
    EditText A39_txt;
    TextView Q40_txt;
    EditText A40_txt;
    TextView exam_form_subject;
    TextView instr;

    //current date
    DateFormat df;
    String date;

    //firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageReference;


    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stud_exam_form);

        //passing text of exam_code
        code_textview = findViewById(R.id.code_textview);
        text_code = getIntent().getExtras().getString("Value");
        code_textview.setText(text_code);
        stud_exam_form_back_btn = findViewById(R.id.stud_exam_form_back_btn);
        submit_btn = findViewById(R.id.stud_exam_form_submit_btn);

        //current date
        df = new SimpleDateFormat("MMM d, yyyy , hh:mm a");
        date = df.format(Calendar.getInstance().getTime());

        //
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
        exam_form_subject = findViewById(R.id.exam_form_subject);
        instr = findViewById(R.id.instr);

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //set text to textview
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("exams_40").whereEqualTo("exam_code",code_textview.getText().toString())
                .get().addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()){
                        for(QueryDocumentSnapshot document : task1.getResult()){

                            String subject = document.getString("subject");

                            if(document.exists()){

                                Q1_txt.setText(document.getString(("ques1")));
                                Q2_txt.setText(document.getString(("ques2")));
                                Q3_txt.setText(document.getString(("ques3")));
                                Q4_txt.setText(document.getString(("ques4")));
                                Q5_txt.setText(document.getString(("ques5")));
                                Q6_txt.setText(document.getString(("ques6")));
                                Q7_txt.setText(document.getString(("ques7")));
                                Q8_txt.setText(document.getString(("ques8")));
                                Q9_txt.setText(document.getString(("ques9")));
                                Q10_txt.setText(document.getString(("ques10")));
                                Q11_txt.setText(document.getString(("ques11")));
                                Q12_txt.setText(document.getString(("ques12")));
                                Q13_txt.setText(document.getString(("ques13")));
                                Q14_txt.setText(document.getString(("ques14")));
                                Q15_txt.setText(document.getString(("ques15")));
                                Q16_txt.setText(document.getString(("ques16")));
                                Q17_txt.setText(document.getString(("ques17")));
                                Q18_txt.setText(document.getString(("ques18")));
                                Q19_txt.setText(document.getString(("ques19")));
                                Q20_txt.setText(document.getString(("ques20")));
                                Q21_txt.setText(document.getString(("ques21")));
                                Q22_txt.setText(document.getString(("ques22")));
                                Q23_txt.setText(document.getString(("ques23")));
                                Q24_txt.setText(document.getString(("ques24")));
                                Q25_txt.setText(document.getString(("ques25")));
                                Q26_txt.setText(document.getString(("ques26")));
                                Q27_txt.setText(document.getString(("ques27")));
                                Q28_txt.setText(document.getString(("ques28")));
                                Q29_txt.setText(document.getString(("ques29")));
                                Q30_txt.setText(document.getString(("ques30")));
                                Q31_txt.setText(document.getString(("ques31")));
                                Q32_txt.setText(document.getString(("ques32")));
                                Q33_txt.setText(document.getString(("ques33")));
                                Q34_txt.setText(document.getString(("ques34")));
                                Q35_txt.setText(document.getString(("ques35")));
                                Q36_txt.setText(document.getString(("ques36")));
                                Q37_txt.setText(document.getString(("ques37")));
                                Q38_txt.setText(document.getString(("ques38")));
                                Q39_txt.setText(document.getString(("ques39")));
                                Q40_txt.setText(document.getString(("ques40")));
                                exam_form_subject.setText(subject + " Exam");
                                instr.setText(document.getString(("instruction")));



                            }
                        }
                    }
                    else {
                        //This Toast will be displayed only when you'll have an error while getting documents.
                        Toast.makeText(this, Objects.requireNonNull(task1.getException()).toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        //button click
        stud_exam_form_back_btn.setOnClickListener(view -> onBackPressed());
        submit_btn.setOnClickListener(view -> submit_checkAnswer());

    }

    @SuppressLint("SetTextI18n")
    private void submit_checkAnswer(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("exams_40").whereEqualTo("exam_code",code_textview.getText().toString())
                .get().addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()){
                        for(QueryDocumentSnapshot document : task1.getResult()){

                            String ans1 = document.getString("ans1");
                            String ans2 = document.getString("ans2");
                            String ans3 = document.getString("ans3");
                            String ans4 = document.getString("ans4");
                            String ans5 = document.getString("ans5");
                            String ans6 = document.getString("ans6");
                            String ans7 = document.getString("ans7");
                            String ans8 = document.getString("ans8");
                            String ans9 = document.getString("ans9");
                            String ans10 = document.getString("ans10");
                            String ans11 = document.getString("ans11");
                            String ans12 = document.getString("ans12");
                            String ans13 = document.getString("ans13");
                            String ans14 = document.getString("ans14");
                            String ans15 = document.getString("ans15");
                            String ans16 = document.getString("ans16");
                            String ans17 = document.getString("ans17");
                            String ans18 = document.getString("ans18");
                            String ans19 = document.getString("ans19");
                            String ans20 = document.getString("ans20");
                            String ans21 = document.getString("ans21");
                            String ans22 = document.getString("ans22");
                            String ans23 = document.getString("ans23");
                            String ans24 = document.getString("ans24");
                            String ans25 = document.getString("ans25");
                            String ans26 = document.getString("ans26");
                            String ans27 = document.getString("ans27");
                            String ans28 = document.getString("ans28");
                            String ans29 = document.getString("ans29");
                            String ans30 = document.getString("ans30");
                            String ans31 = document.getString("ans31");
                            String ans32 = document.getString("ans32");
                            String ans33 = document.getString("ans33");
                            String ans34 = document.getString("ans34");
                            String ans35 = document.getString("ans35");
                            String ans36 = document.getString("ans36");
                            String ans37 = document.getString("ans37");
                            String ans38 = document.getString("ans38");
                            String ans39 = document.getString("ans39");
                            String ans40 = document.getString("ans40");

                            String subject = document.getString("subject");
                            String exam_date_created = document.getString("date_created");
                            String instructor = document.getString("instructor");
                            int score_40 = 0;

                            if(document.exists()){

                                if(A1_txt.getText().toString().trim().equals(ans1)){
                                    score_40++;

                                }

                                if(A2_txt.getText().toString().trim().equals(ans2)){
                                    score_40++;

                                }

                                if(A3_txt.getText().toString().trim().equals(ans3)){
                                    score_40++;
                                }

                                if(A4_txt.getText().toString().trim().equals(ans4)){
                                    score_40++;
                                }
                                if(A5_txt.getText().toString().trim().equals(ans5)){
                                    score_40++;
                                }
                                if(A6_txt.getText().toString().trim().equals(ans6)){
                                    score_40++;
                                }
                                if(A7_txt.getText().toString().trim().equals(ans7)){
                                    score_40++;
                                }
                                if(A8_txt.getText().toString().trim().equals(ans8)){
                                    score_40++;
                                }
                                if(A9_txt.getText().toString().trim().equals(ans9)){
                                    score_40++;
                                }
                                if(A10_txt.getText().toString().trim().equals(ans10)){
                                    score_40++;
                                }
                                if(A11_txt.getText().toString().trim().equals(ans11)){
                                    score_40++;
                                }
                                if(A12_txt.getText().toString().trim().equals(ans12)){
                                    score_40++;
                                }
                                if(A13_txt.getText().toString().trim().equals(ans13)){
                                    score_40++;
                                }
                                if(A14_txt.getText().toString().trim().equals(ans14)){
                                    score_40++;
                                }
                                if(A15_txt.getText().toString().trim().equals(ans15)){
                                    score_40++;
                                }
                                if(A16_txt.getText().toString().trim().equals(ans16)){
                                    score_40++;
                                }
                                if(A17_txt.getText().toString().trim().equals(ans17)){
                                    score_40++;
                                }
                                if(A18_txt.getText().toString().trim().equals(ans18)){
                                    score_40++;
                                }
                                if(A19_txt.getText().toString().trim().equals(ans19)){
                                    score_40++;
                                }
                                if(A20_txt.getText().toString().trim().equals(ans20)){
                                    score_40++;
                                }
                                if(A21_txt.getText().toString().trim().equals(ans21)){
                                    score_40++;
                                }
                                if(A22_txt.getText().toString().trim().equals(ans22)){
                                    score_40++;
                                }
                                if(A23_txt.getText().toString().trim().equals(ans23)){
                                    score_40++;
                                }
                                if(A24_txt.getText().toString().trim().equals(ans24)){
                                    score_40++;
                                }
                                if(A25_txt.getText().toString().trim().equals(ans25)){
                                    score_40++;
                                }
                                if(A26_txt.getText().toString().trim().equals(ans26)){
                                    score_40++;
                                }
                                if(A27_txt.getText().toString().trim().equals(ans27)){
                                    score_40++;
                                }
                                if(A28_txt.getText().toString().trim().equals(ans28)){
                                    score_40++;
                                }
                                if(A29_txt.getText().toString().trim().equals(ans29)){
                                    score_40++;
                                }
                                if(A30_txt.getText().toString().trim().equals(ans30)){
                                    score_40++;
                                }
                                if(A31_txt.getText().toString().trim().equals(ans31)){
                                    score_40++;
                                }
                                if(A32_txt.getText().toString().trim().equals(ans32)){
                                    score_40++;
                                }
                                if(A33_txt.getText().toString().trim().equals(ans33)){
                                    score_40++;
                                }
                                if(A34_txt.getText().toString().trim().equals(ans34)){
                                    score_40++;
                                }
                                if(A35_txt.getText().toString().trim().equals(ans35)){
                                    score_40++;
                                }
                                if(A36_txt.getText().toString().trim().equals(ans36)){
                                    score_40++;
                                }
                                if(A37_txt.getText().toString().trim().equals(ans37)){
                                    score_40++;
                                }
                                if(A38_txt.getText().toString().trim().equals(ans38)){
                                    score_40++;
                                }
                                if(A39_txt.getText().toString().trim().equals(ans39)){
                                    score_40++;
                                }
                                if(A40_txt.getText().toString().trim().equals(ans40)){
                                    score_40++;
                                }

                                //set text to textviews
                                DocumentReference documentReference = fStore.collection("user_students").document(user.getUid());
                                int finalScore_2 = score_40;
                                documentReference.addSnapshotListener(this,(documentSnapshot, e)->{

                                    String fname = Objects.requireNonNull(documentSnapshot).getString("first_name");
                                    String Lname = documentSnapshot.getString("last_name");
                                    String course = documentSnapshot.getString("course");
                                    String year_lvl = documentSnapshot.getString("year_lvl");
                                    String section = documentSnapshot.getString("section");
                                    String exam_code = code_textview.getText().toString();

                                    if(documentSnapshot.exists()){

                                        assert subject != null;
                                        DocumentReference documentReference1 = fStore.collection("stud_exam_info_40").document(user.getUid()).collection(subject).document(exam_code);
                                        Map<String,Object> exam = new HashMap<>();
                                        exam.put("score", finalScore_2);
                                        exam.put("no_of_items", 40);
                                        exam.put("exam_code", code_textview.getText().toString());
                                        exam.put("subject", subject);
                                        exam.put("date_submitted", date);
                                        exam.put("student_name", fname + " " + Lname);
                                        exam.put("exam_date_created", exam_date_created);
                                        exam.put("instructor", instructor);
                                        documentReference1.set(exam).addOnSuccessListener(unused -> Log.d(TAG, "Succesfull!"));

                                        //another collection for id retrieving
                                        DocumentReference documentReference3 = fStore.collection(subject + "_examID_40").document(user.getUid());
                                        Map<String,Object> exam3 = new HashMap<>();
                                        exam3.put("student_name", fname + " " + Lname);
                                        documentReference3.set(exam3).addOnSuccessListener(unused -> Log.d(TAG, "Succesfull!"));

                                        //3rd collection for prof exam records retrieving
                                        DocumentReference documentReference4 = fStore.collection(exam_code).document(user.getUid());
                                        Map<String,Object> exam4 = new HashMap<>();
                                        exam4.put("student_name", fname + " " + Lname);
                                        exam4.put("course_yr_sec", course + "/" + year_lvl + "/" + section);
                                        exam4.put("score", finalScore_2);
                                        exam4.put("exam_date_created", exam_date_created);
                                        exam4.put("date_submitted", date);
                                        exam.put("no_of_items", "40");
                                        documentReference4.set(exam4).addOnSuccessListener(unused -> Log.d(TAG, "Succesfull!"));

                                        //alert dialog
                                        String score1 = Integer.toString(finalScore_2);
                                        AlertDialog dialog;

                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        //Inflate the prof_exam_dialog view
                                        @SuppressLint("InflateParams") View view10 = getLayoutInflater().inflate(R.layout.exam_form_dialog,null);
                                        TextView tv_score = view10.findViewById(R.id.tv_score);
                                        TextView tv_message = view10.findViewById(R.id.tv_message);
                                        Button ok_btn = view10.findViewById(R.id.okay_btn);

                                        if(finalScore_2 < 20){

                                            tv_score.setText(score1 + " out of 40");
                                            tv_message.setText("Please study harder!");
                                        }
                                        else if(finalScore_2 == 20){
                                            tv_score.setText(score1 + " out of 40");
                                            tv_message.setText("Not bad!");
                                        }
                                        else {
                                            tv_score.setText(score1 + " out of 40");
                                            tv_message.setText("Keep it up!");
                                        }

                                        ok_btn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), stud_home.class)));

                                        //set this view to dialog
                                        builder.setView(view10);
                                        //create dialog now
                                        dialog = builder.create();

                                        dialog.show();
                                        dialog.setCancelable(false);

                                    }
                                    else {

                                        Log.d("tag","Document do not exist");
                                    }
                                });




                            }
                        }
                    }
                    else {
                        //This Toast will be displayed only when you'll have an error while getting documents.
                        Toast.makeText(this, Objects.requireNonNull(task1.getException()).toString(), Toast.LENGTH_SHORT).show();
                    }
                });



    }
}