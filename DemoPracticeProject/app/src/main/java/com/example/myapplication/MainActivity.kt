package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)   //making the layout visible for us to access.
        setContentView(binding.root)

        //first create instance of the fragments
        val firstFragment=FirstFragment()
        val secondFragment=SecondFragment()

        //Now we want to replace the initial content of the framelayout with either our first or second fragment
        //we need to use transaction.
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,firstFragment)    //id of the container to replaced with which fragment(pass the instance of that fragment)
            commit() //apply the changes
        }

        binding.btnFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,firstFragment)    //whenever we replace a fragment we don't push the fragment on top of the fragment or activity stack,
                //therefore pressing the back button we'll no take us to the next element in stack.

                //to add on stack use : addToBackStack(takes a name for for the fragment pushed in the backstack, can be null)
                addToBackStack(null)

                commit() //apply the changes
            }
        }

        binding.btnFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,secondFragment)    //id of the container to replaced with which fragment(pass the instance of that fragment)

                addToBackStack(null)

                commit() //apply the changes
            }
        }


    }
}