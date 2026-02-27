package ca.cem.composeretrofitbase.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("exos/long/double/{number}")
    fun listReposString(@Path("number") nombre : Int): Call<String>

}