package com.example.nickelffoxassignments_sheenu.kotlin.scope

class Person3{
    var name="xyz"
    var age=20
    var qualification="graduation"

    fun displayInfo(){
        println("Name : $name")
        println("Age : $age")
        println("Qualification : $qualification")
    }
}
fun main() {
    val person: Person3? = null

    person?.run {

        name="pqrs"
        age=10
        displayInfo()
    }
}
