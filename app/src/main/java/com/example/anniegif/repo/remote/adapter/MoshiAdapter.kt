package com.example.anniegif.repo.remote.adapter

import com.example.anniegif.model.Category
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object MoshiAdapter {
    private val moshi by lazy { Moshi.Builder().build() }
    private val categoryAdapter by lazy {
        val type = Types.newParameterizedType(List::class.java, Category::class.java)
        moshi.adapter<List<Category>>(type)
    }

    fun fromJson(categoryOptions: String): List<Category> {
        return categoryAdapter.fromJson(categoryOptions) ?: emptyList()
    }
}