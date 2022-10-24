package com.jjp.gradeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import java.util.ArrayList
import android.view.MenuItem
import androidx.fragment.app.FragmentManager

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.jjp.gradeapp.navigation.CourseDetailFragment
import com.jjp.gradeapp.navigation.CourseFragment
import com.jjp.gradeapp.navigation.SearchFragment


class SearchPage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener, BottomNavigationView.OnNavigationItemReselectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var bottom_navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_page)
        PreCreateDB.copyDB(this)
        var searchFragment = SearchFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_content,searchFragment).commit()
        bottom_navigation = findViewById(R.id.bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener(this)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.action_search -> {
                    var searchFragment = SearchFragment()
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_content, searchFragment)
                        .commit()
                    CourseDetailFragment.course_search=false
                    return true
                }
                R.id.action_reference -> {
                    var detailViewFragment = CourseFragment()
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_content, detailViewFragment)
                        .commit()
                    return true
                }
            }
        return false
    }
    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
    override fun onNavigationItemReselected(item: MenuItem) {
        TODO("Not yet implemented")
    }
}

