package com.example.nickelffoxassignments_sheenu.kotlin.generic

fun main(){
    var array= arrayOf(1,2,3,null,6)
    printArray(array)
}

fun printArray(arr:Array<*>){
    for(i in arr){
        println(i)
    }
}