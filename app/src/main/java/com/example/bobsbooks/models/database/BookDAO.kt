package com.example.bobsbooks.models.database

import androidx.room.*
import com.example.bobsbooks.models.Book

@Dao
interface BookDAO {

    @Insert
    fun addBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

    @Query("SELECT * FROM books")
    fun retrieveBook(): MutableList<Book>


}