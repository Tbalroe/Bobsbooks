package com.example.bobsbooks.views

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bobsbooks.R
import com.example.bobsbooks.models.api.BookResponse
import com.squareup.picasso.Picasso


import kotlinx.android.synthetic.main.search_view.view.*

class SearchBookView @JvmOverloads constructor(

    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var mLastClickTime: Long = 0

    fun InitiateView(bookInfo: BookResponse, addToDatabaseButtonClickedCallback: () -> Unit) {

        title.text = bookInfo.volumeInfo?.title
        rating.text = bookInfo.volumeInfo?.averageRating.toString()
        setupAuthor(bookInfo)
        setupCategories(bookInfo)
        setStarRatingViews(bookInfo.volumeInfo!!.averageRating)
        setupImage(bookInfo)

        favorited.setOnClickListener {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            addToDatabaseButtonClickedCallback.invoke()
            favoritedred.visibility = View.VISIBLE }


    }
    private fun setStarRatingViews(rating: Double){
        if (rating == 0.0){
            starRatingOne.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            starRatingTwo.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            starRatingThree.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            starRatingFour.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            starRatingFive.setBackgroundResource(R.drawable.ic_grade_grey_24px)
        }
        if (rating >=  1){
            starRatingOne.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            starRatingTwo.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            starRatingThree.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            starRatingFour.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            starRatingFive.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            if (rating >= 1.13){
                starRatingTwo.setBackgroundResource(R.drawable.ic_grade_25pct_24px)}
            if (rating >= 1.37){
                starRatingTwo.setBackgroundResource(R.drawable.ic_grade_50pct_24px)
            }
            if (rating >= 1.63){
                starRatingTwo.setBackgroundResource(R.drawable.ic_grade_75pct_24px)
            }
            if (rating >= 1.95){
                starRatingTwo.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            }

        }
        if (rating >=  2){
            starRatingOne.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            starRatingTwo.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            starRatingFour.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            starRatingFive.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            if (rating >= 2.13){
                starRatingThree.setBackgroundResource(R.drawable.ic_grade_25pct_24px)}
            if (rating >= 2.37){
                starRatingThree.setBackgroundResource(R.drawable.ic_grade_50pct_24px)
            }
            if (rating >= 2.63){
                starRatingThree.setBackgroundResource(R.drawable.ic_grade_75pct_24px)
            }
            if (rating >= 2.95){
                starRatingThree.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            }
        }
        if (rating >=  3){
            starRatingOne.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            starRatingTwo.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            starRatingThree.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            starRatingFive.setBackgroundResource(R.drawable.ic_grade_grey_24px)
            if (rating >= 3.13){
                starRatingFour.setBackgroundResource(R.drawable.ic_grade_25pct_24px)}
            if (rating >= 3.37){
                starRatingFour.setBackgroundResource(R.drawable.ic_grade_50pct_24px)
            }
            if (rating >= 3.63){
                starRatingFour.setBackgroundResource(R.drawable.ic_grade_75pct_24px)
            }
            if (rating >= 3.95){
                starRatingFour.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            }
        }
        if (rating >=  4){
            starRatingOne.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            starRatingTwo.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            starRatingThree.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            starRatingThree.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            if (rating >= 4.13){
                starRatingFive.setBackgroundResource(R.drawable.ic_grade_25pct_24px)}
            if (rating >= 4.37){
                starRatingFive.setBackgroundResource(R.drawable.ic_grade_50pct_24px)
            }
            if (rating >= 4.63){
                starRatingFive.setBackgroundResource(R.drawable.ic_grade_75pct_24px)
            }
            if (rating >= 4.95){
                starRatingFive.setBackgroundResource(R.drawable.ic_grade_100pct_24px)
            }
        }
    }
    private fun setupAuthor(bookInfo: BookResponse){
        if (bookInfo.volumeInfo?.authors?.joinToString() != null){
            author.text = bookInfo.volumeInfo.authors?.joinToString()
        } else{ author.text = "Unknown Author"}
    }
    private fun setupCategories(bookInfo: BookResponse){
        if (bookInfo.volumeInfo?.categories?.joinToString() != null){
            genre.text = bookInfo.volumeInfo?.categories?.joinToString()
        } else{
            genre.text = "Unknown Genre"
        }
    }
    private fun setupImage(bookInfo: BookResponse){
       if (bookInfo.volumeInfo?.imageLinks?.thumbnail != null){
           val picassoUrl = bookInfo.volumeInfo?.imageLinks?.thumbnail
           Log.i("Picasso url", picassoUrl)

           Picasso.get().load(picassoUrl).placeholder(R.drawable.placeholder).fit().centerCrop().into(bookCover)
       } else {
           bookCover.setImageResource(R.drawable.placeholder)
       }

    }
    }

