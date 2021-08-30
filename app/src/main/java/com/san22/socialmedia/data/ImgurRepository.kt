package com.san22.socialmedia.data

import com.san22.libimgur.ImgurClient
import com.san22.libimgur.models.GalleryResponse
import com.san22.libimgur.params.Section

class ImgurRepository {
    val api=ImgurClient.api
    suspend fun `getHotFeed`(): List<GalleryResponse.Data?>?
    {
          val response=api.getGallery(Section.Top)
        return response.body()?.data
    }

    suspend fun `getTopFeed`(): List<GalleryResponse.Data?>? {
        val response=api.getGallery(Section.Hot)
        return response.body()?.data
        
    }
}