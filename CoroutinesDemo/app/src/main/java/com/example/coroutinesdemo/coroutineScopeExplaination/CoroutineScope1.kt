package com.example.coroutinesdemo.coroutineScopeExplaination

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//every coroutine builder has a CoroutineScope object/instance attached to it.
//irrespective of being a child or parent coroutine, each coroutine builder has it's own unique CoroutineScope instance

fun main() = runBlocking {
    println("runBlocking : $this")

    launch {
        println("launch : $this")
        launch {
            println("child launch : $this")
        }
    }
    async {
        println("async : $this")
    }

    println("..some other code..")
}