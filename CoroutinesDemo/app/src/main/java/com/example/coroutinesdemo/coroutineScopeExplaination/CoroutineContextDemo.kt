package com.example.coroutinesdemo.coroutineScopeExplaination

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main()= runBlocking {
    // Thread: main
    //this: CoroutineScope instance
    //coroutineContext : CoroutineContext instance, using this property we can access the coroutineContext
    // of the current coroutine.

    //Without Parameter: CONFINED  [CONFINED DISPATCHER]
    /*
    -Inherits CoroutineContext from the immediate parent coroutine.
    -Even after delay() or suspending  function, it continues to run on the same thread.(i.e the coroutine remains
    confined/restricted to the same thread, the thread which is assigned to a coroutine is decided by the dispatcher,
    so that's why the dispatcher of such coroutine is called as the CONFINED DISPATCHER)
     */
    launch {
        //this coroutine inherits the coroutineContext from the immediate parent coroutine, which means it's going to be
        //launched in the main thread, which is the thread inherited from the runBlocking coroutine.
        println("C1 : ${Thread.currentThread().name}")  //Thread: main
        delay(1000)
        println("C1 after delay: ${Thread.currentThread().name}")  //Thread: main
        //The execution of suspending function (eg: delay()) will not affect the thread of the coroutine or it's subsequent
        //code
    }

    //With parameter: dispatcher.default [similar to GlobalScope.launch { }]
    /*
    -Gets it's own context at Global level. Executes in a separate background thread.
    -After delay() or suspending  function execution, it continues to run in the same thread or some other thread.
 */
    launch(Dispatchers.Default) {
        //Coroutine is created at the application level and will be executed on a separate background thread,
        //just like GlobalScope.launch()
        println("C2 : ${Thread.currentThread().name}") //Thread: T1
        delay(1000)
        println("C2 after delay: ${Thread.currentThread().name}") //Thread: T1 or some other thread
    }

    //With parameter: Dispatchers.Unconfined    [UNCONFINED DISPATCHER]
    /*
-Inherits CoroutineContext from the immediate parent coroutine.
-But after delay() or suspending  function execution, it continues to run on the same thread or in some other thread.
 */
    launch(Dispatchers.Unconfined) {
        //This coroutine will inherit the coroutine context from the immediate parent(therefore will execute on
        //main thread).
        println("C3 : ${Thread.currentThread().name}") //Thread: main
        delay(1000)
        println("C3 after delay: ${Thread.currentThread().name}") //Thread: some other thread(eg T2)


        //Using coroutineContext property to flow context from parent to child.
        launch(coroutineContext) {
            //coroutineContext is the property of the runBlocking coroutine, that is the immediate parent, when we pass the
            //coroutineContext property to a child coroutine for launch, we pass the context of parent to the child,
            //therefore it'll be executed on the same "main" thread.
            //This works just like the CONFINED DISPATCHER
            println("C3 child : ${Thread.currentThread().name}")  //Thread: T2
            delay(1000)
            println("C3 child after delay: ${Thread.currentThread().name}")  //Thread: T2
            //The execution of suspending function (eg: delay()) will not affect the thread of the coroutine or it's subsequent
            //code
        }
    }


    //Using coroutineContext property to flow context from parent to child.
    launch(coroutineContext) {
        //coroutineContext is the property of the runBlocking coroutine, that is the immediate parent, when we pass the
        //coroutineContext property to a child coroutine for launch, we pass the context of parent to the child,
        //therefore it'll be executed on the same "main" thread.
        //This works just like the CONFINED DISPATCHER
        println("C4 : ${Thread.currentThread().name}")  //Thread: main
        delay(1000)
        println("C4 after delay: ${Thread.currentThread().name}")  //Thread: main
        //The execution of suspending function (eg: delay()) will not affect the thread of the coroutine or it's subsequent
        //code
    }


    println(".. Main program..")
}