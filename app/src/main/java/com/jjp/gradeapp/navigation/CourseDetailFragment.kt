package com.jjp.gradeapp.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jjp.gradeapp.R
import com.jjp.gradeapp.adapter.CouresDetailAdapter
import com.jjp.gradeapp.adapter.DatabaseAdapter
import com.jjp.gradeapp.adapter.DepartmentAdapter
import com.jjp.gradeapp.data.Course
import com.jjp.gradeapp.data.Department
import org.w3c.dom.Text
import java.util.ArrayList

class CourseDetailFragment: Fragment() {
    lateinit var search_bar2:SearchView
    lateinit var searchResult_rcv2:RecyclerView
    var databaseAdapter: DatabaseAdapter? = null
    var coursesList= ArrayList<Course>()
    var coursesDetailAdapter: CouresDetailAdapter? = null
    lateinit var result:String
    companion object{
        var course_search:Boolean = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        result = requireArguments().getString("courseabb")!!
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_course_detail, container, false)
        searchResult_rcv2=view.findViewById(R.id.searchResult_rcv2)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        databaseAdapter = DatabaseAdapter(activity)
        course_search=false
        Log.e("courseBool", course_search.toString())
        coursesList= databaseAdapter!!.getSomeCourse(result)
        Log.e("result",result)
        searchResult_rcv2.addItemDecoration(DividerItemDecoration(view.context, 1))
        searchResult_rcv2.layoutManager = layoutManager
        searchResult_rcv2.setHasFixedSize(true)
        coursesDetailAdapter = CouresDetailAdapter(coursesList)
        searchResult_rcv2.adapter = coursesDetailAdapter
        coursesDetailAdapter!!.setOnItemClickListener(object: CouresDetailAdapter.OnItemClickListener{
            override fun onItemClick(pos: Int) {
                course_search=true
                val bundle = Bundle()
                bundle.putString("courseabb", coursesList.get(pos).courAbb)
                bundle.putString("coursenum", coursesList.get(pos).courNum.toString())
                var searchFragment = SearchFragment()
                val transaction=parentFragmentManager.beginTransaction()
                searchFragment.setArguments(bundle);
                transaction.replace(com.jjp.gradeapp.R.id.main_content,searchFragment,null).commitAllowingStateLoss()
                transaction.addToBackStack(null)
                coursesDetailAdapter!!.setOnItemClickListener(this)
            }

        })




    }


}