package com.example.activitylifecycledemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class ActivityOne : AppCompatActivity() {

    val TAG= "LIFECYCLE DEMO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG, "onCreate: Activity One", )

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this,ActivityTwo::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: Activity One", )
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: Activity One", )
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: Activity One ", )
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: Activity One", )
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: Activity One", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: Activity One", )
    }

    override fun finish() {
        super.finish()
        Log.e(TAG, "finish: Activity One", )
    }

}