package com.example.nasapictures.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NASAImage(
    val copyright: String?,
    val date: String,
    val explanation: String,
    @Json(name = "hdurl") val hdUrl: String,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "service_version") val serviceVersion: String,
    val title: String,
    val url: String
)
