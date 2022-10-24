package com.jjp.gradeapp.data

import java.io.Serializable

class Department: Serializable {
    var courAbb: String
    var deptName: String


    constructor(courAbb: String, deptName: String){
        this.courAbb = courAbb
        this.deptName = deptName
    }
}