package com.example.activitylifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ActivityTwo : AppCompatActivity() {

    val TAG= "LIFECYCLE DEMO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        Log.e(TAG, "onCreate: Activity TWO", )
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: Activity TWO", )
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: Activity TWO", )
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: Activity TWO", )
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: Activity TWO", )
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: Activity TWO", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: Activity TWO", )
    }

    override fun finish() {
        super.finish()
        Log.e(TAG, "finish: Activity TWO", )
    }

}