package com.example.coroutinesdemo

import kotlinx.coroutines.*

//creates a coroutine that blocks the current thread(main thread) until the completion of the coroutine.
fun main() = runBlocking {    //Executes in the main thread

    println("Main program starts :  ${Thread.currentThread().name}")  //main thread



    //unlike withTimeout() this one doesn't throw exception instead return Null in case the coroutine fails to finish
    //executing within the given time.
    val value:String? = withTimeoutOrNull(2000){  //coroutine builder
        for(i in 1..500){
            print("$i, ")
//            delay(500)
        }
        "\nreturned string value"
    }
    println(value)

    println("Main program ends :  ${Thread.currentThread().name}")   //main thread

}