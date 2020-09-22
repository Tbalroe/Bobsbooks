package com.example.bobsbooks.fragments.search

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bobsbooks.models.Book
import com.example.bobsbooks.models.api.BookResponse
import kotlinx.android.synthetic.main.fragment_search_list.view.*


class SearchListView @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1

    ): ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: SearchListAdapter
    private lateinit var dataActionDelegate: SearchViewContract
    private lateinit var touchActionDelegate: SearchListFragment.TouchActionDelegate

    fun initView( touchDelegate: SearchListFragment.TouchActionDelegate){
        setupDelegates( touchDelegate)
        setupView()
    }

    fun setupDelegates(touchDelegate: SearchListFragment.TouchActionDelegate){
      //  dataActionDelegate = dataDelegate
        touchActionDelegate = touchDelegate
    }

    fun setupView(){
        searchRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SearchListAdapter(touchActionDelegate = touchActionDelegate)
        searchRecyclerView.adapter = adapter
    }

    fun updateList(list: List<BookResponse>){
        adapter.updateList(list)
    }
}