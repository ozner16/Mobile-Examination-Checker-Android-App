package com.mobileexaminationchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class stud_scores extends AppCompatActivity {

    Button gned07_btn;
    Button gned10_btn;
    Button cosc110_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stud_scores);

        gned07_btn = findViewById(R.id.gned07_btn);
        gned10_btn = findViewById(R.id.gned10_btn);
        cosc110_btn = findViewById(R.id.cosc110_btn);


        gned07_btn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), stud_gned07_exam_scores.class)));
        gned10_btn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), stud_gned10_exam_scores.class)));
        cosc110_btn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), stud_cosc110_exam_scores.class)));
    }
}