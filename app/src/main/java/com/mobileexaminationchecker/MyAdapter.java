package com.mobileexaminationchecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;
    ArrayList<User> userArrayList;

    public MyAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(context).inflate(R.layout.item_gned07, parent, false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        User user = userArrayList.get(position);

        holder.exam_code.setText(user.exam_code);
        holder.no_of_items.setText(String.valueOf(user.no_of_items));
        holder.exam_date_created.setText(user.exam_date_created);
        holder.date_submitted.setText(user.date_submitted);
        holder.instructor.setText(user.instructor);
        holder.score.setText(String.valueOf(user.score));


    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView exam_code, no_of_items, exam_date_created, date_submitted, instructor, score;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            exam_code = itemView.findViewById(R.id.exam_code);
            no_of_items = itemView.findViewById(R.id.no_of_items);
            exam_date_created = itemView.findViewById(R.id.exam_date_created);
            date_submitted = itemView.findViewById(R.id.exam_date_submitted);
            instructor = itemView.findViewById(R.id.instructor);
            score = itemView.findViewById(R.id.score);
        }
    }



}
