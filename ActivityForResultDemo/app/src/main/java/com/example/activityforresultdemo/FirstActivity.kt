package com.example.activityforresultdemo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activityforresultdemo.databinding.ActivityFirstBinding
import com.example.activityforresultdemo.databinding.ActivityMainBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding      //now there's a binding/connection with the activity_main.xml,
    // using binding we can directly access the ui components using the id.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)   //making the layout visible for us to access.
        setContentView(binding.root)



        //upon clicking this button we're sending RESULT_OK for resultCode and then finish the activity.
        //suppose the user doesn't click this button but chooses to click back navigation arrow then the resultCode will be RESULT_CANCELED
        binding.btnFinish.setOnClickListener {
            setResult(Activity.RESULT_OK)  //setting the result to ok
//            setResult(Activity.RESULT_CANCELED)  //setting the result to cancel
            finish()   //finish the current activity
        }
    }
}