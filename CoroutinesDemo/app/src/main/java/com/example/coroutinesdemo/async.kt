package com.example.coroutinesdemo

import kotlinx.coroutines.*

fun main() =runBlocking{    //Executes in the main thread

//    runBlocking { //creates a coroutine that blocks the current thread(main thread) until the completion of the coroutine.

    println("Main program starts :  ${Thread.currentThread().name}")  //main thread

    //run a local coroutine
    //deferred is subclass of job
//    val jobDeferred: Deferred<String> = async { //Thread: main (cuz this coroutine builder present in the scope of the runBlocking coroutine builder
//        //since runBlocking runs on the main thread, this child coroutine inherits the scope of the parent coroutine
//        // and therefore runs on the main thread
//        println("Fake work starts : ${Thread.currentThread().name}") //thread T1
//        delay(1000)  //coroutine is suspended, but the thread: main is free
//        println("Fake work ends : ${Thread.currentThread().name}")  //thread: main or some other thread
//        "returning this string value"
//    }

    //runs a global coroutine
    val jobDeferred: Deferred<String> = GlobalScope.async { //Thread: main (cuz this coroutine builder present in the scope of the runBlocking coroutine builder
        //since runBlocking runs on the main thread, this child coroutine inherits the scope of the parent coroutine
        // and therefore runs on the main thread
        println("Fake work starts : ${Thread.currentThread().name}") //thread T1
        delay(1000)  //coroutine is suspended, but the thread: main is free
        println("Fake work ends : ${Thread.currentThread().name}")  //thread: main or some other thread
        "returning this string value"
    }


    //jobDeferred.join() is used when we just want to wait for the coroutine to finish and not returning any value.
//    jobDeferred.join()// this will wait for the coroutine to be executed, only after which the next statements will be executed.
    val stringValue:String=jobDeferred.await()  //suspending function that waits for the coroutine to finish and returns the value
    println(stringValue)

    println("Main program ends :  ${Thread.currentThread().name}")   //main thread
//    }
}


//using runBlocking for test case
suspend fun myOwnSuspendFunction(){
    delay(1000)
}