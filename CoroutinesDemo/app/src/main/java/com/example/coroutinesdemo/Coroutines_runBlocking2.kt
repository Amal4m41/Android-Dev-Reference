package com.example.coroutinesdemo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() =runBlocking{    //Executes in the main thread

//    runBlocking { //creates a coroutine that blocks the current thread(main thread) until the completion of the coroutine.

        println("Main program starts :  ${Thread.currentThread().name}")  //main thread

        GlobalScope.launch {  //this function creates coroutine that runs on a background thread T1(for example)
            println("Fake work starts : ${Thread.currentThread().name}") //thread T1
            delay(1000)  //delays only this particular coroutine
            println("Fake work ends : ${Thread.currentThread().name}")  //thread T1 or some other thread
        }



        delay(2000) //block the current main thread and wait for the coroutine to finish(not a practical way to wait)

        println("Main program ends :  ${Thread.currentThread().name}")   //main thread
//    }
}