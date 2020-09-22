package com.example.bobsbooks.fragments.books


import com.example.bobsbooks.application.BookApplication
import com.example.bobsbooks.models.database.RoomDataBaseClient
import com.example.bobsbooks.models.Book


class BooksModel  {

   private var dataBaseClient = RoomDataBaseClient.getInstance(BookApplication.instance.applicationContext)



    fun addBook(book: Book){
        dataBaseClient.BookDAO().addBook(book)
    }


    fun updateBook(book: Book){
        dataBaseClient.BookDAO().updateBook(book)
    }


    fun deleteBook(book: Book){
        dataBaseClient.BookDAO().deleteBook(book)
    }


    fun retrieveBooks(): List<Book> = dataBaseClient.BookDAO().retrieveBook()

    fun deleteEntireDatabase(){
        dataBaseClient.clearAllTables()
    }


}