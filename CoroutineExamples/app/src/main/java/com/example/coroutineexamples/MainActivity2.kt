package com.example.coroutineexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.example.coroutineexamples.databinding.ActivityMain2Binding
import com.example.coroutineexamples.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

//Code for coroutine jobs: create independent jobs, then cancel or complete it and do necessary UI changes etc.
// for completion or cancellation of a job.
class MainActivity2 : AppCompatActivity() {

    private val PROGRESS_MAX=100
    private val PROGRESS_START=0
    private val JOB_TIME=4000  //ms

    private lateinit var binding:ActivityMain2Binding

    private lateinit var job:CompletableJob     //Child class of Coroutine Job object


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        job.complete()      //we can set the value for job as completed manually, this is powerful
        binding.jobButton.setOnClickListener {
            if(!::job.isInitialized){    //if the job variable is not initialized
                initJob()
            }
            binding.jobProgressBar.startJoborCancel(job)
        }
    }

    fun ProgressBar.startJoborCancel(job:Job){    //this=ProgressBar
        if(this.progress>0){
            println("${job} is already active. Cancelling...")
            resetJob()
        }
        else{
            binding.jobButton.setText("Cancel Job #1")
            CoroutineScope(IO+job).launch {
                println("coroutine $this is activated with job $job")
                for(i in PROGRESS_START.. PROGRESS_MAX){
                    delay((JOB_TIME/PROGRESS_MAX).toLong())  //just to make sure that our job takes the JOB_TIME time.
                    this@startJoborCancel.progress=i
                }
                updateJobCompleteTextView("Job Completed")
//                binding.jobCompleteText.setText("Job Completed")   //will give error as we're trying to access a UI element
                //which works in the main thread from a coroutine scope
            }
        }
    }

    private fun updateJobCompleteTextView(text:String){
        //No matter from which context this method is called, the jobCompleteText will be updated from the main thread.
        GlobalScope.launch(Main) {
            binding.jobCompleteText.text = text
        }
    }

    private fun resetJob() {    //takes care of cancelling and resetting the job
        if(job.isActive||job.isCompleted){
            job.cancel(CancellationException("Resetting Job")) //will be caught in invokeOnCompletion
        }
        initJob() //cuz IF A JOB IS CANCELLED, IT CAN'T BE REUSED AGAIN. WE NEED TO REINITIALIZE AND SET INVOKE ON COMPLETION
        //ETC.
    }

    fun initJob() {
        binding.jobButton.setText("Start Job #1")
        updateJobCompleteTextView("")
        job= Job()
        job.invokeOnCompletion {
            //when job is completed it=null, if cancelled it=CancellationException

            it?.message.let {
                var msg=it
                if(msg.isNullOrEmpty()){
                    msg="Unknown cancellation error"
                }
                println("${job} was cancelled. Reason $msg")
                showToast(msg)
            }
        }
        binding.jobProgressBar.max=PROGRESS_MAX
        binding.jobProgressBar.progress=PROGRESS_START

    }

    fun showToast(text:String){
        //No matter from where this method is called(inside coroutine etc), it'll be executed in the main thread, toast
        //needs to be shown in the main thread.
        GlobalScope.launch(Main){

            Toast.makeText(this@MainActivity2, text, Toast.LENGTH_SHORT).show()
        }
    }
}