package com.example.bobsbooks.fragments.search


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bobsbooks.models.api.BookResponse
import com.example.bobsbooks.models.api.googlebooksapi.GoogleBooksService
import com.example.bobsbooks.models.api.ResponseWrapper
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class SearchViewModel : ViewModel() {

    val booksResponse = MutableLiveData<MutableList<BookResponse>>()
    val booksList: MutableList<MutableList<BookResponse>> = mutableListOf()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val apiKey: String = YourGoogleApiKeyHere

    init {

    }

    fun getBooks(bookTitle: String) {
        GoogleBooksService.api.getBooks(bookTitle, apiKey).enqueue(object: Callback<ResponseWrapper<BookResponse>> {

            override fun onFailure(call: Call<ResponseWrapper<BookResponse>>, t: Throwable) {
                onError(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseWrapper<BookResponse>>,
                response: Response<ResponseWrapper<BookResponse>>
            ) {
                if (response.isSuccessful){
                    val books = response.body()
                    Log.w("2.0 getFeed > ", Gson().toJson(response.body()));
                    books?.let {
//                        booksList.add(books.items)
                        booksResponse.value = books.items

                        loading.value = false
                        error.value = null
                        Log.i("Content of livedata", booksResponse.getValue().toString())
                    }
                }
            }

        })
    }

    private fun onError(message: String) {
        error.value = message
      loading.value = false
    }




}