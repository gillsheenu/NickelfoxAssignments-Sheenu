package com.example.nickelffoxassignments_sheenu.kotlin.generic

//fun <T> genericsExample(value: T) {
//    println(value)                                 /* Error-cannot use'T' as reified Type*/
//    println("Type of T: ${T::class.java}")
//}

inline fun <reified T> genericsExample(value: T) {
    println(value)
    println("Type of T: ${T::class.java}")
}
fun main() {
    genericsExample<String>("Learning Generics!")
    genericsExample<Int>(100)
}