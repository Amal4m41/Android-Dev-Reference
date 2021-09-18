package com.example.viewmodeldemo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MyRandomNumberGenerator: ViewModel() {

    private val randomNumber: MutableLiveData<String> = MutableLiveData()
//    public var randomNumber: LiveData<String>? = null


    init {
//        getNumber()
    }


    public fun getNumber():LiveData<String>{

//        Log.e("TAG", "getNumber: ")
//        randomNumber.postValue((Random().nextInt(10)+1).toString())
        if(randomNumber.value.isNullOrEmpty()) {
            generateRandomNumber()
        }
        return randomNumber
    }

    public fun generateRandomNumber() {
//        Log.e("TAG", "generateRandomNumber: ")
//        randomNumber.postValue((Random().nextInt(10)+1).toString())
        val str = (Random().nextInt(10) + 1).toString()
        randomNumber.postValue(str)

        Log.e("TAG", "generateRandomNumber: $randomNumber", )
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("TAG", "onCleared: ")
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