package com.example.viewmodeldemo

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*

class MyRandomNumberGenerator: ViewModel() {

    private var randomNumber: String? = null

    public fun getNumber():String{
        //if it's not null then return the random number



        Log.i("TAG", "getNumber: ")
        if(randomNumber==null){
            randomNumber=generateRandomNumber()
        }
        return randomNumber!!
    }

    private fun generateRandomNumber():String{
        Log.i("TAG", "generateRandomNumber: ")
        return (Random().nextInt(10)+1).toString()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TAG", "onCleared: ")
    }
}

//class MyRandomNumberGenerator {
//
//    private var randomNumber: String? = null
//
//    public fun getNumber():String{
//        //if it's not null then return the random number
//
//        Log.i("TAG", "getNumber: ")
//        randomNumber?.let {
//            return it
//        }
//
//        return generateRandomNumber()
//    }
//
//    private fun generateRandomNumber():String{
//        Log.i("TAG", "generateRandomNumber: ")
//        return (Random().nextInt(10)+1).toString()
//    }
//}