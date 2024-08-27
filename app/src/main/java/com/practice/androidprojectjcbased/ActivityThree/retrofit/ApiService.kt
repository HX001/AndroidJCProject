package com.practice.androidprojectjcbased.ActivityThree.retrofit

import com.practice.androidprojectjcbased.ActivityThree.data.Photos
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/photos")
    suspend fun getPhotoContent(): Response<List<Photos>>
}