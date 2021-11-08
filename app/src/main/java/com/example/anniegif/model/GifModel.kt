package com.example.anniegif.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class GifModel(
    val url: List<GifInfo>
): Serializable

@JsonClass(generateAdapter = true)
data class GifInfo(
    val url: String,
    val anime_name: String
): Serializable