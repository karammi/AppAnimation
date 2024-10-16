package com.asad.appanimation.home.data.dataSource.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeDataModel(
    val created: String,
    val id: Int,
    val fileName: String,
    val url: String,
    val version: Int
)