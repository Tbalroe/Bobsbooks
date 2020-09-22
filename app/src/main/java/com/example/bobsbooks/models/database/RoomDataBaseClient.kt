package com.example.bobsbooks.models.database

import android.content.Context
import androidx.room.*
import com.example.bobsbooks.fragments.books.BooksModel
import com.example.bobsbooks.models.Book
import com.example.bobsbooks.models.Thumbnail
import com.example.bobsbooks.models.VolumeInfo

const val DATABASE_SCHEMA_VERSION = 1
const val DB_NAME = "local-db"
val model: BooksModel = BooksModel()

@Database(version = DATABASE_SCHEMA_VERSION, entities = [Book::class])
@TypeConverters(ArrayConverter::class)
abstract class RoomDataBaseClient: RoomDatabase() {

    abstract fun BookDAO(): BookDAO

    companion object{
        private var instance: RoomDataBaseClient? = null

        fun getInstance(context: Context): RoomDataBaseClient{
            if (instance==null){
                instance = createDatabase(context)


            }
            return instance!!
        }

        //Move away from main thread
       private fun createDatabase(context: Context): RoomDataBaseClient{
            return Room.databaseBuilder(context, RoomDataBaseClient::class.java, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }
}