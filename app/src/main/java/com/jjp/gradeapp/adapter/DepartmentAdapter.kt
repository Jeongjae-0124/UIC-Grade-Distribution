package com.jjp.gradeapp.adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jjp.gradeapp.R
import com.jjp.gradeapp.data.Department
import java.util.*
import android.widget.Filter.FilterResults

class DepartmentAdapter(var departmentList:ArrayList<Department>) :
    RecyclerView.Adapter<DepartmentAdapter.CustomViewHolder>(), Filterable {
    companion object{
        var filteredDepartment = ArrayList<Department>()
    }
    var itemFilter = ItemFilter()

    var context: Context? = null
    var searchResult_rcv: RecyclerView? = null
    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int) //you can pass whatever information you need from the item that will be clicked, here, i want to further use his position in the adapter's list
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    init {
        filteredDepartment.addAll(departmentList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.department_item, parent, false)
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener(View.OnClickListener {
                if (mListener != null) {
                    val position: Int = getAdapterPosition()
                    if (position != RecyclerView.NO_POSITION) {
                        mListener!!.onItemClick(position)
                    }
                }
            })

        }

    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.DepartmentName.text = filteredDepartment!!.get(position).deptName
        holder.DepartmentAbb.text = "(" + filteredDepartment!!.get(position).courAbb + ")"
    }

    override fun getItemCount(): Int {
        return filteredDepartment.size
    }

    override fun getFilter(): Filter {
        return itemFilter
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val DepartmentName: TextView = itemView.findViewById(R.id.course_name2)
        val DepartmentAbb: TextView = itemView.findViewById(R.id.course_abb2)
    }

    inner class ItemFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            var filterString = charSequence.toString()
            filterString=filterString.lowercase()
            val results = FilterResults()
            Log.d(TAG, "charSequence : $charSequence")

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<Department> = ArrayList<Department>()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = departmentList
                results.count = departmentList.size

                return results
                //공백제외 2글자 이인 경우 -> 이름으로만 검색
            } else if (filterString.trim { it <= ' ' }.length <= 2) {
                for (department in departmentList) {
                    if (department.courAbb.lowercase().contains(filterString)) {
                        filteredList.add(department)
                        Log.e("filter",filteredList.toString())
                    }
                }
                //그 외의 경우(공백제외 2글자 초과) -> 이름/전화번호로 검색
            } else {
                for (department in departmentList) {
                    if (department.courAbb.lowercase().contains(filterString) || department.deptName.lowercase().contains(filterString)) {
                        filteredList.add(department)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            filteredDepartment.clear()
            filteredDepartment.addAll(filterResults.values as ArrayList<Department>)
            notifyDataSetChanged()
        }
    }
}


