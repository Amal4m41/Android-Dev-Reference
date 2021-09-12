package com.example.textsummarizerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.textsummarizerdemo.databinding.ActivityMainBinding
import com.ml.quaterion.text2summary.Text2Summary

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            getTextSummary()
        }
    }

    private fun getTextSummary(){
        val text = binding.etTextInput.text.toString()

        val summary =  Text2Summary.summarize(text, compressionRate = 0.7f )

        Log.i("RESULT", "Summary: $summary")
    }
}