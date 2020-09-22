package com.example.bobsbooks.models.api.googlebooksapi

import com.example.bobsbooks.models.api.BookResponse
import com.example.bobsbooks.models.api.ResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET("/books/v1/volumes?maxResults=4")
    fun getBooks(@Query("q") booktitle: String, @Query("key") apiKey: String): Call<ResponseWrapper<BookResponse>>

}