package com.example.bobsbooks.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bobsbooks.R
import com.example.bobsbooks.fragments.books.BooksListFragment
import com.example.bobsbooks.fragments.books.BooksViewModel
import com.example.bobsbooks.fragments.search.SearchListFragment
import com.example.bobsbooks.fragments.search.SearchViewModel
import com.example.bobsbooks.models.Book
import com.example.bobsbooks.models.Thumbnail
import com.example.bobsbooks.models.VolumeInfo
import com.example.bobsbooks.models.api.BookResponse

import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_activity.*


class NavigationActivity : AppCompatActivity(), SearchView.OnQueryTextListener, BooksListFragment.TouchActionDelegate, SearchView.OnCloseListener, SearchListFragment.TouchActionDelegate {

    lateinit var searchView: SearchView
    lateinit var databaseViewModel: BooksViewModel
    lateinit var viewModel: SearchViewModel

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {R.id.navigation_search -> {
                navigationView.getMenu().setGroupCheckable(0, true, true);
                    replaceFragment(SearchListFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_books -> {
                    navigationView.getMenu().setGroupCheckable(0, true, true);
                    replaceFragment(BooksListFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        replaceFragment(SearchListFragment.newInstance())
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        databaseViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)



        }



    override fun onBackPressed() {
        super.onBackPressed()
        navigationView.getMenu().setGroupCheckable(0, true, true);

    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.book_search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search for book"
//        searchView.setOnCloseListener { onClose() }
        /*searchView.onActionViewExpanded()
        searchView.clearFocus()*/
//        searchView.setIconifiedByDefault(false)


        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        //replaces fragment if in BooksListFragment when searching
        replaceFragment(SearchListFragment.newInstance())

        val toast = Toast.makeText(
            applicationContext,
            query,
            Toast.LENGTH_SHORT
        )
        toast.show()
        searchView.setQuery("",false)
        searchView.queryHint = "Search for book"
        Log.i("Query fra text field", query)
       viewModel.getBooks(query)
        searchView.onActionViewCollapsed()

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }



    override fun launchBookFragment(bookId: Book) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentHolder, com.example.bobsbooks.create.BookFragment.newInstance(bookId.uid))
            .addToBackStack(null)
            .commit()
        navigationView.getMenu().setGroupCheckable(0, false, true);
    }

    override fun onClose(): Boolean {
        //Currently not being used
        Log.i("Closed menu", "Menu called onClose")
        return true
    }

    override fun addBookToDatabase(bookId: BookResponse) {
        unknownVariablesInBookReponse(bookId)

        val thumbnail = Thumbnail(bookId.volumeInfo?.imageLinks!!.thumbnail)
        val volumeInfo = VolumeInfo( bookId.volumeInfo.title, bookId.volumeInfo.authors, bookId.volumeInfo.categories!!, thumbnail, bookId.volumeInfo.averageRating, mapDataToStingIsbn(bookId))
        val addBook = Book(volumeInfo = volumeInfo )
       databaseViewModel.onAddBook(addBook)
    }

    fun unknownVariablesInBookReponse(bookId: BookResponse){
        if (bookId.volumeInfo?.categories == null){

            bookId.volumeInfo?.categories = listOf("Unknown genre")
        }
        if (bookId.volumeInfo?.authors == null){
            bookId.volumeInfo?.authors = listOf("Unknown author")
        }
    }

    fun mapDataToStingIsbn(bookId: BookResponse): String{
        val map: Map<String, String>? = bookId.volumeInfo?.industryIdentifiers?.get(0)
        val list: List<Pair<String, String>> = map!!.toList()
        return list[1].second
    }
}