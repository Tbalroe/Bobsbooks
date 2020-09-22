package com.example.bobsbooks.fragments.books

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bobsbooks.R
import com.example.bobsbooks.models.Book
import kotlinx.android.synthetic.main.fragment_books_list.view.*


class BooksListFragment : Fragment() {

    lateinit var viewModel: BooksViewModel
    lateinit var contentView: BooksListView
    var touchActionDelegate: TouchActionDelegate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        touchActionDelegate = context as TouchActionDelegate?
    }

    override fun onDetach() {
        super.onDetach()
        touchActionDelegate = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_books_list, container, false).apply {
            contentView = this as BooksListView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        setContentView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    private fun setContentView(){
        contentView.initView(viewModel, touchActionDelegate!!)

    }

    private fun bindViewModel(){
        viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)
        viewModel.bookListLiveData.observe(viewLifecycleOwner, Observer {booklist ->
            contentView.updateList(booklist)

        } )
    }

    companion object {
        fun newInstance() = BooksListFragment()
    }

    interface TouchActionDelegate{
        fun launchBookFragment(bookId: Book)
    }
}