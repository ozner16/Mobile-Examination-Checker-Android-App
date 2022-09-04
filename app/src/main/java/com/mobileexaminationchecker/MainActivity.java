package com.mobileexaminationchecker;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.Objects;
public class MainActivity extends AppCompatActivity {

    Button reg_btn;
    Button login_btn;
    TextInputEditText email_login;
    TextInputEditText pass_login;
    TextView forgot_pass;

    //firebase
    FirebaseAuth mAuth;

    //progress bar
    ProgressBar progressBar;
    ProgressBar progressBar_dial;

    public static final String PREF_NAME = "MyPrefsFile";
    int home_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg_btn =  findViewById(R.id.create_acct);
        login_btn = findViewById(R.id.login_btn);
        email_login = findViewById(R.id.email_login);
        pass_login = findViewById(R.id.password);
        forgot_pass = findViewById(R.id.forgot_pass);
        AlertDialog dialog;
        progressBar = findViewById(R.id.progressBar);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //inflate the custom_dialog view
        View view = getLayoutInflater().inflate(R.layout.custom_dialog,null);

        //set this view to dialog
        builder.setView(view);

        //create dialog now
        dialog = builder.create();

        //register button click
        reg_btn.setOnClickListener(view1 -> dialog.show());

        progressBar_dial = view.findViewById(R.id.progressBar_dial);

        Button reg_dial_btn = view.findViewById(R.id.reg_stud_btn);
        reg_dial_btn.setOnClickListener(view12 -> {
            progressBar_dial.setVisibility(View.VISIBLE);
            Intent intent = new Intent(MainActivity.this, student_reg.class);
            startActivity(intent);

        });

        Button reg_prof_dial_btn = view.findViewById(R.id.reg_prof_btn);
        reg_prof_dial_btn.setOnClickListener(view13 -> {
            progressBar_dial.setVisibility(View.VISIBLE);
            Intent intent = new Intent(MainActivity.this, prof_reg.class);
            startActivity(intent);

        });

        forgot_pass.setOnClickListener(view1 -> {
            progressBar.setVisibility(View.VISIBLE);
            Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
            startActivity(intent);

        });


        //firebase
        mAuth = FirebaseAuth.getInstance();

        //login button click
        login_btn.setOnClickListener(view1 -> loginUser());

    }

    private void loginUser(){

        String email = Objects.requireNonNull(email_login.getText()).toString();
        String pass = Objects.requireNonNull(pass_login.getText()).toString();

        if(TextUtils.isEmpty(email)){
            email_login.setError("Email cannot be empty");
            email_login.requestFocus();
        }
        else if(TextUtils.isEmpty(pass)){
            pass_login.setError("Password cannot be empty");
            pass_login.requestFocus();
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    mAuth = FirebaseAuth.getInstance();

                    assert user != null;

                    if(user.isEmailVerified()){
                        progressBar.setVisibility(View.GONE);
                        homePage_stud();
                        homePage_prof();
                    }
                    else {
                        mAuth.signOut();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Your account is not verified. Please check your email!",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Log in Error: Invalid Email or Password" , Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void homePage_stud(){
        String email = Objects.requireNonNull(email_login.getText()).toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("user_students").whereEqualTo("email",email)
                .get().addOnCompleteListener(task1 -> {
            if(task1.isSuccessful()){
                for(QueryDocumentSnapshot document : task1.getResult()){
                    if(document.exists()){
                        home_count = 1;
                        SharedPreferences settings = getSharedPreferences(PREF_NAME,0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt("home_count",home_count);
                        editor.apply();
                        startActivity(new Intent(MainActivity.this, stud_home.class));
                    }
                }
            }
            else {
                //This Toast will be displayed only when you'll have an error while getting documents.
                Toast.makeText(this, Objects.requireNonNull(task1.getException()).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void homePage_prof(){
        String email = Objects.requireNonNull(email_login.getText()).toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("user_professors").whereEqualTo("email",email)
                .get().addOnCompleteListener(task1 -> {
            if(task1.isSuccessful()){
                for(QueryDocumentSnapshot document : task1.getResult()){
                    if(document.exists()){
                        home_count = 2;
                        SharedPreferences settings = getSharedPreferences(PREF_NAME,0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt("home_count",home_count);
                        editor.apply();
                        startActivity(new Intent(MainActivity.this, prof_home.class));
                    }
                }
            }
            else {
                //This Toast will be displayed only when you'll have an error while getting documents.
                Toast.makeText(this, Objects.requireNonNull(task1.getException()).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //keep user logged in even if u removed the app in the background
    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(View.GONE);
        progressBar_dial.setVisibility(View.GONE);
        FirebaseUser user = mAuth.getCurrentUser();
        SharedPreferences settings = getSharedPreferences(PREF_NAME,0);
        home_count = settings.getInt("home_count", home_count);

        /*if(user != null){

            startActivity(new Intent(MainActivity.this,stud_home.class));

        }*/
        if(user != null && home_count == 1){

            startActivity(new Intent(MainActivity.this, stud_home.class));

        }
        if(user != null && home_count == 2){

            startActivity(new Intent(MainActivity.this, prof_home.class));

        }
       /* if(user != null && home_count == 0){

            Toast.makeText(this, "count home = 0!", Toast.LENGTH_SHORT).show();

        }*/


    }




}