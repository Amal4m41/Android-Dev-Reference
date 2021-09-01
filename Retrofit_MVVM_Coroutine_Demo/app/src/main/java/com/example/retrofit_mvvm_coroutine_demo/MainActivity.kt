package com.example.retrofit_mvvm_coroutine_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofit_mvvm_coroutine_demo.adapters.CharacterItemsAdapter
import com.example.retrofit_mvvm_coroutine_demo.databinding.ActivityMainBinding
import com.example.retrofit_mvvm_coroutine_demo.network.ApiClient
import com.example.retrofit_mvvm_coroutine_demo.network.Character
import com.example.retrofit_mvvm_coroutine_demo.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.apiService.fetchCharacters("2") //setting the query value for page=2

        client.enqueue(object : retrofit2.Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                if (response.isSuccessful) {
                    Log.i("TAG", "Characters: ${response.body()}")

                    val result = response.body()?.results
                    attachRecyclerViewAdapterForCharacters(result)

                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.i("TAG", "onFailure: ${t.message}")
            }

        })

    }

    private fun attachRecyclerViewAdapterForCharacters(characterList: List<Character>?){
        if (characterList != null) {
            val adapterRecyclerView = CharacterItemsAdapter(this,characterList)
            binding.rvCharacters.apply {
                layoutManager=GridLayoutManager(this@MainActivity,2)
                setHasFixedSize(true)
                adapter=adapterRecyclerView
            }
        }
    }
}