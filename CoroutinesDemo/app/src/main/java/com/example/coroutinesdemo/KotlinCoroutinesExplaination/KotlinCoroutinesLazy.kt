package com.example.coroutinesdemo.KotlinCoroutinesExplaination

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main()= runBlocking{//Creates a blocking coroutine that executes in the current thread(main)    
    println("Main program starts : ${Thread.currentThread().name}")   //main thread


    val msg1:Deferred<String> = async(start = CoroutineStart.LAZY) {
        getMessage1()
    }  //creates a child coroutine within the scope of the runBlocking parent coroutine and it's thread(main thread)
    val msg2:Deferred<String> = async(start = CoroutineStart.LAZY) {
        getMessage2()
    } //creates a child coroutine within the scope of the runBlocking parent coroutine and it's thread(main thread)

    println("The entire message is : ${msg1.await()}${msg2.await()}")
    /*
    Even if we comment the above line i.e. even if we're not using the results of the suspending functions they'll be executed
    and therefore it'll be wasting the system resources.
    To optimize the code we'll only make the coroutine execute if we use the result from it in our program.
    To do this we add the coroutine lazy  parameter to the async coroutine builder.
    i.e. only when the msg1.await() then only it starts the async coroutine builder and execute the code in it.
    If the second async was also lazily executed then only after msg2.await() is executed the coroutine will start and
    execute it's code... i.e. one after the other both the coroutine will start and execute it's code(i.e. first start the
    msg1.await() and finish executing it, then msg2.await() will start it's coroutine and finish executing it..sequential here)
     */

    println("Main program ends : ${Thread.currentThread().name}")   //main thread

    //Within a coroutine the methods are by default executed in sequence(top to bottom).
}

private suspend fun getMessage1():String{
    delay(1000L) //pretend to do some work
    println("After working in getMessage1()")
    return "Hello "
}

private suspend fun getMessage2():String{
    delay(1000L) //pretend to do some work
    println("After working in getMessage2()")
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