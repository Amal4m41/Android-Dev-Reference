package com.example.viewmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewModelProviderFactory = RandomNumberViewModelFactory()

        //Creates ViewModelProvider, which will create ViewModels via the given Factory and retain them in a store of the given ViewModelStoreOwner
        //In this case the ViewModelStoreOwner is this activity(this).
        val viewModel = ViewModelProvider(this,viewModelProviderFactory).get(MyRandomNumberGenerator::class.java)

//        findViewById<TextView>(R.id.tvRandomNumber).text =
//                "Random Number : ${MyRandomNumberGenerator().getNumber()}"
//        findViewById<TextView>(R.id.tvRandomNumber).text =
//                "Random Number : ${viewModel.getNumber()}"

        /*
        Adds the given observer to the observers list within the lifespan of the given owner. The events are dispatched on the main thread. If LiveData already has data set, it will be delivered to the observer.
The observer will only receive events if the owner is in Lifecycle.State.STARTED or Lifecycle.State.RESUMED state (active).
If the owner moves to the Lifecycle.State.DESTROYED state, the observer will automatically be removed.
When data changes while the owner is not active, it will not receive any updates. If it becomes active again, it will receive the last available data automatically.
LiveData keeps a strong reference to the observer and the owner as long as the given LifecycleOwner is not destroyed. When it is destroyed, LiveData removes references to the observer & the owner.
If the given owner is already in Lifecycle.State.DESTROYED state, LiveData ignores the call.
If the given owner, observer tuple is already in the list, the call is ignored. If the observer is already in the list with another owner, LiveData throws an IllegalArgumentException.
         */

        val livedata:LiveData<String> = viewModel.getNumber()

        Log.e("TAG", "onCreate: $livedata", )
        livedata.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                //Called when the data changes, or if the livedata already has some data it'll be delivered here.
                Log.e("TAG", "onChanged: ", )
                findViewById<TextView>(R.id.tvRandomNumber).text = t!!
            }
        })

        findViewById<Button>(R.id.button).setOnClickListener {
            Log.e("TAG", "BUTTON FETCH CLICKED ", )
            viewModel.generateRandomNumber()
        }



//        Log.e("TAG", "onCreate: ")
    }
}