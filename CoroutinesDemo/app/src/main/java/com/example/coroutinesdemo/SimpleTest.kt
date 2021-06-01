package com.example.coroutinesdemo

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

//creating class to write the test case
class SimpleTest {

//    @Test
//    fun myFirstTest(){
//        Assert.assertEquals(10,5+5)
//
//    }
    @Test
    fun myFirstTest(){
    //we use the runBlocking to test the suspending function since the suspending function can be
    //called only inside of a coroutine or another suspending function
        runBlocking {
            myOwnSuspendFunction()
        }
    }
    //runBlocking is a coroutine builder that is going to launch a coroutine, this coroutine is going to
    //block the current THREAD on which it's operating and therefore will help us to execute the suspending
    //function
}