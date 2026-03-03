package com.example.retrofitcomplexe

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("exos/truc/complexe")
    fun listReposString(@Query("name") name: String = "Naim"): Call<String>
}