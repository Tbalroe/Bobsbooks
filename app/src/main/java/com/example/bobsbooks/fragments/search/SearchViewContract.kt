package com.example.bobsbooks.fragments.search

import com.example.bobsbooks.models.Book
import com.example.bobsbooks.models.api.BookResponse

interface SearchViewContract {
    fun onBookUpdated(bookIndex: Int, isRead: Boolean)
    fun onDeleteBook(book: Book)
    fun onAddBook(book:String)
}