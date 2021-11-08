package com.example.anniegif.repo.remote

import com.example.anniegif.model.GifInfo
import com.example.anniegif.model.GifModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GifService {

    @GET("{path}")
    suspend fun getPath(
        @Path("path") pathParam: String,
        @Query("amount") amount: Int,
    ): Response<GifModel>

}


