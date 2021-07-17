package com.example.coroutinesdemo

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//every coroutine builder has a CoroutineScope object/instance attached to it.
//irrespective of being a child or parent coroutine, each coroutine builder has it's own unique CoroutineScope instance
//
fun main() = runBlocking {
    println("runBlocking : $this")  //the signature shows what type of coroutine it is(eg: BlockingCoroutine, Standalone etc)

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

/*OUTPUT:
runBlocking : BlockingCoroutine{Active}@30946e09  //Active shows that when the statement was executed, the coroutine was in active state
..some other code..
launch : StandaloneCoroutine{Active}@1cf4f579
async : DeferredCoroutine{Active}@18769467
child launch : StandaloneCoroutine{Active}@46ee7fe8
 */