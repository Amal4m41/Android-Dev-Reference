package com.example.coroutinesdemo.KotlinCoroutinesExplaination

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main()= runBlocking{//Creates a blocking coroutine that executes in the current thread(main)    
    println("Main program starts : ${Thread.currentThread().name}")   //main thread


    val time= measureTimeMillis{
        val msg1= getMessageOne()
        val msg2= getMessageTwo()
        println("The entire message is : $msg1$msg2")
    }


    println("Time : $time")  //2034 ms

    println("Main program ends : ${Thread.currentThread().name}")   //main thread

    //Within a coroutine the methods are by default executed in sequence(top to bottom).
}

suspend fun getMessageOne():String{
    delay(1000L) //pretend to do some work
    return "Hello "
}

suspend fun getMessageTwo():String{
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