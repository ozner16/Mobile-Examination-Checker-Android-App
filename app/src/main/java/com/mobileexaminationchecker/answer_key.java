package com.mobileexaminationchecker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
public class answer_key extends AppCompatActivity {

    public static final String TAG = "tag";
    Button reset_btn;
    Button save_btn;

    //firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageReference;

    EditText ans1;
    EditText ans2;
    EditText ans3;
    EditText ans4;
    EditText ans5;
    EditText ans6;
    EditText ans7;
    EditText ans8;
    EditText ans9;
    EditText ans10;

    //shared Pref
    public static final String PREF_NAME1 = "answers1";
    public static final String PREF_NAME2 = "answers2";
    public static final String PREF_NAME3 = "answers3";
    public static final String PREF_NAME4 = "answers4";
    public static final String PREF_NAME5 = "answers5";
    public static final String PREF_NAME6 = "answers6";
    public static final String PREF_NAME7 = "answers7";
    public static final String PREF_NAME8 = "answers8";
    public static final String PREF_NAME9 = "answers9";
    public static final String PREF_NAME10 = "answers10";

    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String answer5;
    String answer6;
    String answer7;
    String answer8;
    String answer9;
    String answer10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_key);

        reset_btn = findViewById(R.id.reset_btn);
        save_btn = findViewById(R.id.save_btn);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        ans5 = findViewById(R.id.ans5);
        ans6 = findViewById(R.id.ans6);
        ans7 = findViewById(R.id.ans7);
        ans8 = findViewById(R.id.ans8);
        ans9 = findViewById(R.id.ans9);
        ans10 = findViewById(R.id.ans10);

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();



        reset_btn.setOnClickListener(view -> {
            ans1.setText("");
            ans2.setText("");
            ans3.setText("");
            ans4.setText("");
            ans5.setText("");
            ans6.setText("");
            ans7.setText("");
            ans8.setText("");
            ans9.setText("");
            ans10.setText("");

            fStore.collection("answer_key_scanner").document(user.getUid()).delete();
        });

        save_btn.setOnClickListener(view -> {

            String answer1 = ans1.getText().toString().trim();
            String answer2 = ans2.getText().toString().trim();
            String answer3 = ans3.getText().toString().trim();
            String answer4 = ans4.getText().toString().trim();
            String answer5 = ans5.getText().toString().trim();
            String answer6 = ans6.getText().toString().trim();
            String answer7 = ans7.getText().toString().trim();
            String answer8 = ans8.getText().toString().trim();
            String answer9 = ans9.getText().toString().trim();
            String answer10 = ans10.getText().toString().trim();

            //shared Pref
            SharedPreferences settings1 = getSharedPreferences(PREF_NAME1,0);
            SharedPreferences.Editor editor1 = settings1.edit();
            editor1.putString("ans1", answer1);
            editor1.apply();

            SharedPreferences settings2 = getSharedPreferences(PREF_NAME2,0);
            SharedPreferences.Editor editor2 = settings2.edit();
            editor2.putString("ans2", answer2);
            editor2.apply();

            SharedPreferences settings3 = getSharedPreferences(PREF_NAME3,0);
            SharedPreferences.Editor editor3 = settings3.edit();
            editor3.putString("ans3", answer3);
            editor3.apply();

            SharedPreferences settings4 = getSharedPreferences(PREF_NAME4,0);
            SharedPreferences.Editor editor4 = settings4.edit();
            editor4.putString("ans4", answer4);
            editor4.apply();

            SharedPreferences settings5 = getSharedPreferences(PREF_NAME5,0);
            SharedPreferences.Editor editor5 = settings5.edit();
            editor5.putString("ans5", answer5);
            editor5.apply();

            SharedPreferences settings6 = getSharedPreferences(PREF_NAME6,0);
            SharedPreferences.Editor editor6 = settings6.edit();
            editor6.putString("ans6", answer6);
            editor6.apply();

            SharedPreferences settings7 = getSharedPreferences(PREF_NAME7,0);
            SharedPreferences.Editor editor7 = settings7.edit();
            editor7.putString("ans7", answer7);
            editor7.apply();

            SharedPreferences settings8 = getSharedPreferences(PREF_NAME8,0);
            SharedPreferences.Editor editor8 = settings8.edit();
            editor8.putString("ans8", answer8);
            editor8.apply();

            SharedPreferences settings9 = getSharedPreferences(PREF_NAME9,0);
            SharedPreferences.Editor editor9 = settings9.edit();
            editor9.putString("ans9", answer9);
            editor9.apply();

            SharedPreferences settings10 = getSharedPreferences(PREF_NAME10,0);
            SharedPreferences.Editor editor10 = settings10.edit();
            editor10.putString("ans10", answer10);
            editor10.apply();


            DocumentReference documentReference3 = fStore.collection("answer_key_scanner").document(user.getUid());
            Map<String,Object> exam3 = new HashMap<>();
            exam3.put("ans1", "1."+answer1);
            exam3.put("ans2", "2."+answer2);
            exam3.put("ans3", "3."+answer3);
            exam3.put("ans4", "4."+answer4);
            exam3.put("ans5", "5."+answer5);
            exam3.put("ans6", "6."+answer6);
            exam3.put("ans7", "7."+answer7);
            exam3.put("ans8", "8."+answer8);
            exam3.put("ans9", "9."+answer9);
            exam3.put("ans10", "10."+answer10);
            documentReference3.set(exam3).addOnSuccessListener(unused -> Log.d(TAG, "Succesfull!"));
            Toast.makeText(getApplicationContext(),"Answers successfully saved!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), scanner.class));

        });




    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences settings1 = getSharedPreferences(PREF_NAME1,0);
        String Ans1 = settings1.getString("ans1",answer1);

        SharedPreferences settings2 = getSharedPreferences(PREF_NAME2,0);
        String Ans2 = settings2.getString("ans2",answer2);

        SharedPreferences settings3 = getSharedPreferences(PREF_NAME3,0);
        String Ans3 = settings3.getString("ans3",answer3);

        SharedPreferences settings4 = getSharedPreferences(PREF_NAME4,0);
        String Ans4 = settings4.getString("ans4",answer4);

        SharedPreferences settings5 = getSharedPreferences(PREF_NAME5,0);
        String Ans5 = settings5.getString("ans5",answer5);

        SharedPreferences settings6 = getSharedPreferences(PREF_NAME6,0);
        String Ans6 = settings6.getString("ans6",answer6);

        SharedPreferences settings7 = getSharedPreferences(PREF_NAME7,0);
        String Ans7 = settings7.getString("ans7",answer7);

        SharedPreferences settings8 = getSharedPreferences(PREF_NAME8,0);
        String Ans8 = settings8.getString("ans8",answer8);

        SharedPreferences settings9 = getSharedPreferences(PREF_NAME9,0);
        String Ans9 = settings9.getString("ans9",answer9);

        SharedPreferences settings10 = getSharedPreferences(PREF_NAME10,0);
        String Ans10 = settings10.getString("ans10",answer10);


        //set text to textviews
        DocumentReference documentReference = fStore.collection("answer_key_scanner").document(user.getUid());
        documentReference.addSnapshotListener(this,(documentSnapshot,e)->{

            assert documentSnapshot != null;
            if(documentSnapshot.exists()){

                ans1.setText(Ans1);
                ans2.setText(Ans2);
                ans3.setText(Ans3);
                ans4.setText(Ans4);
                ans5.setText(Ans5);
                ans6.setText(Ans6);
                ans7.setText(Ans7);
                ans8.setText(Ans8);
                ans9.setText(Ans9);
                ans10.setText(Ans10);

            }
            else {

                Log.d("tag","Document do not exist");
            }
        });
    }
}