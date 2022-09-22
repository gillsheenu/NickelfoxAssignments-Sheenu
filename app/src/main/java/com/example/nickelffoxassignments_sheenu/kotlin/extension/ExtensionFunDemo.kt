package com.example.nickelffoxassignments_sheenu.kotlin.extension

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

fun String.formattedString():String{
    return "-------------------------\n$this\n------------------------"
}
open class Shape
class Rectangle: Shape()

fun Shape.getName() = "Shape"
fun Rectangle.getName() = "Rectangle"

fun printClassName(s: Shape) {
    println(s.getName())
}

val Rectangle.side:Int
    get()=10

@RequiresApi(Build.VERSION_CODES.O)
fun main(){
    println("hello Everyone".formattedString())
    printClassName(Rectangle())
    println(Rectangle().side)
    var t1= LocalDate.now()
    t1.nextYearDay()


}
