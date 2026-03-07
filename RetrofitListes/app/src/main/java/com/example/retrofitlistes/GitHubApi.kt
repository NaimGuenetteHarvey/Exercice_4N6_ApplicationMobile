package com.example.retrofitlistes

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("exos/long/list")
    fun getTrucs(): Call<List<Truc>>
    @GET("exos/truc/list")
    fun getLong(): Call<List<Long>>

}