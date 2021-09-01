package com.example.retrofit_mvvm_coroutine_demo.network

import com.squareup.moshi.Json

data class Character(
        @Json(name="name")
        val name:String,
        @Json(name="image")
        val image:String
)

data class CharacterResponse(
        @Json(name="results")
        val results:List<Character>
)

