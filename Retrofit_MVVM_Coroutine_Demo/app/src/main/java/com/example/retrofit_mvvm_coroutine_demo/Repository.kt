package com.example.retrofit_mvvm_coroutine_demo

import com.example.retrofit_mvvm_coroutine_demo.network.ApiService


//SSOT : Single source of truth
//Suppose we have 2 data sources(api and local db) then this class acts as a mediator
//between the two sources, knows where to get the data from so that the viewmodel
//is solely responsible for making the data available for the view to be observed.
class Repository(private val apiService: ApiService) {
    fun getCharacters(page:String) = apiService.fetchCharacters(page)
}