package com.example.bobsbooks.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bobsbooks.R
import com.example.bobsbooks.fragments.search.SearchListFragment
import com.example.bobsbooks.models.Book
import kotlinx.android.synthetic.main.book_fragment.view.*


class BookFragment : Fragment() {

    var bookId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         bookId = arguments?.getInt(ARG_BOOK_ID) as Int

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.book_fragment, container, false).apply {
            title.text = bookId.toString()
        }

    }

    companion object {
        private const val ARG_BOOK_ID = "book_id"
        fun newInstance(bookId: Int): BookFragment{
            val args = Bundle()
            args.putInt(ARG_BOOK_ID,bookId)
            return BookFragment().apply { arguments = args }
        }


    }

}
