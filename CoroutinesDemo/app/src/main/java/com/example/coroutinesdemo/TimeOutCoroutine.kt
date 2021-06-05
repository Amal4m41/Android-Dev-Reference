package com.example.coroutinesdemo

import kotlinx.coroutines.*

//creates a coroutine that blocks the current thread(main thread) until the completion of the coroutine.
fun main() = runBlocking {    //Executes in the main thread

    println("Main program starts :  ${Thread.currentThread().name}")  //main thread


    //if the coroutine doesn't complete it's execution within 2 seconds then I'll raise exception "TimeoutCancellationException"
//    withTimeout(2000){  //coroutine builder
//            for(i in 1..500){
//                print("$i, ")
//                delay(500)
//            }
//    }
    try{
        withTimeout(2000){  //coroutine builder
                for(i in 1..500){
                    print("$i, ")
                    delay(500)
                }
        }
    }
    catch (e: TimeoutCancellationException){
        //code
    }
    finally {
        //code.
    }
    println("Main program ends :  ${Thread.currentThread().name}")   //main thread

}