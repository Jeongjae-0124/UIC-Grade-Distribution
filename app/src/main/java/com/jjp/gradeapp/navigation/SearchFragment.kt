package com.jjp.gradeapp.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.jjp.gradeapp.*
import com.jjp.gradeapp.adapter.DatabaseAdapter
import com.jjp.gradeapp.data.Course
import com.jjp.gradeapp.data.Grade
import java.util.ArrayList

class SearchFragment: Fragment() {
    lateinit var term_spinner: Spinner
    lateinit var year_spinner: Spinner
    lateinit var courseAbb_edit: EditText
    lateinit var courseNum_edit: EditText
    var databaseAdapter: DatabaseAdapter? = null
    var gradesList = ArrayList<Grade>()
    companion object{
        lateinit var term:String
        lateinit var year:String
        lateinit var course_abb:String
        lateinit var course_number:String
    }
    lateinit var search_button: Button
    lateinit var result_courseAbb:String
    lateinit var result_courseNum:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view=LayoutInflater.from(activity).inflate(R.layout.fragment_search,container,false)
        term_spinner=view.findViewById(R.id.term_spinner)
        year_spinner=view.findViewById(R.id.year_spinner)
        courseAbb_edit=view.findViewById(R.id.courseAbb_edit)
        courseNum_edit=view.findViewById(R.id.courseNum_edit)
        search_button=view.findViewById(R.id.search_button)
        if(CourseDetailFragment.course_search==true){
            result_courseAbb = requireArguments().getString("courseabb")!!
            result_courseNum = requireArguments().getString("coursenum")!!
            courseAbb_edit.setText(result_courseAbb);
            courseNum_edit.setText(result_courseNum)
            CourseDetailFragment.course_search==false
        }

        val termItem = resources.getStringArray(R.array.term_array)
        val termAdapter= ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, termItem)
        val yearItem= resources.getStringArray(R.array.year_array)
        val yearAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, yearItem)

        term_spinner.adapter=termAdapter
        year_spinner.adapter=yearAdapter
        course_abb =courseAbb_edit.text.toString()
        course_number =courseNum_edit.text.toString()
        term_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        term ="sp"

                    }
                    1 -> {
                        term ="su"

                    }
                    2 -> {
                        term ="fa"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }

        year_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        year ="10"

                    }
                    1 -> {
                        year ="11"
                    }
                    2 -> {
                        year ="12"
                    }
                    3 -> {
                        year ="13"
                    }
                    4 -> {
                        year ="14"
                    }
                    5 -> {
                        year ="15"
                    }
                    6 -> {
                        year ="16"
                    }
                    7 -> {
                       year ="17"
                    }
                    8 -> {
                        year ="18"
                    }
                    9 -> {
                        year ="19"
                    }
                    10 -> {
                        year ="20"
                    }
                    11 -> {
                        year ="21"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }


        search_button.setOnClickListener({
            if (courseAbb_edit.text.toString().length==0
                ||courseNum_edit.text.toString().length==0) {
                var toast=Toast.makeText(getActivity(),"ERROR: Enter the course", Toast.LENGTH_SHORT)
//                val view = toast.view
//                view?.setBackgroundColor(Color.BLACK)
//                val group = toast.view as ViewGroup
//                val msgTextView = group.getChildAt(0) as TextView
//                msgTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
//                msgTextView.setTextColor(Color.WHITE)
                toast.show()

            }
            else {
                course_number =courseNum_edit.text.toString()
                course_abb = courseAbb_edit.text.toString().toUpperCase()
                databaseAdapter =
                    DatabaseAdapter(getActivity())
                gradesList = databaseAdapter!!.getSomeGrades(course_abb, course_number.toInt())
                Log.e("gradesList",gradesList.size.toString())
                if(gradesList.size>1){
                    val intent = Intent(activity, GradeListPage::class.java)
                    intent.putExtra("term", term)
                    intent.putExtra("year", year)
                    intent.putExtra("course_abb", course_abb)
                    intent.putExtra("course_number", course_number)
                    startActivity(intent)
                }
                if(gradesList.size==1){
                    val intent = Intent(activity, GradeDetail::class.java)
                    val gradeSelected = gradesList[0]
                    intent.putExtra("gradeSelected",gradeSelected)
                    startActivity(intent)

                }

                if(gradesList.size==0){
                    var toast=Toast.makeText(activity,"ERROR: Could not find the course", Toast.LENGTH_SHORT)
//                    val view = toast.view
//                    val group = toast.view as ViewGroup
//                    group.setBackgroundColor(Color.BLACK)
//                    val msgTextView = group.getChildAt(0) as TextView
//                    msgTextView.setTextColor(Color.WHITE)
//                    msgTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
                    toast.show()

                }
            }


        })

        return view
    }


}