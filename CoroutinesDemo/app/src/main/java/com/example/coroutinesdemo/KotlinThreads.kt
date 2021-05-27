package com.example.coroutinesdemo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

fun main(){    //Executes in the main thread

    println("Main program starts :  ${Thread.currentThread().name}")

    thread {  //this function creates a background coroutine that runs on a background thread.
        println("Fake work starts : ${Thread.currentThread().name}")
        Thread.sleep(1000)   //pretend doing some work (eg: uploading some file)
        println("Fake work ends : ${Thread.currentThread().name}")
    }

    println("Main program ends :  ${Thread.currentThread().name}")
}
//The application will wait for all the threads to complete.(i.e the application will only be over once the threads are finished
// executing)