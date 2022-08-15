package com.example.nickelffoxassignments_sheenu.kotlin.extension

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