package com.example.myapp

import com.example.businesslogic.PrintService
import com.example.datamodel.Message

class MyApplication {
    val greetings: String
        get() = "Hello World!"
}

fun main() {
    val printService = PrintService()
    val message = Message(MyApplication().greetings)
    printService.print(message = message)
}