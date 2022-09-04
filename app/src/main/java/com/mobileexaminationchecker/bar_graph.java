package com.mobileexaminationchecker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class bar_graph extends AppCompatActivity {


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

    BarChart barChart;
    ArrayList<BarEntry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_graph);

        //firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        storage= FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        barChart = findViewById(R.id.barchart);


        /*entries.add(new BarEntry(8f, 0));
        entries.add(new BarEntry(2f, 1));
        entries.add(new BarEntry(5f, 2));


        BarDataSet bardataset = new BarDataSet(entries, "Grades");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("GNED 07");
        labels.add("GNED 10");
        labels.add("COSC 110");


        BarData data = new BarData(labels, bardataset);
        barChart.setData(data); // set the data and list of labels into chart
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setDescription("");  // set the description
        barChart.animateY(3000);*/

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

                            if(entries.size() == 0){


                                //out of looping above
                                gned07_avg =  (gned07_total_score.floatValue()/gned07_total_no_of_items.floatValue());
                                gned07_grade_percent = gned07_avg * 100;


                                //Bar chart
                                entries.add(new BarEntry(gned07_grade_percent, 0));
                                BarDataSet bardataset = new BarDataSet(entries, "Grades");

                                labels.add("GNED 07");

                                BarData data = new BarData(labels, bardataset);
                                barChart.setData(data); // set the data and list of labels into chart
                                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                barChart.setDescription("");  // set the description
                                barChart.animateY(3000);
                            }
                            else if(entries.size() == 1){

                                //out of looping above
                                gned07_avg =  (gned07_total_score.floatValue()/gned07_total_no_of_items.floatValue());
                                gned07_grade_percent = gned07_avg * 100;


                                //Bar chart
                                entries.add(new BarEntry(gned07_grade_percent, 1));
                                BarDataSet bardataset = new BarDataSet(entries, "Grades");

                                labels.add("GNED 07");

                                BarData data = new BarData(labels, bardataset);
                                barChart.setData(data); // set the data and list of labels into chart
                                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                barChart.setDescription("");  // set the description
                                barChart.animateY(3000);

                            }
                            else {

                                //out of looping above
                                gned07_avg =  (gned07_total_score.floatValue()/gned07_total_no_of_items.floatValue());
                                gned07_grade_percent = gned07_avg * 100;


                                //Bar chart
                                entries.add(new BarEntry(gned07_grade_percent, 2));
                                BarDataSet bardataset = new BarDataSet(entries, "Grades");

                                labels.add("GNED 07");

                                BarData data = new BarData(labels, bardataset);
                                barChart.setData(data); // set the data and list of labels into chart
                                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                barChart.setDescription("");  // set the description
                                barChart.animateY(3000);

                            }




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


                            if(entries.size() == 0){


                                //out of looping above
                                gned10_avg =  (gned10_total_score.floatValue()/gned10_total_no_of_items.floatValue());
                                gned10_grade_percent = gned10_avg * 100;


                                //Bar chart
                                entries.add(new BarEntry(gned10_grade_percent, 0));
                                BarDataSet bardataset = new BarDataSet(entries, "Grades");
                                labels.add("GNED 10");

                                BarData data = new BarData(labels, bardataset);
                                barChart.setData(data); // set the data and list of labels into chart
                                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                barChart.setDescription("");  // set the description
                                barChart.animateY(3000);
                            }
                            else if(entries.size() == 1){

                                //out of looping above
                                gned10_avg =  (gned10_total_score.floatValue()/gned10_total_no_of_items.floatValue());
                                gned10_grade_percent = gned10_avg * 100;


                                //Bar chart
                                entries.add(new BarEntry(gned10_grade_percent, 1));
                                BarDataSet bardataset = new BarDataSet(entries, "Grades");
                                labels.add("GNED 10");

                                BarData data = new BarData(labels, bardataset);
                                barChart.setData(data); // set the data and list of labels into chart
                                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                barChart.setDescription("");  // set the description
                                barChart.animateY(3000);

                            }
                            else {


                                //out of looping above
                                gned10_avg =  (gned10_total_score.floatValue()/gned10_total_no_of_items.floatValue());
                                gned10_grade_percent = gned10_avg * 100;


                                //Bar chart
                                entries.add(new BarEntry(gned10_grade_percent, 2));
                                BarDataSet bardataset = new BarDataSet(entries, "Grades");
                                labels.add("GNED 10");

                                BarData data = new BarData(labels, bardataset);
                                barChart.setData(data); // set the data and list of labels into chart
                                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                barChart.setDescription("");  // set the description
                                barChart.animateY(3000);
                            }




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


                            if(entries.size() == 0){

                                //out of looping above
                                cosc110_avg =  (cosc110_total_score.floatValue()/cosc110_total_no_of_items.floatValue());
                                cosc110_grade_percent = cosc110_avg * 100;


                                //Bar chart
                                entries.add(new BarEntry(cosc110_grade_percent, 0));
                                BarDataSet bardataset = new BarDataSet(entries, "Grades");
                                labels.add("COSC 110");

                                BarData data = new BarData(labels, bardataset);
                                barChart.setData(data); // set the data and list of labels into chart
                                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                barChart.setDescription("");  // set the description
                                barChart.animateY(3000);


                            }
                            else if(entries.size() == 1){

                                //out of looping above
                                cosc110_avg =  (cosc110_total_score.floatValue()/cosc110_total_no_of_items.floatValue());
                                cosc110_grade_percent = cosc110_avg * 100;


                                //Bar chart
                                entries.add(new BarEntry(cosc110_grade_percent, 1));
                                BarDataSet bardataset = new BarDataSet(entries, "Grades");
                                labels.add("COSC 110");

                                BarData data = new BarData(labels, bardataset);
                                barChart.setData(data); // set the data and list of labels into chart
                                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                barChart.setDescription("");  // set the description
                                barChart.animateY(3000);


                            }
                            else {

                                //out of looping above
                                cosc110_avg =  (cosc110_total_score.floatValue()/cosc110_total_no_of_items.floatValue());
                                cosc110_grade_percent = cosc110_avg * 100;


                                //Bar chart
                                entries.add(new BarEntry(cosc110_grade_percent, 2));
                                BarDataSet bardataset = new BarDataSet(entries, "Grades");
                                labels.add("COSC 110");

                                BarData data = new BarData(labels, bardataset);
                                barChart.setData(data); // set the data and list of labels into chart
                                bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                                barChart.setDescription("");  // set the description
                                barChart.animateY(3000);

                            }




                        }



                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });



    }

}