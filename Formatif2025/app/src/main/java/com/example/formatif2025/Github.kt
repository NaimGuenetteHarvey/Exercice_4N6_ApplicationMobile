package com.example.formatif2025


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Github {
    @GET("exam/representations/{nombre}")
    fun list(@Path("nombre") nombre: String): Call<List<Representation>>
}