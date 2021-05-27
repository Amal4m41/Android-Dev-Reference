package com.example.activityforresultdemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.activityforresultdemo.databinding.ActivityFirstBinding
import com.example.activityforresultdemo.databinding.ActivitySecondBinding



class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)   //making the layout visible for us to access.
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {

            //intent object with no context or we don't pass which class we want to go, but in this case we use intent to
            //just assign information so that we can sent it over.
            val intent= Intent()

            if(binding.etName.text.isEmpty()){
                Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show()
            }
            else if(binding.etEmail.text.isEmpty()){
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
            }
            else{
                //adding data to the intent object(a key-value pair)
                intent.putExtra(MainActivity.NAME,binding.etName.text.toString())
                intent.putExtra(MainActivity.EMAIL,binding.etEmail.text.toString())

                //sending the resultCode and intent(which contains the data)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }

    }
}