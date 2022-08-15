package com.example.nickelffoxassignments_sheenu.kotlin.extension

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

fun main(){
    println("hello Everyone".formattedString())
    printClassName(Rectangle())
}
