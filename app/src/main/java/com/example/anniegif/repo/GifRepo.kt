package com.example.anniegif.repo

import com.example.anniegif.model.GifModel
import com.example.anniegif.repo.remote.RetrofitInstance
import retrofit2.Response

object GifRepo {
    suspend fun getGifAPI(path: String, amount: Int): Response<GifModel> {
        return RetrofitInstance.gifService.getPath(path, amount)
    }
}