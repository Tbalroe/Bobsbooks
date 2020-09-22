package com.example.bobsbooks.application

import android.app.Application

class BookApplication: Application() {

    companion object{
      lateinit var instance: Application
    }

    init {
        instance = this
    }
}