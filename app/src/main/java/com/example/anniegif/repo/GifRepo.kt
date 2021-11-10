package com.example.anniegif.repo

import com.example.anniegif.model.Category
import com.example.anniegif.model.GifModel
import com.example.anniegif.repo.remote.RetrofitInstance
import com.example.anniegif.repo.remote.adapter.MoshiAdapter
import retrofit2.Response

object GifRepo {

    private val gifService by lazy { RetrofitInstance.gifService }

    suspend fun getCategories(): List<Category> = MoshiAdapter.run {
        val response = gifService.getCategory()
        val jsonStr = response.string().run { substring(1, length.minus(2)) }
            .split("},")
            .map{
                val split = it.split(":{")
                val jsonObject = String.format("{ %s, %s }", "\"title\": ${split[0]}", split[1])
                jsonObject
            }
        fromJson(jsonStr.toString())
    }

    suspend fun getGif(path: String, amount: Int): Response<GifModel> {
        return RetrofitInstance.gifService.getGif(path, amount)
    }
}