package com.example.bobsbooks.fragments.books

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bobsbooks.models.Book
import kotlinx.android.synthetic.main.fragment_books_list.view.*

class BooksListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
): ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var bookAdapter: BookFragmentAdapter
    private lateinit var dataActionDelegate: BookViewContract
    private lateinit var touchActionDelegate: BooksListFragment.TouchActionDelegate


    fun initView(daDelegate: BookViewContract, touchDelegate: BooksListFragment.TouchActionDelegate){
        setupDelegates(daDelegate, touchDelegate)
        setupView()

    }

    fun setupDelegates(daDelegate: BookViewContract, touchDelegate: BooksListFragment.TouchActionDelegate){
        dataActionDelegate = daDelegate
        touchActionDelegate = touchDelegate
    }

    fun setupView(){
        booksRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            bookAdapter = BookFragmentAdapter(dataActionDelegate = dataActionDelegate, touchActionDelegate =  touchActionDelegate)
            booksRecyclerView.adapter = bookAdapter
        }
    }

    fun updateList(list: MutableList<Book>){
        bookAdapter.updateList(list)
    }
}