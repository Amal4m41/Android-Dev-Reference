package com.example.retrofit_mvvm_coroutine_demo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_mvvm_coroutine_demo.network.ApiClient
import com.example.retrofit_mvvm_coroutine_demo.network.CharacterResponse
import retrofit2.Call
import retrofit2.Response
import com.example.retrofit_mvvm_coroutine_demo.network.Character

class MainViewModel(private val repository: Repository=
    Repository(ApiClient.apiService)):ViewModel() {

    //creating a private property so that we only modify data inside this class.
    private var _characterList = MutableLiveData<ScreenState<List<Character>>>()


    val characterLiveData:LiveData<ScreenState<List<Character>>>
        get() = _characterList


    init {
        fetchCharacter()
    }


    private fun fetchCharacter(){
        val client = repository.getCharacters("2")

        _characterList.postValue(ScreenState.Loading(null))
        client.enqueue(object : retrofit2.Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {

                if (response.isSuccessful) {
//                    _characterList.postValue(response.body()?.results)
                    _characterList.postValue(ScreenState.Success(response.body()?.results))
                }
                else{
                    _characterList.postValue(ScreenState.Error(response.code().toString()))
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
//                Log.i("CALLBACK", "onFailure: ${t.message}")
                _characterList.postValue(ScreenState.Error(t.message.toString()))
            }

        })
    }

}