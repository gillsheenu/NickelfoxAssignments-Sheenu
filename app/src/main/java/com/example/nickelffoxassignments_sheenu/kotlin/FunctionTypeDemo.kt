package com.example.nickelffoxassignments_sheenu.kotlin

fun main(){
   var fnSum=::sum
    var fnPower:(Double,Double)->Double=::power
    var fnGreeting:(String)->Unit=::greetings
    println("Sum of two number is $fnPower(2,3)")
    println("Power of a raise to b is : $fnPower(2.0,3.0)")
    fnGreeting("xyz")

}
fun sum(a:Int,b:Int):Int{
    return a+b

}
fun power(a:Double,b:Double):Double{
    return Math.pow(a,b)
}
fun greetings(s:String){
    println("Good Luck $s")
}