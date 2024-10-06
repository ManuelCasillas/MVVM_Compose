package com.formation.data.dataSource

import com.formation.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Date

class QueryInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()

        val publicKey = BuildConfig.MARVEL_PUBLIC_KEY
        val privateKey= BuildConfig.MARVEL_PRIVATE_KEY

        val ts = Date().time
        val hash = generateHash(ts, privateKey, publicKey)

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }

}