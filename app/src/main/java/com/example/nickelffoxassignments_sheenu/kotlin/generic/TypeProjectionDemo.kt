package com.example.nickelffoxassignments_sheenu.kotlin.generic

fun copy(array1: Array<out Any>, array2: Array<Any>) {

    for (i in array1.indices)
        array2[i] = array1[i]

    for (i in array2.indices) {
        println(array2[i])
    }
}
fun main(args :Array<String>) {
    val intArray: Array<Int> = arrayOf(1, 2, 3)
    val anyArray :Array<Any> = Array<Any>(3) { "" }
    copy(intArray, anyArray)

}
