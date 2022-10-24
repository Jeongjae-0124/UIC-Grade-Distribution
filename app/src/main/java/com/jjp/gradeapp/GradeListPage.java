package com.jjp.gradeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jjp.gradeapp.adapter.DatabaseAdapter;
import com.jjp.gradeapp.adapter.GradeAdapter;
import com.jjp.gradeapp.data.Grade;

import java.util.ArrayList;

public class GradeListPage extends AppCompatActivity {
    DatabaseAdapter databaseAdapter;
    RecyclerView rvGrades;
    GradeAdapter gradesAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Grade> gradesList=new ArrayList<>();

    TextView yearResult;
    TextView courseResult;
    String term;
    String year;
    String course_number;
    String course_abb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_list_page);
        yearResult=findViewById(R.id.yearResult);
        courseResult=findViewById(R.id.courseResult);
        rvGrades = findViewById(R.id.searchResult_rcv);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvGrades.addItemDecoration( dividerItemDecoration);
        Intent intent = getIntent();
        term = intent.getStringExtra("term");
        year = intent.getStringExtra("year");
        course_number=intent.getStringExtra("course_number");
        course_abb=intent.getStringExtra("course_abb");

        yearResult.setText((term+year));
        courseResult.setText((course_abb+course_number));
        databaseAdapter=new DatabaseAdapter(this);
        gradesList= databaseAdapter.getSomeGrades(course_abb,Integer.parseInt(course_number));
        rvGrades=findViewById(R.id.searchResult_rcv);
        rvGrades.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        rvGrades.setLayoutManager(layoutManager);
        gradesAdapter=new GradeAdapter(this, gradesList, rvGrades);
        rvGrades.setAdapter(gradesAdapter);

    }
}