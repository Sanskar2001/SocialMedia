package com.san22.libimgur.apis

import com.san22.libimgur.ImgurClient
import com.san22.libimgur.params.Section
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImgurAPIv3Tests {

     private val client=OkHttpClient.Builder().addInterceptor{
         val request=it.request().newBuilder().addHeader("Authorization","Client-ID 6c2487150c8e340" ).build()
         it.proceed(request)
     }.build()
    private val retrofit= Retrofit.Builder().client(client).addConverterFactory(MoshiConverterFactory.create()).baseUrl("https://api.imgur.com/3/").build()
    val api=ImgurClient.api

   @Test
  fun `get tags working`() = runBlocking {
           val response = api.getTags()
           
       }




    @Test
  fun `get galleries-hot working`()
    {

        runBlocking {
            val response=api.getGallery(Section.Hot);
            println(response?.raw().toString())
        }

    }

    @Test
    fun `get galleries top working`()
    {
        runBlocking {
            val response=api.getGallery(Section.Top);
            println(response?.raw().toString())
        }
       

    }
}