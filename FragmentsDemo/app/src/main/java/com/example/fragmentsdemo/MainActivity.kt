package com.example.fragmentsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fragmentsdemo.databinding.ActivityMainBinding
import com.example.fragmentsdemo.fragments.FirstFragment
import com.example.fragmentsdemo.fragments.SecondFragment

class MainActivity : AppCompatActivity() {

    private var firstFragment:Fragment = FirstFragment()
    private var secondFragment:Fragment = SecondFragment()
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(firstFragment,true) //set the default fragment


        binding.btnFragment1.setOnClickListener {
            setFragment(firstFragment)
        }
        binding.btnFragment2.setOnClickListener {
            setFragment(secondFragment)
        }

    }

    private fun setFragment(fragment:Fragment,default:Boolean = false){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)  //this will replace the existing fragment in the framelayout
            if(!default){
                addToBackStack(null)  //this will add the fragment on top of the fragment stack
            }
            commit()
        }
    }
}