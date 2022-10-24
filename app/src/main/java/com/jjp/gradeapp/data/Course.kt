package com.jjp.gradeapp.data

import java.io.Serializable

class Course: Serializable {
    var courAbb: String
    var courNum: Int = 0
    var courTitle: String


    constructor(courAbb: String, courNum:Int ,courTitle: String){
        this.courAbb = courAbb
        this.courNum = courNum
        this.courTitle = courTitle
    }
}