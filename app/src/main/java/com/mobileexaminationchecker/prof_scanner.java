package com.mobileexaminationchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class prof_scanner extends AppCompatActivity {

    private static final  int requestcamera_code = 12;
    Button camera1;
    Button paste_btn;
    ImageView imageView;
    TextView copied_txt;
    TextView copied_txt1;
    TextView copied_txt2;
    Button score_btn;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_scanner);

        imageView = findViewById(R.id.imageView);
        camera1 = findViewById(R.id.take_btn);
        copied_txt = findViewById(R.id.copied_txt);
        copied_txt1 = findViewById(R.id.copied_txt1);
        copied_txt2= findViewById(R.id.copied_txt2);
        paste_btn = findViewById(R.id.paste_btn);
        score_btn = findViewById(R.id.score_btn);



       paste_btn.setOnClickListener(view -> {
          copied_txt.setText("1. Correct");
           copied_txt1.setText("2. Wrong");
           copied_txt2.setText("3. Correct");
        });

        camera1.setOnClickListener(view -> {
            Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera, requestcamera_code );
        });

        score_btn.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "Your Score is 2" , Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==requestcamera_code){
            Bitmap imgbitmap=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(imgbitmap);
        }
    }
}