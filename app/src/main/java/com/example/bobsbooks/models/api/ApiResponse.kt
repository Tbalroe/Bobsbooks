package com.example.bobsbooks.models.api

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T>(
    val items: MutableList<T>
)
data class GRReponseWrapper<T>(
    val books: List<T>
)


data class BookResponse(

    @SerializedName("id")
    val title: String?,
    val volumeInfo: VolumeInfo?
)
data class VolumeInfo(
    val title: String,
    val imageLinks: Thumbnail,
    var authors: List<String>?,
    val averageRating: Double,
    var categories: List<String>?,
    val industryIdentifiers: List<Map<String, String>>
)
data class Thumbnail(
    val thumbnail: String
)

data class Books(
    @SerializedName("average_rating")
    val averageRating: Double
)
