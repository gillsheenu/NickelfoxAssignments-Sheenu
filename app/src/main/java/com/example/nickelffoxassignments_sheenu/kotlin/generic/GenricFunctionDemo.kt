package com.example.nickelffoxassignments_sheenu.kotlin.generic

fun <T> middleItem(list:List<T>):T{
    return list[list.size/2]
}
fun main(){

    var middleInteger= middleItem(listOf(1,2,3,4,5))
    println(middleInteger)

    var middleString=middleItem(listOf("one","Two","Three"))
    println(middleString)
}