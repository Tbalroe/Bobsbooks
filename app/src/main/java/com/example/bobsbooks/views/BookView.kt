package com.example.bobsbooks.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bobsbooks.R
import com.example.bobsbooks.models.Book
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.book_view.view.*


class BookView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {



    fun initiateView(bookInfo: Book, deleteButtonClickedCallback: () -> Unit, launchNewFragment: () -> Unit) {
        title.text = bookInfo.volumeInfo.title

        if (bookInfo.volumeInfo.authors?.joinToString() != null){
            val authorToUpperCase = bookInfo.volumeInfo.authors?.joinToString()
            author.text = authorToUpperCase?.toUpperCase()

        } else{ author.text = "Unknown Author"}


        setStarRatingViews(bookInfo.volumeInfo.averageRating)
        rating.text = bookInfo.volumeInfo.averageRating.toString()

        if (bookInfo.volumeInfo?.categories?.joinToString() != null){
            genre.text = bookInfo.volumeInfo?.categories?.joinToString()
        } else{
            genre.text = "Unknown Genre"
        }

        this.setOnLongClickListener { launchNewFragment.invoke()
            true}
        favorited.setOnClickListener { deleteButtonClickedCallback.invoke() }
       Picasso.get().load(bookInfo.volumeInfo.imageLinks.thumbnail).fit().centerCrop().into(bookCover)
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
}