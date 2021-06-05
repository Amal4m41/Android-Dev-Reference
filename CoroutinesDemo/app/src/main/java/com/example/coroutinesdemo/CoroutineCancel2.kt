package com.example.coroutinesdemo

import kotlinx.coroutines.*
import kotlin.concurrent.thread

//creates a coroutine that blocks the current thread(main thread) until the completion of the coroutine.
fun main() = runBlocking{    //Executes in the main thread

    println("Main program starts :  ${Thread.currentThread().name}")  //main thread

    val job=launch(Dispatchers.Default) {  //inherits the thread and coroutine scope from the immediate parent coroutine
        println("Fake work starts : ${Thread.currentThread().name}") //thread main
        for(i in 1..500) {
            if (!isActive) {
                break    //or return@launch
            }
            print("$i, ")
            Thread.sleep(50)
        }
        println()
        println("Fake work ends : ${Thread.currentThread().name}")  //thread main or some other thread
    }



//    delay(2000) //block the current main thread and wait for the coroutine to finish(not a practical way to wait)

    delay(100) //print a few values before we cancel
//    job.cancel()
//    job.join()  //waits for the coroutine to finish
    job.cancelAndJoin()

    println("Main program ends :  ${Thread.currentThread().name}")   //main thread

}