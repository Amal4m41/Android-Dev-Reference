package com.example.coroutineexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class MainActivity3 : AppCompatActivity() {

    val TAG="Check"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val button=findViewById<Button>(R.id.Button)

        button.setOnClickListener {
            Toast.makeText(this, "CLICKED BUTTON", Toast.LENGTH_SHORT).show()

//            runBlocking {
//                Log.e(TAG, "Thread : ${Thread.currentThread().name}") //main thread
//                val job=async {networkCall()}
//                val stringValue=job.await()  //will block the main thread until this result it obtained(therefore blocks
//                //the UI update )
//                Log.e(TAG, stringValue )
//            }

            lifecycleScope.launch(Dispatchers.IO) {
                //CoroutineScope tied to this LifecycleOwner's Lifecycle.
                //This scope will be cancelled when the Lifecycle is destroyed
                Log.e(TAG, "Thread : ${Thread.currentThread().name}") //main thread
                val job=async {networkCall()}
                val stringValue=job.await()
                Log.e(TAG, stringValue )
            }
        }

/*
        Log.e(TAG, "Before Coroutine : ${Thread.currentThread().name}", )
        GlobalScope.launch(Dispatchers.Default) {
            //Starts in the global scope, will exist till the life of the application.
            //ie. If the coroutine finishes it's job, it will be destroyed and not kept alive until the application dies.
            // Otherwise if the job is not finished and takes a lot of time it'll keep running until we close the application.
            //eg: if we run this code and close the application before 5 seconds then networkCall() won't be completed and
            // all the coroutines and threads will be cancelled.
            //  IF THE MAIN THREAD FINISHES IT'S WORK, ALL THE OTHER THREAD AND COROUTINES WILL BE CANCELLED.
            val result=networkCall()
            Log.e(TAG, "Inside Coroutine +$result : ${Thread.currentThread().name}", )

        }
        Log.e(TAG, "After Coroutine : ${Thread.currentThread().name}", )
*/

//        Log.e(TAG, "Before runBlocking" )
//        runBlocking {
////            launch {
////                Log.e(TAG, "Launch: ${Thread.currentThread().name}", )  //main thread(but non blocking)
////            }
//            launch(Dispatchers.IO) {
//                delay(3000L)
//                Log.e(TAG, "Launch 1 finished")
//            }
//            launch(Dispatchers.IO) {
//                delay(6000L)
//                Log.e(TAG, "Launch 2 finished")
//            }
//
//            Log.e(TAG, "Inside runBlocking")
////            delay(5000L)
//            Log.e(TAG, "Inside runBlocking2")
//            //After executing this line, runBlocking waits for the launch 1 and launch 2 coroutines to get over.
//        }
//        Log.e(TAG, "After runBlocking" )
//        /*
//2021-07-20 19:13:29.834 1772-1772/com.example.coroutineexamples E/Check: Before runBlocking
//2021-07-20 19:13:29.962 1772-1772/com.example.coroutineexamples E/Check: Inside runBlocking
//2021-07-20 19:13:29.963 1772-1772/com.example.coroutineexamples E/Check: Inside runBlocking2
//2021-07-20 19:13:32.971 1772-1822/com.example.coroutineexamples E/Check: Launch 1 finished
//2021-07-20 19:13:35.970 1772-1822/com.example.coroutineexamples E/Check: Launch 2 finished
//2021-07-20 19:13:35.972 1772-1772/com.example.coroutineexamples E/Check: After runBlocking
//         */



    }
    suspend fun networkCall():String{
        delay(10000L)
        Log.e(TAG, "complete fake networkCall")
        return "Result 1"
    }
}
