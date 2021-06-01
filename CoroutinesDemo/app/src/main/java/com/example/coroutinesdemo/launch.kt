package com.example.coroutinesdemo

import kotlinx.coroutines.*

fun main() =runBlocking{    //Executes in the main thread

//    runBlocking { //creates a coroutine that blocks the current thread(main thread) until the completion of the coroutine.

    println("Main program starts :  ${Thread.currentThread().name}")  //main thread

    val job: Job = launch {  //Thread: main (cuz this coroutine builder present in the scope of the runBlocking coroutine builder
        //since runBlocking runs on the main thread, this child coroutine inherits the scope of the parent coroutine
        // and therefore runs on the main thread
        println("Fake work starts : ${Thread.currentThread().name}") //thread T1
        delay(1000)  //coroutine is suspended, but the thread: main is free
        println("Fake work ends : ${Thread.currentThread().name}")  //thread: main or some other thread
    }



    job.join()// this will wait for the coroutine to be executed, only after which the next statements will be executed.
    //job.cancel() //to cancel the coroutine

    println("Main program ends :  ${Thread.currentThread().name}")   //main thread
//    }
}