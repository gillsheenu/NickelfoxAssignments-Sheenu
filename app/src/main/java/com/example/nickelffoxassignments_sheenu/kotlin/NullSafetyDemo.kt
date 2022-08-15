package com.example.nickelffoxassignments_sheenu.kotlin

fun main(args: Array<String>) {
    // created a list contains names
    var stringList: List<String?> = listOf("abc","xyz", null, "PQRS")

    // created new list
    var newList = listOf<String?>()

    for (item in stringList) {
        // executes only for non-nullable values
        item?.let { newList = newList.plus(it) }
    }
    //  print the elements stored in newlist
    for(items in newList){
        println(items)
    }

    /* Program for elvis Operator */

    var str:String?="xyzPQRS"

    //string is not empty
    println(str?.length)


    str=null
    //String is empty
    println(str?.length ?: "its a emptyString")


    /*program for not null assertion !! operator */

    var str2:String?="xyzPQRS"
    println(str2!!.length)

    str2=null
//    println(str2!!.length)


    /*program for safe Cast */

    val obj:Any=getValue("2")
    var casted=obj as? Int
    var casted2 =obj as? String

    println("result of casted is : $casted")
    println("result of casted2 is : $casted2")
}

fun getValue(value:String):Any {
    return when (value) {
        "1" -> 100
        "2" -> "hello"
        "3" -> true
        "4" -> 22.22
        else -> false
    }
}
