package com.example.viewmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewModelProviderFactory = RandomNumberViewModelFactory()
        val viewModel = ViewModelProvider(this,viewModelProviderFactory).get(MyRandomNumberGenerator::class.java)

//        findViewById<TextView>(R.id.tvRandomNumber).text =
//                "Random Number : ${MyRandomNumberGenerator().getNumber()}"
        findViewById<TextView>(R.id.tvRandomNumber).text =
                "Random Number : ${viewModel.getNumber()}"



        Log.i("TAG", "onCreate: ")
    }
}