package com.example.anniegif.repo.remote

import com.example.anniegif.model.GifModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GifService {
    @GET("{path}")
    suspend fun getGif(
        @Path("path") pathParam: String,
        @Query("amount") amount: Int,
    ): Response<GifModel>

    @GET("endpoints")
    suspend fun getCategory(): ResponseBody
}


