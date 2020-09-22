package com.example.bobsbooks.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "books")
data class Book (
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    @Embedded
    var volumeInfo: VolumeInfo
)
data class VolumeInfo(

    val title: String,
    val authors: List<String>?,
    val categories: List<String>?,
    @Embedded
    val imageLinks: Thumbnail,
    val averageRating: Double,
    var isbn: String? = null


)

data class Thumbnail(
    val thumbnail: String
)

