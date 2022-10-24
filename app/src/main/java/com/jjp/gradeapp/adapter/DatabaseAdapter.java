package com.jjp.gradeapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jjp.gradeapp.data.Course;
import com.jjp.gradeapp.data.Department;
import com.jjp.gradeapp.data.Grade;
import com.jjp.gradeapp.navigation.SearchFragment;

import java.util.ArrayList;

public class DatabaseAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    ArrayList<Grade> gradesList= new ArrayList<Grade>();
    ArrayList<Department> departmentsList= new ArrayList<Department>();
    ArrayList<Course> coursesList=new ArrayList<>();
    public DatabaseAdapter(Context context){
        helper=new DatabaseHelper(context);
        db=helper.getWritableDatabase();
    }

    public void close(){
        helper.close();
    }

    public ArrayList<Grade> getSomeGrades( String course_abb, int course_number){
        String tablename= SearchFragment.term+SearchFragment.year;
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE CRSSUBJCD=? AND CRSNBR=?",tablename),new String[]{course_abb,Integer.toString(course_number)},null);
        while(cursor.moveToNext()){
            int index1=cursor.getColumnIndex(DatabaseHelper.KEY_CRSSUBJCD);
            int index2=cursor.getColumnIndex(DatabaseHelper.KEY_CRSNBR);
            int index3=cursor.getColumnIndex(DatabaseHelper.KEY_CRSTITLE);
            int index4=cursor.getColumnIndex(DatabaseHelper.KEY_DEPTCD);
            int index5=cursor.getColumnIndex(DatabaseHelper.KEY_DEPTNAME);
            int index6=cursor.getColumnIndex(DatabaseHelper.KEY_A);
            int index7=cursor.getColumnIndex(DatabaseHelper.KEY_B);
            int index8=cursor.getColumnIndex(DatabaseHelper.KEY_C);
            int index9=cursor.getColumnIndex(DatabaseHelper.KEY_D);
            int index10=cursor.getColumnIndex(DatabaseHelper.KEY_F);
            int index11=cursor.getColumnIndex(DatabaseHelper.KEY_ADV);
            int index12=cursor.getColumnIndex(DatabaseHelper.KEY_CR);
            int index13=cursor.getColumnIndex(DatabaseHelper.KEY_DFR);
            int index14=cursor.getColumnIndex(DatabaseHelper.KEY_I);
            int index15=cursor.getColumnIndex(DatabaseHelper.KEY_NG);
            int index16=cursor.getColumnIndex(DatabaseHelper.KEY_NR);
            int index17=cursor.getColumnIndex(DatabaseHelper.KEY_O);
            int index18=cursor.getColumnIndex(DatabaseHelper.KEY_PR);
            int index19=cursor.getColumnIndex(DatabaseHelper.KEY_S);
            int index20=cursor.getColumnIndex(DatabaseHelper.KEY_U);
            int index21=cursor.getColumnIndex(DatabaseHelper.KEY_W);
            int index22=cursor.getColumnIndex(DatabaseHelper.KEY_PrimaryInstructor);
            int index23=cursor.getColumnIndex(DatabaseHelper.KEY_GradeRegs);

            String courseAbb=cursor.getString(index1);
            int courNum=cursor.getInt(index2);
            String courTitle=cursor.getString(index3);
            int deptCd=cursor.getInt(index4);
            String deptName=cursor.getString(index5);
            int aNum=cursor.getInt(index6);
            int bNum=cursor.getInt(index7);
            int cNum=cursor.getInt(index8);
            int dNum=cursor.getInt(index9);
            int fNum=cursor.getInt(index10);
            int advance=cursor.getInt(index11);
            int credit=cursor.getInt(index12);
            int deferred=cursor.getInt(index13);
            int incomplete=cursor.getInt(index14);
            int nonGrade=cursor.getInt(index15);
            int notReported=cursor.getInt(index16);
            int outstanding=cursor.getInt(index17);
            int proficient=cursor.getInt(index18);
            int satis=cursor.getInt(index19);
            int unsatis=cursor.getInt(index20);
            int withdraw=cursor.getInt(index21);
            String instructor=cursor.getString(index22);
            int regs=cursor.getInt(index23);




            Grade grade=new Grade(courseAbb,courNum,courTitle,deptCd,deptName,aNum,
                                  bNum,cNum,dNum,fNum,advance,credit,deferred,incomplete,nonGrade,
                                  notReported,outstanding,proficient,satis,unsatis,withdraw,instructor,
                                  regs);
            gradesList.add(grade);

        }
        Log.e("gradesize", String.valueOf(gradesList.size()));
        return gradesList;

    }

    public ArrayList<Department> getSomeDepart(){
        Cursor cursor=db.rawQuery("SELECT DISTINCT CRSSUBJCD,DEPTNAME FROM newcourse2",null);
        while(cursor.moveToNext()){
            int index1=cursor.getColumnIndex(DatabaseHelper.KEY_CRSSUBJCD);
            int index2=cursor.getColumnIndex(DatabaseHelper.KEY_DEPTNAME);
            String courseAbb=cursor.getString(index1);
            String deptName=cursor.getString(index2);
            Department department=new Department(courseAbb,deptName);
            departmentsList.add(department);
        }
        return departmentsList;
    }

    public ArrayList<Course> getSomeCourse(String course_abb){
        Cursor cursor=db.rawQuery("SELECT  * FROM newcourse2 WHERE CRSSUBJCD =? GROUP BY CRSNBR",new String[]{course_abb},null);

        while(cursor.moveToNext()){
            int index1=cursor.getColumnIndex(DatabaseHelper.KEY_CRSSUBJCD);
            int index2=cursor.getColumnIndex(DatabaseHelper.KEY_CRSNBR);
            int index3=cursor.getColumnIndex(DatabaseHelper.KEY_CRSTITLE);
            String courseAbb=cursor.getString(index1);
            int courseNum=cursor.getInt(index2);
            String courseTitle=cursor.getString(index3);
            Course course=new Course(courseAbb,courseNum,courseTitle);
            coursesList.add(course);
        }
        return coursesList;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{
        private static final String DATBASE_NAME="gradedb.db";
        private static final String TABLE_NAME=SearchFragment.term+SearchFragment.year;
        private static final int DATABASE_VERSION=1;
        private static final String KEY_CRSSUBJCD="CRSSUBJCD";
        private static final String KEY_CRSNBR="CRSNBR";
        private static final String KEY_CRSTITLE="CRSTITLE";
        private static final String KEY_DEPTCD="DEPTCD";
        private static final String KEY_DEPTNAME="DEPTNAME";

        private static final String KEY_A="A";
        private static final String KEY_B="B";
        private static final String KEY_C="C";
        private static final String KEY_D="D";
        private static final String KEY_F="F";
        private static final String KEY_ADV="ADV";
        private static final String KEY_CR="CR";
        private static final String KEY_DFR="DFR";
        private static final String KEY_I="I";
        private static final String KEY_NG="NG";
        private static final String KEY_NR="NR";
        private static final String KEY_O="O";
        private static final String KEY_PR="PR";
        private static final String KEY_S="S";
        private static final String KEY_U="U";
        private static final String KEY_W="W";
        private static final String KEY_PrimaryInstructor="PrimaryInstructor";
        private static final String KEY_GradeRegs="GradeRegs";


        private Context context;
        public DatabaseHelper(Context context){
            super(context,DATBASE_NAME,null, DATABASE_VERSION);
            this.context=context;
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
