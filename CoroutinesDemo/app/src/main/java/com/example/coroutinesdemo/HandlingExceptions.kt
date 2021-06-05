package com.example.coroutinesdemo

import kotlinx.coroutines.*

//creates a coroutine that blocks the current thread(main thread) until the completion of the coroutine.
fun main() = runBlocking{    //Executes in the main thread

    println("Main program starts :  ${Thread.currentThread().name}")  //main thread

    val job=launch {  //inherits the thread and coroutine scope from the immediate parent coroutine
        println("Fake work starts : ${Thread.currentThread().name}") //thread main
        try {
            for(i in 1..500){
                print("$i, ")
                Thread.sleep(50)
                yield()  //will throw CancellationException upon the cancellation of this coroutine.
            }
        }
        catch (e: CancellationException){
            println("\nException caught safely : ${e.message}")
        }
        finally {
            //To run suspending functions inside finally we need to use withContext:
            //delay(100) //the lines below this code won't be executed as this suspending fn will throw an exception.
            withContext(NonCancellable){
                delay(100)
            }
            println("Close all resources in finally")
        }
        println("Fake work ends : ${Thread.currentThread().name}")  //thread main or some other thread
    }



//    delay(2000) //block the current main thread and wait for the coroutine to finish(not a practical way to wait)

    delay(1000) //print a few values before we cancel
//    job.cancel()
//    job.join()  //waits for the coroutine to finish
//    job.cancelAndJoin()
    job.cancel(CancellationException("My own crash message"))
    println("Main program ends :  ${Thread.currentThread().name}")   //main thread

}