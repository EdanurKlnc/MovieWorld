package com.example.movieworld.details

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieworld.databinding.FragmentDetailsBinding
import com.example.movieworld.model.Movie
import com.example.movieworld.network.Poster_base_url
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var movie: Movie
    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = args.movie

        getDetails()

    }

    private fun getDetails() {
        binding.apply {
            detailsTitle.text = movie.title
            tv_year2.text = movie.release_date
            tv_overview.text = movie.overview
            tv_detailslanguage.text = movie.original_language
            movie.vote_average.also { tv_count.text = it.toString() }

            val moviePosterURL = Poster_base_url + movie.poster_path
            Glide.with(this@DetailsFragment)
                .load(moviePosterURL)
                .into(ivDetailsSmallPoster)
            Glide.with(this@DetailsFragment)
                .load(moviePosterURL)
                .into(icDetailsImage)
        }
        binding.btnMoredetails.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "İzliyorum".plus(movie.title).plus("-").plus(movie.release_date).plus("\n")
                        .plus("https:").plus(movie.id)
                )
                putExtra(Intent.EXTRA_TITLE, "Paylaş".plus(movie.title).plus("\n"))
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, "Paylaş")
            startActivity(shareIntent)
        }

    }


}


