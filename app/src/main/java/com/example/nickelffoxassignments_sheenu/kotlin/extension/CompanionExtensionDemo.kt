package com.example.nickelffoxassignments_sheenu.kotlin.extension

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class MyObject{
    companion object{
        fun printObject(){
            println("This is a companion object ")
        }
    }
}
fun MyObject.Companion.printObjectName(){
    println("This is companion by name")
}
fun main(){
    MyObject.printObjectName()
}
@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.nextYearDay() {
    println("current day on $this: ${this.dayOfWeek}")
    var t1=this
    t1=this.plusYears(1)
    println("on same date $t1 day will bee: ${t1.dayOfWeek}")
}