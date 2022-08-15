package com.example.nickelffoxassignments_sheenu.kotlin.generic

class customer(var firstName:String,var age:Int){
}

fun <T:customer>Display(list:List<T>){
    for(i in list){
        println("FirstName is ${i.firstName} & age is ${i.age}")
    }
}
fun main(){
    var customerList= ArrayList<customer>()
    customerList.add(customer("xyz",10))
    customerList.add(customer("pqrs",20))
    customerList.add(customer("mnop",15))
    Display(customerList)

}