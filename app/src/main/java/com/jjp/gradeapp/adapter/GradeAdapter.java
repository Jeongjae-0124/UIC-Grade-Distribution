package com.jjp.gradeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jjp.gradeapp.GradeDetail;
import com.jjp.gradeapp.R;
import com.jjp.gradeapp.data.Grade;

import java.util.ArrayList;

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.ViewHolder> {
    Context context;
    ArrayList<Grade> gradesList;
    RecyclerView rvGrades;
    final View. OnClickListener onClickListener=new MyOnClickListener();
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView CourseName;
        TextView Instr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CourseName=itemView.findViewById(R.id.tvCourseName);
            Instr=itemView.findViewById(R.id.tvInstr);

        }
    }

    public GradeAdapter(Context context,ArrayList<Grade> gradesList, RecyclerView rvGrades){
        this.context=context;
        this.gradesList=gradesList;
        this.rvGrades=rvGrades;

    }


    @NonNull
    @Override
    public GradeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.single_item, parent,false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GradeAdapter.ViewHolder holder, int position) {
        Grade grade=gradesList.get(position);
        holder.CourseName.setText(grade.getCourTitle());
        holder.Instr.setText(grade.getInstructor());
    }

    @Override
    public int getItemCount() {
        return gradesList.size();
    }

    private class MyOnClickListener implements View. OnClickListener {
        @Override
        public void onClick(View view) {
            int itemPosition=rvGrades.getChildLayoutPosition(view);
            Grade gradeSelected= gradesList.get(itemPosition);
            Intent intent=new Intent(context, GradeDetail.class);
            intent.putExtra("gradeSelected",gradeSelected);
            intent.putExtra("itemPoistion",itemPosition);
            context.startActivity(intent);

        }
    }
}
