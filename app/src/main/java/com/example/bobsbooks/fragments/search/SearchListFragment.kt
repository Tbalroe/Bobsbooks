package com.example.bobsbooks.fragments.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bobsbooks.R
import com.example.bobsbooks.fragments.books.BooksListFragment
import com.example.bobsbooks.models.Book
import com.example.bobsbooks.models.api.BookResponse
import kotlinx.android.synthetic.main.fragment_search_list.*

class SearchListFragment : Fragment() {


    lateinit var viewModel: SearchViewModel
    lateinit var contentListView: SearchListView
    var touchActionDelegate: TouchActionDelegate? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_list, container, false).apply {
            contentListView = this as SearchListView
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        touchActionDelegate = context as SearchListFragment.TouchActionDelegate?
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setContentView()
        searchRecyclerView.visibility = View.INVISIBLE
    }

    override fun onResume(){
        super.onResume()

    }

    private fun setContentView(){
        contentListView.initView(touchActionDelegate!!)
    }

   private fun bindViewModel(){
       Log.i("Viewmodel", "view model bliver kaldt")
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        viewModel.booksResponse.observe(viewLifecycleOwner, Observer {list ->
           list?.let {
                Log.i("Observer gets called", "Updatelistgetscalled")
               searchRecyclerView.visibility = View.VISIBLE
                contentListView.updateList(list)
          }
        } )

        viewModel.error.observe(viewLifecycleOwner, Observer { errorMsg ->
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
        })
    }



    companion object {
        fun newInstance(): SearchListFragment {
            return SearchListFragment()
        }
    }

    interface TouchActionDelegate{
        fun addBookToDatabase(bookId: BookResponse)
    }





}