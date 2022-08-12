package com.example.movieworld.singleList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieworld.databinding.MovieSingleItemBinding
import com.example.movieworld.model.Movie
import com.example.movieworld.network.Poster_base_url
import kotlinx.android.synthetic.main.movie_single_item.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies = emptyList<Movie>()
    private lateinit var viewModel: MovieViewModel


    inner class ViewHolder(val binding: MovieSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MovieSingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }


    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currMovie = movies[position]

        holder.binding.cvMovieTitle.text = movies.get(position).title
        holder.binding.cvMovieReleaseDate.text = movies.get(position).release_date

        val moviePosterURL = Poster_base_url + movies.get(position).poster_path
        Glide.with(holder.itemView)
            .load(moviePosterURL)
            .into(holder.itemView.cvImageView);

        holder.itemView.setOnClickListener{mView->
            val direction = MovieListFragmentDirections.actionMovieListFragmentToDetailsFragment(currMovie)
            mView.findNavController().navigate(direction)
        }

    }

    fun setData(newMovieModelList: List<Movie>) {
        val oldList = movies
        val diffResult = DiffUtil.calculateDiff(MovieDiffUtilCallback(oldList, newMovieModelList))
        this.movies = newMovieModelList
        diffResult.dispatchUpdatesTo(this)

    }
}



