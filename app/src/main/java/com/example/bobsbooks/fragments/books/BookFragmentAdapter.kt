package com.example.bobsbooks.fragments.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bobsbooks.R
import com.example.bobsbooks.models.Book
import com.example.bobsbooks.views.BookView

class BookFragmentAdapter(
     val bookList: MutableList<Book> = mutableListOf(),
     val  dataActionDelegate: BookViewContract,
     val touchActionDelegate: BooksListFragment.TouchActionDelegate
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_view, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(bookList[position], position)


    }
    override fun getItemCount(): Int = bookList.size

   inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun onBind(bookInfo: Book, listIndex: Int) {
            (view as BookView).initiateView(bookInfo,
                { dataActionDelegate.onDeleteBook(bookList[listIndex]) },
                { touchActionDelegate.launchBookFragment(bookList[listIndex]) })
        }

    }

    fun updateList(list: MutableList<Book>){
        bookList.clear()
        bookList.addAll(list)
        notifyDataSetChanged()
    }

}