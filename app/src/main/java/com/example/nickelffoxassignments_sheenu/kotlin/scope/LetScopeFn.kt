package com.example.nickelffoxassignments_sheenu.kotlin.scope

class Person{
    var name:String?="xyz"
    var age=20
    var qualification="graduation"

    fun displayInfo(){
        println("Name : $name")
        println("Age : $age")
        println("Qualification : $qualification")
    }
}
fun main(){
    var person=Person()
    person.let {
        it.displayInfo()
    }


    person.name=null
    person.name?.let {
        println("person name is $it")
    }

    person.name="pqrs"
    person.name?.let {
        println("person name is $it")
    }
}