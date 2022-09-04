package com.mobileexaminationchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class item_analyzation extends AppCompatActivity {


    public static final String TAG = "tag";
    //firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseStorage storage;
    StorageReference storageReference;

    //
    float gned07_avg = 0;
    float gned07_grade_percent = 0;
    float gned10_avg = 0;
    float gned10_grade_percent = 0;
    float cosc110_avg = 0;
    float cosc110_grade_percent = 0;
    List<DataEntry> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_analyzation);

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        gned07_avg();
        gned10_avg();
        cosc110_avg();


    }

    private void gned07_avg(){


        AtomicInteger gned07_item_count = new AtomicInteger();
        AtomicLong gned07_total_score = new AtomicLong();
        AtomicInteger gned07_total_no_of_items = new AtomicInteger();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("stud_exam_info_20").document(user.getUid()).collection("GNED 07")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            long score = (int) Objects.requireNonNull(document.getLong("score")).longValue();
                            long no_of_items = (int) Objects.requireNonNull(document.getLong("no_of_items")).longValue();
                            gned07_total_score.set(gned07_total_score.get() + score);
                            gned07_total_no_of_items.set((int) (gned07_total_no_of_items.get() + no_of_items));
                            gned07_item_count.getAndIncrement();

                        }

                        if(gned07_item_count.intValue() > 0){

                            //out of looping above
                            gned07_avg =  (gned07_total_score.floatValue()/gned07_total_no_of_items.floatValue());
                            gned07_grade_percent = gned07_avg * 100;


                            //pie chart
                            Pie pie = AnyChart.pie();
                            data.add(new ValueDataEntry("GNED 07", gned07_grade_percent));
                            pie.data(data);

                            AnyChartView anyChartView = findViewById(R.id.any_chart_view);
                            anyChartView.setChart(pie);

                        }



                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });



    }

    private void gned10_avg(){

        AtomicInteger gned10_item_count = new AtomicInteger();
        AtomicLong gned10_total_score = new AtomicLong();
        AtomicInteger gned10_total_no_of_items = new AtomicInteger();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("stud_exam_info_20").document(user.getUid()).collection("GNED 10")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            long score = (int) Objects.requireNonNull(document.getLong("score")).longValue();
                            long no_of_items = (int) Objects.requireNonNull(document.getLong("no_of_items")).longValue();
                            gned10_total_score.set(gned10_total_score.get() + score);
                            gned10_total_no_of_items.set((int) (gned10_total_no_of_items.get() + no_of_items));
                            gned10_item_count.getAndIncrement();

                        }

                        if(gned10_item_count.intValue() > 0){

                            //out of looping above
                            gned10_avg =  (gned10_total_score.floatValue()/gned10_total_no_of_items.floatValue());
                            gned10_grade_percent = gned10_avg * 100;


                            //pie chart
                            Pie pie = AnyChart.pie();
                            data.add(new ValueDataEntry("GNED 10", gned10_grade_percent));
                            pie.data(data);

                            AnyChartView anyChartView = findViewById(R.id.any_chart_view);
                            anyChartView.setChart(pie);

                        }



                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });



    }

    private void cosc110_avg(){


        AtomicInteger cosc110_item_count = new AtomicInteger();
        AtomicLong cosc110_total_score = new AtomicLong();
        AtomicInteger cosc110_total_no_of_items = new AtomicInteger();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("stud_exam_info_20").document(user.getUid()).collection("COSC 110")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            long score = (int) Objects.requireNonNull(document.getLong("score")).longValue();
                            long no_of_items = (int) Objects.requireNonNull(document.getLong("no_of_items")).longValue();
                            cosc110_total_score.set(cosc110_total_score.get() + score);
                            cosc110_total_no_of_items.set((int) (cosc110_total_no_of_items.get() + no_of_items));
                            cosc110_item_count.getAndIncrement();

                        }

                        if(cosc110_item_count.intValue() > 0){

                            //out of looping above
                            cosc110_avg =  (cosc110_total_score.floatValue()/cosc110_total_no_of_items.floatValue());
                            cosc110_grade_percent = cosc110_avg * 100;


                            //pie chart
                            Pie pie = AnyChart.pie();
                            data.add(new ValueDataEntry("COSC 110", cosc110_grade_percent));
                            pie.data(data);

                            AnyChartView anyChartView = findViewById(R.id.any_chart_view);
                            anyChartView.setChart(pie);

                        }



                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });


    }



}