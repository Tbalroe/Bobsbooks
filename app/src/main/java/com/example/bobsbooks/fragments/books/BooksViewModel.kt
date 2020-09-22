package com.example.bobsbooks.fragments.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bobsbooks.models.Book

class BooksViewModel : ViewModel(), BookViewContract {
    val model: BooksModel= BooksModel()


    private val _bookListLiveData: MutableLiveData<MutableList<Book>> = MutableLiveData()
    val bookListLiveData: LiveData<MutableList<Book>> = _bookListLiveData


    init {

        loadData()
    }

    fun loadData(){
        _bookListLiveData.postValue(model.retrieveBooks().toMutableList())
    }

    override fun onDeleteBook(book: Book) {
        model.deleteBook(book)
        loadData()
    }

    override fun onAddBook(book: Book) {
        model.addBook(book)
        loadData()
    }


}