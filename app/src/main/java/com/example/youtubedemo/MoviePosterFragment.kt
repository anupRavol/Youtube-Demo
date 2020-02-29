package com.example.youtubedemo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.popular_list_item.*

/**
 * A simple [Fragment] subclass.
 */
class MoviePosterFragment : Fragment() {


     val videoPosterThumbnail: String? = null
     val posterTitle: String? = null

    companion object {


        fun getInstance(posterThumbnail: String, posterTitle: String): MoviePosterFragment {
            val fragment = MoviePosterFragment()
            val bundle = Bundle()
            bundle.apply {
                putString("thumbnail", posterThumbnail)
                putString("title", posterTitle)
            }

            fragment.arguments = bundle

            return fragment

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_poster, container, false)
    }


}
