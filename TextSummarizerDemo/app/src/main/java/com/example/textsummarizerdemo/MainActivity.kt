package com.example.textsummarizerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

    val callback = object : Text2Summary.SummaryCallback {
        override fun onSummaryProduced(summary: String) {
            // The summary is ready!
            Log.i("RESULT", "Summary: ${summary.split("\\.\\s*\\.".toRegex())}")
//            Log.i("RESULT", "Summary: $summary")
            binding.progressBar.visibility=View.INVISIBLE
        }
    }

    private fun getTextSummary(){
        binding.progressBar.visibility= View.VISIBLE
        val text = binding.etTextInput.text.toString()

//        val summary =  Text2Summary.summarize(text, compressionRate = 0.7f )  //Will pause the main thread during heavy jobs
        Text2Summary.summarizeAsync( text , 0.7f , callback  )
    }
}