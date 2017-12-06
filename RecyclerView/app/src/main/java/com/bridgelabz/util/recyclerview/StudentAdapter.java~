package com.bridgelabz.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bridgeit on 1/9/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.CustomViewHolder> {

    Context context;
    ArrayList<studentSubjects> StudentSubjects;

    public StudentAdapter(Context context, ArrayList<studentSubjects> studentSubjects) {
        this.context = context;
        this.StudentSubjects = studentSubjects;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        studentSubjects studentsub = StudentSubjects.get(position);
        holder.imageView.setImageResource(studentsub.imageResid);
        holder.textView.setText(studentsub.subjectName);
        holder.checkBox.setChecked(studentsub.isWorkDone);
    }

    @Override
    public int getItemCount() {
        return StudentSubjects.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        CheckBox checkBox;

        public CustomViewHolder(View view) {
            super(view);
            imageView =(ImageView) view.findViewById(R.id.imageView);
            textView = (TextView) view.findViewById(R.id.textView);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StudentSubjects.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}
