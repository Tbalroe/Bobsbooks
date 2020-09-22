package com.example.bobsbooks.fragments.books

import com.example.bobsbooks.models.Book

interface BookViewContract {
    fun onDeleteBook(book: Book)
    fun onAddBook(book: Book)

}