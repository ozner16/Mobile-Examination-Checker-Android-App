package com.mobileexaminationchecker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;


import java.util.Objects;

public class ForgotPassword extends AppCompatActivity {

    EditText emailEditText;
    Button resetPasswordButton;

    //firebase
    FirebaseAuth auth;

    //progress bar
    ProgressBar progressBar;

    public void openDialog(){

        forgotPass_success_dialog custom_dialog = new forgotPass_success_dialog();
        custom_dialog.show(getSupportFragmentManager(),"Custom dialog");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.email_forgotPass);
        resetPasswordButton = findViewById(R.id.forgot_pass_btn);
        progressBar = findViewById(R.id.progressBar);

        resetPasswordButton.setOnClickListener(view1 -> resetPass());

        //firebase
        auth = FirebaseAuth.getInstance();


    }

    private void resetPass(){

        String email = emailEditText.getText().toString().trim();
        auth = FirebaseAuth.getInstance();

        if(email.isEmpty()){
            emailEditText.setError("Email is required!");
            emailEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please provide a valid email!");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(task -> {
                    boolean check = Objects.requireNonNull(task.getResult().getSignInMethods()).isEmpty();
                    if(check){

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(ForgotPassword.this, "Email doesn't exist!", Toast.LENGTH_SHORT).show();

                    }
                    else {

                        auth.sendPasswordResetEmail(email).addOnCompleteListener(task2 -> {

                            if(task2.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                openDialog();
                            }
                            else{
                                progressBar.setVisibility(View.GONE);
                                auth.sendPasswordResetEmail(email).addOnCompleteListener(task1 -> {

                                    if(task1.isSuccessful()){

                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(ForgotPassword.this, "Verification link is already sent. Please check your email!", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(ForgotPassword.this, "Error: Too much sending of request. Please contact the admin!", Toast.LENGTH_LONG).show();
                                    }

                                });
                            }

                        });
                    }
                });
        //



    }


    @Override
    public void onStart() {
        super.onStart();
        progressBar.setVisibility(View.GONE);
    }
}