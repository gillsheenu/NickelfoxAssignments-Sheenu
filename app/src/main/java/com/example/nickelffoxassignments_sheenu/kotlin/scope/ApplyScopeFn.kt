package com.example.nickelffoxassignments_sheenu.kotlin.scope

class Company() {
    lateinit var name: String
    lateinit var objective: String
    lateinit var founder: String
}


fun main() {

    val company = Company().apply {

        name = "xyz"
        objective = "software Development"
        founder = "pqrs"

    }
    println(company.name)

}