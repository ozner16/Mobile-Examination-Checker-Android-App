package com.mobileexaminationchecker;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class prof_change_pass extends AppCompatActivity {

    TextInputEditText prof_changePass_txt1;
    TextInputEditText prof_changePass_txt2;
    Button bck_btn;
    Button confirm_btn;

    //firebase
    FirebaseUser user;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_change_pass);

        prof_changePass_txt1 = findViewById(R.id.prof_changePass_txt1);
        prof_changePass_txt2 = findViewById(R.id.prof_changePass_txt2);
        bck_btn = findViewById(R.id.bck_btn);
        confirm_btn = findViewById(R.id.confirm_btn);
        user = FirebaseAuth.getInstance().getCurrentUser();
        fAuth = FirebaseAuth.getInstance();

        confirm_btn.setOnClickListener(view -> changePassword());
        bck_btn.setOnClickListener(view1 -> onBackPressed());

        //Re-Authenticate User
        ReAuthenticateUser();


    }

    public void changePassword(){

        String new_pass = Objects.requireNonNull(prof_changePass_txt1.getText()).toString();
        String confirm_new_pass = Objects.requireNonNull(prof_changePass_txt2.getText()).toString();

        //firestore
        user = FirebaseAuth.getInstance().getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();


        if(TextUtils.isEmpty(new_pass) || TextUtils.isEmpty(confirm_new_pass)){
            Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
        }
        else {

            if(new_pass.equals(confirm_new_pass)){

                user.updatePassword(new_pass)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {

                                DocumentReference docRef = fStore.collection("user_professors").document(userId);
                                Map<String,Object> edited = new HashMap<>();
                                edited.put("password",new_pass);
                                docRef.update(edited);
                                Toast.makeText(getApplicationContext(), "Password successfully changed!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(prof_change_pass.this, prof_home.class));
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else {

                Toast.makeText(this, "New password and Confirm password doesn't match!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void ReAuthenticateUser(){


        //firestore
        user = FirebaseAuth.getInstance().getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        String userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = fStore.collection("user_professors").document(userId);
        documentReference.addSnapshotListener(this,(documentSnapshot,e)->{

            String email = Objects.requireNonNull(documentSnapshot).getString("email");
            String password = documentSnapshot.getString("password");

            if(documentSnapshot.exists()){

                AuthCredential credential = EmailAuthProvider.getCredential(Objects.requireNonNull(email), Objects.requireNonNull(password));
                Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).reauthenticate(credential);

            }
            else {

                Log.d("tag","Document do not exist");
            }
        });



    }

}