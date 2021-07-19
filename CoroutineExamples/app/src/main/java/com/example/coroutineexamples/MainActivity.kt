package com.example.coroutineexamples

import android.hardware.camera2.TotalCaptureResult
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.coroutineexamples.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Demo code for chain result coroutines, i.e. get a result from one api request(done in bg) and use that
// result for the next api call etc.
class MainActivity : AppCompatActivity() {

    private val RESULT_1="Result #1"
    private val RESULT_2="Result #2"

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            //Important coroutineContext: IO(Network request, Local database requests etc.),
            //Main(working on the main thread, interacting with UI etc.),
            //Default(to use for heavy computational work, filter a large list etc.)
            CoroutineScope(IO).launch {
                //Background thread DefaultDispatcher-worker-1
                fakeAPIRequest()
            }

        }
    }

    private fun setNewText(input:String){  //This will do the job in which ever thread it's called from(and
        // it needs to be called from the main thread to avoid crashing)
        logThread("setNewText()")
        binding.textView.text=binding.textView.text.toString()+"\n$input"
    }

    private suspend fun setTextOnMainThread(input:String){
//        CoroutineScope(Main).launch {
              //fired up a new coroutine in the main thread
//            setNewText(input)
//        }
//        OR
        withContext(Main){
            //the coroutine is switched to main thread instead of firing up a new coroutine in the main thread
            setNewText(input)
        } //when we call withContext() with a coroutine, it'll switch it's context(here we switched from background thread
        //to the main thread). So coroutines are kind of thread independent, like we can switch it from one thread to other.
    }

    private suspend fun fakeAPIRequest(){
        val result1=getResult1FromApi()   //execute, wait, get result and move on
        println("debug: $result1")
//        binding.textView.text=result1    //will give error as we are working in the background thread and trying
        //to interact with UI components which works in the main thread.
        //android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
        setTextOnMainThread(result1)

        val result2=getResult2FromApi(result1)
        setTextOnMainThread(result2)
    }

    private suspend fun getResult1FromApi():String{ //can be called within a coroutine
        logThread("getResult1FromApi()")
        delay(1000) //will only delay this specific coroutine(simulating network calls)
//        logThread("getResult1FromApi()")
//        Thread.sleep(1000)//will sleep/delay the entire thread, so all the coroutines
        // working in the thread will also go to sleep
        return RESULT_1
    }

    private suspend fun getResult2FromApi(result1: String):String{
        logThread("getResult2FromApi()")
        delay(1000)
        return RESULT_2+result1
    }

    private fun logThread(methodName:String){
        println("debug : ${methodName}:${Thread.currentThread().name}")
    }


}