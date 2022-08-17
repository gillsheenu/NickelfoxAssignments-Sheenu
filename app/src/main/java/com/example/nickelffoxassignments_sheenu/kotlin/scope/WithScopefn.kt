package com.example.nickelffoxassignments_sheenu.kotlin.scope

class Person2{
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
    val person: Person2? = null

    //This shows an error
//    with(person){
//        name="pqrs"
//        age=10
//        displayInfo()
//    }

    with(person) {
        this?.name = "xyz"
        this?.age = 10
        this?.displayInfo()
    }
}

