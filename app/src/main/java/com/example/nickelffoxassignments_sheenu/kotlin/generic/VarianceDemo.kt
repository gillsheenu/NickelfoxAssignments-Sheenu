package com.example.nickelffoxassignments_sheenu.kotlin.generic

open class Phone(var os:String){
    fun phoneType(){
        println("Current PhoneType is : $os ")
    }
}
class Android: Phone("Android"){
}
class Ios:Phone("Ios"){
}

class PhoneProducer<out T>(){
}
class PhoneConsumer<in T>{
}


fun main() {


    var producer: PhoneProducer<Phone> = PhoneProducer<Ios>()
    var producer1: PhoneProducer<out Phone> = PhoneProducer<Android>()

//    var producer2:PhoneProducer<Android> = PhoneProducer<Phone>()  Error-Type MisMatch


    var consumer: PhoneConsumer<Android> = PhoneConsumer<Phone>()

//    var consumer1:PhoneConsumer<Phone> = PhoneConsumer<Android>()   Error-TypeMisMatch
}
