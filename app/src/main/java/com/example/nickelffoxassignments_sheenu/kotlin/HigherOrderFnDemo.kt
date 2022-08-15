package com.example.nickelffoxassignments_sheenu.kotlin


fun main(){
    calculatorFun(2.0,3.5,::additionFun)
    calculatorFun(3.5,2.0){a:Double,b:Double->a-b}
    var result= multiplicationFun(2,3){x:Int,y:Int->x*y}
    var final=result()
    println(final)
}

fun additionFun(a:Double,b:Double):Double{
    return a+b
}
inline fun calculatorFun(a:Double,b:Double,fn:(Double,Double)->Double){
    var result=fn(a,b)
    println(result)
}
fun multiplicationFun(a:Int,b:Int,gn:(Int,Int)->Int):()->Int{
    var result=gn(a,b)
    return {result*result}
}


