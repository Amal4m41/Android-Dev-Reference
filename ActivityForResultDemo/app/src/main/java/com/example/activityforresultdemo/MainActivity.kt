package com.example.activityforresultdemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.activityforresultdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding      //now there's a binding/connection with the activity_main.xml,
    // using binding we can directly access the ui components using the id.


    companion object{    //to define static fields(methods and properties)
        private const val FIRST_ACTIVITY_REQUEST_CODE=1
        private const val SECOND_ACTIVITY_REQUEST_CODE=2

        const val NAME="name"
        const val EMAIL="email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)   //making the layout visible for us to access.
        setContentView(binding.root)

        binding.btnLaunchFirstActivity.setOnClickListener {       //to launch the first activity
            //intent to switch activity(from this class to firstactivity class)
            val intent=Intent(this,FirstActivity::class.java)
            //we also want to get a result from the activity, therefore we use: startActivityForResult
            //we get 'requestCode' depending upon the request code that we have passed once we start the activity
            startActivityForResult(intent, FIRST_ACTIVITY_REQUEST_CODE)
        }

        binding.btnLaunchSecondActivity.setOnClickListener {        //to launch the second activity
            val intent=Intent(this,SecondActivity::class.java)
            startActivityForResult(intent,SECOND_ACTIVITY_REQUEST_CODE)
        }

    }

    //upon finishing the activity the result will be sent which can be accessed here
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode==Activity.RESULT_OK){
            if(requestCode== FIRST_ACTIVITY_REQUEST_CODE){    //comparing the request code to identify the result(is of which activity ?)
                binding.tvFirstActivityResult.text="SUCCESS"
            }
            else if(requestCode== SECOND_ACTIVITY_REQUEST_CODE){
                if(data!=null){ //OR val name=data?.getStringArrayExtra(NAME).toString(), use safe call
                    val name=data.getStringExtra(NAME).toString()   //accessing the value from intent using the keys
                    val email=data.getStringExtra(EMAIL).toString()
                    binding.tvSecondActivityResult.text="$name -> $email"
                }
            }
        }
        else if(resultCode==Activity.RESULT_CANCELED){
            Log.e("Cancelled","Cancelled")
            Toast.makeText(this, "Result Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}