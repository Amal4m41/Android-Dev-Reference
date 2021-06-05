package com.example.coroutinesdemo

import kotlinx.coroutines.*

//creates a coroutine that blocks the current thread(main thread) until the completion of the coroutine.
fun main() = runBlocking{    //Executes in the main thread

    println("Main program starts :  ${Thread.currentThread().name}")  //main thread

    val job=launch {  //inherits the thread and coroutine scope from the immediate parent coroutine
        println("Fake work starts : ${Thread.currentThread().name}") //thread main
        for(i in 1..500){
            print("$i, ")
//            delay(50)
            yield()  //or use delay() or any other suspending function to make this coroutine cooperative(so that it's cancellable)
        }
        println()
        println("Fake work ends : ${Thread.currentThread().name}")  //thread main or some other thread
    }



//    delay(2000) //block the current main thread and wait for the coroutine to finish(not a practical way to wait)

    delay(1000) //print a few values before we cancel
//    job.cancel()
//    job.join()  //waits for the coroutine to finish
    job.cancelAndJoin()

    println("Main program ends :  ${Thread.currentThread().name}")   //main thread

}