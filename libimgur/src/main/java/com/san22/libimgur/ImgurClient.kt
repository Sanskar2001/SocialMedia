package com.san22.libimgur

import com.san22.libimgur.apis.ImgurAPIv3
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ImgurClient {
    public const val API_KEY="6c2487150c8e340"
    private val client by lazy{
        OkHttpClient.Builder().addInterceptor{
        val request=it.request().newBuilder().addHeader("Authorization","Client-ID $API_KEY" ).build()
        it.proceed(request)
    }.build()
    }
    private val retrofit by lazy {
        Retrofit.Builder().client(client).addConverterFactory(
        MoshiConverterFactory.create()).baseUrl("https://api.imgur.com/3/").build()
    }
    val api by lazy {  retrofit.create(ImgurAPIv3::class.java)}

}