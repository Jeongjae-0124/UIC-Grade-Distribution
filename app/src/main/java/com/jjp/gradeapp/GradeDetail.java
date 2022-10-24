package com.jjp.gradeapp;

import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jjp.gradeapp.data.Grade;
import com.jjp.gradeapp.navigation.SearchFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradeDetail extends AppCompatActivity {
    TextView course_abb_text, course_name_text, primary_instructor, professor_name,
            rateMyProf, term_text, term_text2, withdraw_num, reg_num, sat_num, unsat_num;
    BarChart chart;
    Grade gradeSelected;
    int itemPosition;
    String firstName;
    String lastName;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_detail);
        gradeSelected = (Grade) getIntent().getSerializableExtra("gradeSelected");
        itemPosition = getIntent().getIntExtra("itemPosition", 1);
        rateMyProf = findViewById(R.id.rateMyProf);
        course_abb_text = findViewById(R.id.course_abb_text);
        course_name_text = findViewById(R.id.course_name_text);
        professor_name = findViewById(R.id.professor_name);
        primary_instructor = findViewById(R.id.primary_instructor);
        term_text2 = findViewById(R.id.term_text2);
        term_text = findViewById(R.id.term_text);
        chart = findViewById(R.id.chart);
        withdraw_num = findViewById(R.id.withdraw_num);
        reg_num = findViewById(R.id.reg_num);
        sat_num = findViewById(R.id.sat_num);
        unsat_num = findViewById(R.id.unsat_num);

        course_abb_text.setText(gradeSelected.getCourAbb() + gradeSelected.getCourNum());
        course_name_text.setText(gradeSelected.getCourTitle());

        professor_name.setText(gradeSelected.getInstructor());
        term_text2.setText(SearchFragment.term + SearchFragment.year);

        withdraw_num.setText(Integer.toString(gradeSelected.getWithdraw()));
        reg_num.setText(Integer.toString(gradeSelected.getRegs()));
        sat_num.setText(Integer.toString(gradeSelected.getSatis()) + "/");
        unsat_num.setText(Integer.toString(gradeSelected.getUnsatis()));

        String text = "(RateMyProfessor)";
        rateMyProf.setText(text);
        int target_comma = gradeSelected.getInstructor().indexOf(",");
        if(target_comma!=-1){
            lastName = gradeSelected.getInstructor().substring(0, target_comma);
            firstName = gradeSelected.getInstructor().substring(target_comma + 2);
            if (lastName.indexOf(" ") != -1) {
                int target_lastName = lastName.indexOf(" ");
                lastName = lastName.substring(0, target_lastName);
            }
            if (firstName.indexOf(" ") != -1) {
                int target_firstName = firstName.indexOf(" ");
                firstName = firstName.substring(0, target_firstName);
            }
        }
        else{
            firstName="Grad";
            lastName="Asst";
            rateMyProf.setVisibility(View.GONE);
            Log.e("last", lastName);
            Log.e("first", firstName);
        }





        Linkify.TransformFilter transformFilter= new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return "https://www.ratemyprofessors.com/search/teachers?query="
                        + firstName+ "%20" + lastName + "&sid=U2Nob29sLTExMTE=";
            }
        };
        Pattern pattern = Pattern.compile("(RateMyProfessor)");
        Log.e("first name", firstName);
        Log.e("last name", lastName);
        Linkify.addLinks(rateMyProf, pattern, "", null, transformFilter);

        BarData barData = new BarData();
        BarDataSet barDataSet = new BarDataSet(data1(), "Data");
        barDataSet.setValueFormatter(new com.github.mikephil.charting.formatter.PercentFormatter());
        barData.addDataSet(barDataSet);
        barData.setValueTextSize(20);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setData(barData);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.setDrawGridBackground(true);
        chart.setTouchEnabled(false);
        chart.getAxisLeft().setStartAtZero(true);
        ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("A");
        xLabel.add("B");
        xLabel.add("C");
        xLabel.add("D");
        xLabel.add("F");
        String[] values = {"A", "B", "C", "D", "F"};

        chart.setExtraBottomOffset(10);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(5);
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setTextSize(20);
        chart.invalidate();

    }

    private ArrayList<BarEntry> data1() {
        ArrayList<BarEntry> data_val = new ArrayList<>();
        float Apercent = ((float) gradeSelected.getANum() / gradeSelected.getRegs()) * 100;
        Log.e("A", String.valueOf(Apercent));
        float Bpercent = ((float) gradeSelected.getBNum() / gradeSelected.getRegs()) * 100;
        float Cpercent = ((float) gradeSelected.getCNum() / gradeSelected.getRegs()) * 100;
        float Dpercent = ((float) gradeSelected.getDNum() / gradeSelected.getRegs()) * 100;
        float Fpercent = ((float) gradeSelected.getFNum() / gradeSelected.getRegs()) * 100;
        data_val.add(new BarEntry('1', Apercent));
        data_val.add(new BarEntry('2', Bpercent));
        data_val.add(new BarEntry('3', Cpercent));
        data_val.add(new BarEntry('4', Dpercent));
        data_val.add(new BarEntry('5', Fpercent));
        return data_val;
    }

    public class MyXAxisValueFormatter extends ValueFormatter implements IAxisValueFormatter {

        private String[] mValues;

        public MyXAxisValueFormatter(String[] values) {
            Log.e("return", String.valueOf(values[0]));
            Log.e("return1", String.valueOf(values[1]));
            Log.e("return2", String.valueOf(values[2]));
            this.mValues = values;

        }

        public String getFormattedValue(float value) {
            // "value" represents the position of the label on the axis (x or y)
            if (value == 49) {
                return mValues[0];
            }
            if (value == 50) {
                return mValues[1];
            }
            if (value == 51) {
                return mValues[2];
            }
            if (value == 52) {
                return mValues[3];
            }
            return mValues[4];

        }
    }

    private class  PercentFormatter extends ValueFormatter implements IValueFormatter {
        private DecimalFormat mFormat;

        public PercentFormatter() {
            mFormat = new DecimalFormat("###,###,##0.0"); // use one decimal
        }
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mFormat.format(value)+"%";
        }
    }


}