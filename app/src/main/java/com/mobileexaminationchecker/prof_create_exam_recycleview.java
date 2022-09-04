package com.mobileexaminationchecker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class prof_create_exam_recycleview extends AppCompatActivity {
    String []data = {"Question1:","Question2:", "Question3:", "Question4:","Question5:", "Question6:", "Question7:", "Question8:","Question9:", "Question10:", "Question11:"
            , "Question12:","Question13:", "Question14:", "Question15:", "Question16:","Question17:", "Question18:", "Question19:", "Question20:","Question21:", "Question22:", "Question23:", "Question24:","Question25:", "Question26:", "Question27:"
            , "Question28:","Question29:", "Question30:", "Question31:", "Question32:","Question33:", "Question34:", "Question35:","Question36:", "Question37:", "Question38:","Question39:", "Question40:", "Question41:","Question42:", "Question43:", "Question44:"
            ,"Question45:", "Question46:", "Question47:","Question48:", "Question49:", "Question50:","Question51:", "Question52:", "Question53:","Question54:", "Question55:", "Question56:","Question57:", "Question58:", "Question59:","Question60:", "Question61:", "Question62:"
            ,"Question63:", "Question64:", "Question65:","Question66:", "Question67:", "Question68:","Question69:", "Question70:", "Question71:","Question72:", "Question73:", "Question74:","Question75:", "Question76:", "Question77:","Question78:", "Question79:", "Question80:"
            ,"Question81:", "Question82:", "Question83:","Question84:", "Question85:", "Question86:","Question87:", "Question88:", "Question89:","Question90:", "Question91:", "Question92:","Question93:", "Question94:", "Question95:"
            ,"Question96:", "Question97:", "Question98:","Question99:", "Question100:"};
    int counter = 1;
    Button save_edit;
    Button add;
    Button delete_btn;
    Button remove_quest_btn;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_create_exam_recycleview);

        remove_quest_btn = findViewById(R.id.remove_quest_btn);
        delete_btn = findViewById(R.id.delete_btn);
        add= findViewById(R.id.add);
        save_edit = findViewById(R.id.save_edit);
        List<String> items = new LinkedList<>();
        items.add(data[0]);

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        DemoAdapter adapter = new DemoAdapter(items);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener(view -> {


            if(counter <= 99 ){

                items.add(data[counter%100]);
                counter++;
                adapter.notifyItemInserted(items.size()-1);

            }
            else {
                add.setEnabled(false);
                add.setBackgroundColor(getColor(R.color.Light_gray));

            }


            save_edit.setOnClickListener(view1 -> {
                Toast.makeText(getApplicationContext(), "Exam has been saved!" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), prof_home.class));

            });

        });

        remove_quest_btn.setOnClickListener(view5-> {
                if(items.size() > 0){

                    adapter.notifyItemRemoved(items.size()-1);
                    adapter.items.remove(items.size()-1);

                    counter--;

                }



            });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


            /*if(counter <= 99 ){

                delete_btn.setEnabled(false);
                delete_btn.setBackgroundColor(getColor(R.color.Light_gray));
            }
            else {

                add.setEnabled(false);
                add.setBackgroundColor(getColor(R.color.Light_gray));
                save_edit.setEnabled(true);
                delete_btn.setEnabled(true);

            }*/





    }
}