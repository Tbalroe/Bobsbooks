package com.example.bobsbooks.fragments.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bobsbooks.R
import com.example.bobsbooks.models.api.BookResponse
import com.example.bobsbooks.views.SearchBookView


class SearchListAdapter(
    val bookList: MutableList<BookResponse> = mutableListOf(),
    val touchActionDelegate: SearchListFragment.TouchActionDelegate
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =

            SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_view, parent, false))

    override fun getItemCount(): Int = bookList.size

   inner class SearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
         fun onBind(bookInfo: BookResponse, listIndex: Int) {

            (view as SearchBookView).InitiateView(bookInfo,
                { touchActionDelegate.addBookToDatabase(bookList[listIndex]) })
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchViewHolder).onBind(bookList[position], position)
    }

    fun updateList(list: List<BookResponse>){
        bookList.clear()
        bookList.addAll(list)
        Log.d("booksConfirmed", bookList.joinToString())
        notifyDataSetChanged()
    }

}



