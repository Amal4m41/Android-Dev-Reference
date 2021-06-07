package com.example.coroutinesdemo.KotlinCoroutinesExplaination

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main()= runBlocking{//Creates a blocking coroutine that executes in the current thread(main)    
    println("Main program starts : ${Thread.currentThread().name}")   //main thread


    //If we wrap these two functions within async or launch coroutine builder functions, we'll be able to execute them in
    //concurrent manner.
//    val time= measureTimeMillis{
//        val msg1= getMessageOne()
//        val msg2= getMessageTwo()
//        println("The entire message is : $msg1$msg2")
//    }
    val time = measureTimeMillis {

        val msg1:Deferred<String> = async {
            getMessage1()
        }  //creates a child coroutine within the scope of the runBlocking parent coroutine and it's thread(main thread)
        val msg2:Deferred<String> = async {
            getMessage2()
        } //creates a child coroutine within the scope of the runBlocking parent coroutine and it's thread(main thread)
        //Therefore these functions are executed in a separate background coroutine, i.e. these two child coroutines
        //are getting executed in concurrent manner.
        println("The entire message is : ${msg1.await()}${msg2.await()}")
    }

    println("Time : $time")  //1036 ms   //

    println("Main program ends : ${Thread.currentThread().name}")   //main thread

    //Within a coroutine the methods are by default executed in sequence(top to bottom).
}

private suspend fun getMessage1():String{
    delay(1000L) //pretend to do some work
    return "Hello "
}

private suspend fun getMessage2():String{
    delay(1000L) //pretend to do some work
    return "World!"
}



//fun demo()=demo2{
////    s:String->print(s)
//    print(it)
//}
//
//
//fun demo2(action:(String)->Unit){
//    action("LOL")
//}