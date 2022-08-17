package com.example.nickelffoxassignments_sheenu.kotlin.extension

fun String?.printLength():Int{
    if(this==null) return 0
    return this.length

}

fun main(){
    var name:String?=null
    println("Length of name is ${name.printLength()}")

    name="xyzpqrs"
    println("Length of name is ${name.printLength()}")

}