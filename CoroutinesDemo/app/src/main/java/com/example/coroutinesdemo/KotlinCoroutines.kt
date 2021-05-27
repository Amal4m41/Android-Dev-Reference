package com.example.coroutinesdemo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

fun main(){    //Executes in the main thread

    println("Main program starts :  ${Thread.currentThread().name}")

    GlobalScope.launch {  //this function creates a background thread(worker thread)
        println("Fake work starts : ${Thread.currentThread().name}")
        Thread.sleep(1000)   //pretend doing some work (eg: uploading some file)
        println("Fake work ends : ${Thread.currentThread().name}")
    }

    Thread.sleep(2000) //block the current main thread and wait for the coroutine to finish(not a practical way to wait)
    println("Main program ends :  ${Thread.currentThread().name}")
}
//Unlike threads, for coroutines the application by default does not wait for it to finish its execution.