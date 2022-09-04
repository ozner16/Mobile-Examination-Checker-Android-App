 package com.mobileexaminationchecker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 public class scanner extends AppCompatActivity {

    Button button_capture, button_check_exam, button_answer_key;
    EditText textview_data;
    Bitmap bitmap;

     //firebase
     FirebaseAuth fAuth;
     FirebaseFirestore fStore;
     FirebaseUser user;
     FirebaseStorage storage;
     StorageReference storageReference;

    private static  final int REQUEST_CAMERA_CODE = 100;
     ArrayList<String> arr_scanned_txt = new ArrayList<>();

     //alert dialog
     AlertDialog dialog;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);

        button_capture = findViewById(R.id.button_capture);
        button_check_exam = findViewById(R.id.button_check_exam);
        button_answer_key = findViewById(R.id.button_answer_key);
        textview_data = findViewById(R.id.text_data);


        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        if(ContextCompat.checkSelfPermission(scanner.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(scanner.this, new String[]{

                    Manifest.permission.CAMERA

            }, REQUEST_CAMERA_CODE);
        }

            button_capture.setOnClickListener(view -> CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(scanner.this));

        button_check_exam.setOnClickListener(view -> checkExam());

        button_answer_key.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), answer_key.class)));

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private void checkExam(){

        List<CharSequence> lines = new ArrayList<>();

        if(!arr_scanned_txt.isEmpty()){

            int count = textview_data.getLineCount();
            for (int line = 0; line < count; line++) {
                int start = textview_data.getLayout().getLineStart(line);
                int end = textview_data.getLayout().getLineEnd(line);
                CharSequence substring = textview_data.getText().subSequence(start, end);
                lines.add(substring);
            }

            if(lines.size() >= 10){

                String stud_ans1 = lines.get(0).toString();
                String stud_ans2 = lines.get(1).toString();
                String stud_ans3 = lines.get(2).toString();
                String stud_ans4 = lines.get(3).toString();
                String stud_ans5 = lines.get(4).toString();
                String stud_ans6 = lines.get(5).toString();
                String stud_ans7 = lines.get(6).toString();
                String stud_ans8 = lines.get(7).toString();
                String stud_ans9 = lines.get(8).toString();
                String stud_ans10 = lines.get(9).toString();


                //set text to textviews
                DocumentReference documentReference = fStore.collection("answer_key_scanner").document(user.getUid());
                documentReference.addSnapshotListener(this,(documentSnapshot,e)->{

                    int score = 0;
                    assert documentSnapshot != null;
                    String Answ1 = documentSnapshot.getString("ans1");
                    String Answ2 = documentSnapshot.getString("ans2");
                    String Answ3 = documentSnapshot.getString("ans3");
                    String Answ4 = documentSnapshot.getString("ans4");
                    String Answ5 = documentSnapshot.getString("ans5");
                    String Answ6 = documentSnapshot.getString("ans6");
                    String Answ7 = documentSnapshot.getString("ans7");
                    String Answ8 = documentSnapshot.getString("ans8");
                    String Answ9 = documentSnapshot.getString("ans9");
                    String Answ10 = documentSnapshot.getString("ans10");

                    if(documentSnapshot.exists()){

                        if(stud_ans1.trim().equals(Answ1)){
                            score++;
                        }
                        if(stud_ans2.trim().equals(Answ2)){
                            score++;
                        }
                        if(stud_ans3.trim().equals(Answ3)){
                            score++;
                        }
                        if(stud_ans4.trim().equals(Answ4)){
                            score++;
                        }
                        if(stud_ans5.trim().equals(Answ5)){
                            score++;
                        }
                        if(stud_ans6.trim().equals(Answ6)){
                            score++;
                        }
                        if(stud_ans7.trim().equals(Answ7)){
                            score++;
                        }
                        if(stud_ans8.trim().equals(Answ8)){
                            score++;
                        }
                        if(stud_ans9.trim().equals(Answ9)){
                            score++;
                        }
                        if(stud_ans10.trim().equals(Answ10)){
                            score++;
                        }

                        String total_score = Integer.toString(score);

                        //alert dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);

                        //Inflate the prof_exam_dialog view
                        @SuppressLint("InflateParams") View view10 = getLayoutInflater().inflate(R.layout.already_take_exam_dialog,null);
                        TextView tv_message = view10.findViewById(R.id.tv_message);
                        Button okay_btn = view10.findViewById(R.id.okay_btn);

                        tv_message.setTextColor(getColor(R.color.blue));

                        tv_message.setText("Your score is: " + total_score);

                        //set this view to dialog
                        builder.setView(view10);
                        //create dialog now
                        dialog = builder.create();

                        dialog.show();

                        okay_btn.setOnClickListener(view -> dialog.dismiss());


                    }
                    else {

                        Log.d("tag","Document do not exist");
                    }
                });


            }




        }
        else {

            Toast.makeText(scanner.this,"EMPTY!", Toast.LENGTH_SHORT).show();

        }



    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

             CropImage.ActivityResult result = CropImage.getActivityResult(data);
             if(resultCode == RESULT_OK){

                 assert result != null;
                 Uri resultUri = result.getUri();
                 try {

                     bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                     getTextFromImage(bitmap);


                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }

         }
     }

     @SuppressLint("SetTextI18n")
     private void getTextFromImage(Bitmap bitmap){
         TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
         if(!recognizer.isOperational()){
             Toast.makeText(scanner.this,"Error Occurred!!!", Toast.LENGTH_SHORT).show();
         }
         else {

             Frame frame = new Frame.Builder().setBitmap(bitmap).build();
             SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
             StringBuilder stringBuilder = new StringBuilder();
             for(int i=0; i<textBlockSparseArray.size(); i++){
                 TextBlock textBlock = textBlockSparseArray.valueAt(i);
                 stringBuilder.append(textBlock.getValue());
                 arr_scanned_txt.add(textBlock.getValue());
                 stringBuilder.append("\n");
             }
             //adding string to the textview
             textview_data.setText(stringBuilder.toString());
             button_capture.setText("Retake");
             button_check_exam.setVisibility(View.VISIBLE);
             button_answer_key.setVisibility(View.GONE);

         }

     }

     //COPY TEXT METHOD
    /* private void copyToClipBoard(String text){

         ClipboardManager clipBoard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
         ClipData clip = ClipData.newPlainText("Copied data", text);
         clipBoard.setPrimaryClip(clip);
         Toast.makeText(scanner.this,"Copied to clipboard!", Toast.LENGTH_SHORT).show();


     }*/

 }