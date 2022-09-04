package com.mobileexaminationchecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {


    Context context1;
    ArrayList<User1> user1ArrayList;

    public MyAdapter1(Context context1, ArrayList<User1> user1ArrayList) {
        this.context1 = context1;
        this.user1ArrayList = user1ArrayList;
    }

    @NonNull
    @Override
    public MyAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context1).inflate(R.layout.item_exam_records_20, parent, false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter1.MyViewHolder holder, int position) {

        User1 user1 = user1ArrayList.get(position);

        holder.student_name.setText(user1.student_name);
        holder.course_yr_sec.setText(user1.course_yr_sec);
        holder.score.setText(String.valueOf(user1.score));
        holder.exam_date_created.setText(user1.exam_date_created);
        holder.date_submitted.setText(user1.date_submitted);

    }

    @Override
    public int getItemCount() {
        return user1ArrayList.size();
    }

    public void filterList(ArrayList<User1> filteredList){

        user1ArrayList = filteredList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView student_name, course_yr_sec, score, exam_date_created, date_submitted;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            student_name = itemView.findViewById(R.id.stud_name);
            course_yr_sec = itemView.findViewById(R.id.course_yr_sec);
            score = itemView.findViewById(R.id.score);
            exam_date_created = itemView.findViewById(R.id.exam_date_created);
            date_submitted = itemView.findViewById(R.id.exam_date_submitted);

        }
    }


}
