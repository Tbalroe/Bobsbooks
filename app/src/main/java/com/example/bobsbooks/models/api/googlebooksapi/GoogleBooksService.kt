package com.example.bobsbooks.models.api.googlebooksapi

import com.example.bobsbooks.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GoogleBooksService{
    private val BASE_URL = "https://www.googleapis.com/books/v1/volumes/"

    val okhttp2Client = OkHttpClient.Builder()


    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        if (BuildConfig.DEBUG) {
            okhttp2Client.addInterceptor(logging)
        }
    }

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttp2Client.build())
        .build()
        .create(GoogleBooksApi::class.java)


}