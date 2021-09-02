package com.example.retrofit_mvvm_coroutine_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofit_mvvm_coroutine_demo.adapters.CharacterItemsAdapter
import com.example.retrofit_mvvm_coroutine_demo.databinding.ActivityMainBinding
import com.example.retrofit_mvvm_coroutine_demo.network.ApiClient
import com.example.retrofit_mvvm_coroutine_demo.network.Character
import com.example.retrofit_mvvm_coroutine_demo.network.CharacterResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy{  //viewModel is only initialized when it's needed.
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //observe takes in the context and the implementation of observer interface, where we need to override the
        //onChanged method which is called when the livedata value is changed.
        viewModel.characterLiveData.observe(this){
            characters ->
            attachRecyclerViewAdapterForCharacters(characters)
        }

    }

    private fun attachRecyclerViewAdapterForCharacters(screenState: ScreenState<List<Character>>){
        when(screenState){
            is ScreenState.Loading ->{
                binding.progressBar.visibility= View.VISIBLE
            }

            is ScreenState.Success ->{
                binding.progressBar.visibility=View.GONE
                if (screenState.data != null) {
                    val adapterRecyclerView = CharacterItemsAdapter(this,screenState.data)
                    binding.rvCharacters.apply {
                        layoutManager=GridLayoutManager(this@MainActivity,2)
                        setHasFixedSize(true)
                        adapter=adapterRecyclerView
                    }
                }
            }

            is ScreenState.Error ->{
                binding.progressBar.visibility=View.GONE
                val rootView = binding.root
                Snackbar.make(rootView,screenState.message!!,Snackbar.LENGTH_LONG).show()
            }
        }



    }
}