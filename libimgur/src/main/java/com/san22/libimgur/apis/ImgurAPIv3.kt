package com.san22.libimgur.apis

import com.san22.libimgur.models.GalleryResponse
import com.san22.libimgur.models.TagsResponse
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurAPIv3
{

    @GET("gallery/{section}")
    suspend fun getGallery(
        @Path("section")section:String,
        @Query("album_previews")albumPreviews:Boolean?=true

    ):Response<GalleryResponse>

    @GET("tags")
   suspend fun getTags():Response<TagsResponse>


}