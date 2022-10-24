package com.jjp.gradeapp.navigation

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jjp.gradeapp.R
import com.jjp.gradeapp.adapter.DatabaseAdapter
import com.jjp.gradeapp.adapter.DepartmentAdapter
import java.util.ArrayList
import com.jjp.gradeapp.data.Department
import java.util.Locale.filter




class CourseFragment: Fragment(){
    private lateinit var searchResult_rcv: RecyclerView
    var databaseAdapter: DatabaseAdapter? = null
    var departmentList=ArrayList<Department>()
    lateinit var search_bar:SearchView
    var departmentAdapter: DepartmentAdapter? = null

    //    private lateinit var courseList:ArrayList<Course>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(com.jjp.gradeapp.R.layout.fragment_course, container, false)
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        databaseAdapter = DatabaseAdapter(activity)
        departmentList = databaseAdapter!!.someDepart
        search_bar=view.findViewById(R.id.search_bar)
        search_bar.setOnQueryTextListener(searchViewTextListener)
        searchResult_rcv = view.findViewById(com.jjp.gradeapp.R.id.searchResult_rcv)
        searchResult_rcv.addItemDecoration(DividerItemDecoration(view.context, 1))
        searchResult_rcv.layoutManager = layoutManager
        searchResult_rcv.setHasFixedSize(true)
        departmentAdapter = DepartmentAdapter(departmentList)
        searchResult_rcv.adapter = departmentAdapter
        departmentAdapter!!.setOnItemClickListener(object: DepartmentAdapter.OnItemClickListener{
            override fun onItemClick(pos: Int) {
                val bundle = Bundle()
                bundle.putString("courseabb", DepartmentAdapter.filteredDepartment.get(pos).courAbb)

                var courseDetailFragment = CourseDetailFragment()
                val transaction=parentFragmentManager.beginTransaction()
                courseDetailFragment.setArguments(bundle);
                transaction.replace(com.jjp.gradeapp.R.id.main_content,courseDetailFragment,null).commitAllowingStateLoss()
                transaction.addToBackStack(null)
                departmentAdapter!!.setOnItemClickListener(this)
            }

        })

    }
    var searchViewTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            //텍스트 입력/수정시에 호출
            override fun onQueryTextChange(s: String): Boolean {
                departmentAdapter?.filter?.filter(s)
                return false
            }
        }


}



//