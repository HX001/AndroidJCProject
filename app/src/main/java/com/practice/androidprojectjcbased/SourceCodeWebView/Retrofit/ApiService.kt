package com.practice.androidprojectjcbased.SourceCodeWebView.Retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getPageSource(@Url url: String) : Response<String>
}