package com.jjp.gradeapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jjp.gradeapp.R
import com.jjp.gradeapp.data.Course
import com.jjp.gradeapp.data.Department
import java.util.*

class CouresDetailAdapter(private val coursesList:ArrayList<Course>) : RecyclerView.Adapter<CouresDetailAdapter.CustomViewHolder>() {

    var context: Context? = null
    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int) //you can pass whatever information you need from the item that will be clicked, here, i want to further use his position in the adapter's list
    }


    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.coursedetail_item,parent,false)
        return CustomViewHolder(view).apply{
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

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.CourseAbb.text= coursesList!!.get(position).courAbb
        holder.CourseNum.text= coursesList!!.get(position).courNum.toString()+":"
        holder.CourseTitle.text= coursesList!!.get(position).courTitle
    }

    override fun getItemCount(): Int {
        return coursesList!!.size
    }


    class CustomViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val CourseAbb:TextView=itemView.findViewById(R.id.course_abb)
        val CourseNum:TextView=itemView.findViewById(R.id.course_num)
        val CourseTitle:TextView=itemView.findViewById(R.id.course_title)


    }




}