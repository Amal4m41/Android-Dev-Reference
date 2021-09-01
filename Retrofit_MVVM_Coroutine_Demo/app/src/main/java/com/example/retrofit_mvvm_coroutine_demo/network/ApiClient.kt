package com.example.retrofit_mvvm_coroutine_demo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    const val BASE_URL= "https://rickandmortyapi.com/api/"

    //Creating a variable for the moshi builder.
    val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    //Creating an instance of retrofit by lazy, so that it's only
    //initialized when needed.
    val retrofit:Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
    }

    //Create an implementation of the API endpoints defined by the {@code service} interface.
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

//Creating an interface to define how retrofit talks to the service using GET method.
//our api link : https://rickandmortyapi.com/api/character/?page=2
interface ApiService{
    @GET("character")  //directory of the server.
    fun fetchCharacters(@Query("page") page:String): Call<CharacterResponse>
}

