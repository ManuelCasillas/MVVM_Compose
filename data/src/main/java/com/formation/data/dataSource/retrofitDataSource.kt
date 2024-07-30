package com.formation.data.dataSource

import com.formation.data.constants.Constant.Companion.BASE_URL
import com.formation.data.api.CharactersApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun createRetrofit(): CharactersApi {

    val okhttp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(QueryInterceptor())
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    val retrofit =  Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okhttp)
        .build()

    return retrofit.create(CharactersApi::class.java)

}

