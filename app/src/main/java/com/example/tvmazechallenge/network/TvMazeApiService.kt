package com.example.tvmazechallenge.network

import com.example.tvmazechallenge.model.Show
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "http://api.tvmaze.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TvMazeApiService {
    @GET("shows")
    fun getProperties(): Call<List<Show>>
}

object TvMazeApi {
    val retrofitService : TvMazeApiService by lazy {
        retrofit.create(TvMazeApiService::class.java) }
}
