package com.example.nickelffoxassignments_sheenu.kotlin.scope

fun main() {
    val list = mutableListOf<Int>(1, 2, 3)


    list.also {
        it.add(4)
        it.remove(2)

    }
    println(list)
}
