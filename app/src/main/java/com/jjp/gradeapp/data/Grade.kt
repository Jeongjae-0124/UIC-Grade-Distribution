package com.jjp.gradeapp.data

import java.io.Serializable

class Grade  : Serializable{
    var courAbb:String
    var courNum:Int=0
    var courTitle:String
    var deptCd:Int=0
    var deptName:String
    var aNum:Int=0
    var bNum:Int=0
    var cNum:Int=0
    var dNum:Int=0
    var fNum:Int=0;
    var advance:Int=0;
    var credit:Int=0;
    var deferred:Int=0;
    var incomplete:Int=0;
    var nonGrade:Int=0;
    var notReported:Int=0;
    var outstanding:Int=0;
    var proficient:Int=0;
    var satis:Int=0;
    var unsatis:Int=0;
    var withdraw:Int=0
    var instructor:String
    var regs:Int=0;

    constructor(courAbb: String,courNum:Int, courTitle:String,deptCd:Int,
                deptName:String,aNum:Int,bNum:Int, cNum:Int,dNum:Int,fNum:Int,
                advance:Int,credit:Int,deferred:Int, incomplete:Int, nonGrade:Int,
                notReported:Int,outstanding:Int,proficient:Int,satis:Int,unsatis:Int,
                withdraw:Int, instructor:String, regs:Int){
        this.courAbb=courAbb
        this.courNum=courNum
        this.courTitle=courTitle
        this.deptCd=deptCd
        this.deptName=deptName
        this.aNum=aNum
        this.bNum=bNum
        this.cNum=cNum
        this.dNum=dNum
        this.fNum=fNum
        this.advance=advance
        this.credit=credit
        this.deferred=deferred
        this.incomplete=incomplete
        this.nonGrade=nonGrade
        this.notReported=notReported
        this.outstanding=outstanding
        this.proficient=proficient
        this.satis=satis
        this.unsatis=unsatis
        this.withdraw=withdraw
        this.instructor=instructor
        this.regs=regs
    }










}