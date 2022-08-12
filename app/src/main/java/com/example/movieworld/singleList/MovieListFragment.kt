package com.example.movieworld.singleList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieworld.R
import com.example.movieworld.databinding.FragmentMovieListBinding
import com.example.movieworld.model.Movie
import kotlinx.coroutines.launch

class MovieListFragment : Fragment(R.layout.fragment_movie_list){
    private val _movieViewModel by viewModels<MovieViewModel>()

    private val MovieAdapter by lazy { MovieAdapter() }
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieListBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.contactButton.setOnClickListener{
            val action = MovieListFragmentDirections.actionMovieListFragmentToContactFragment()
            findNavController().navigate(action)
        }
        binding.exitButton.setOnClickListener{
            val action = MovieListFragmentDirections.actionMovieListFragmentToLoginFragment()
            findNavController().navigate(action)

        }

        val adapter = MovieAdapter()
        binding.recyclerView.adapter = adapter

        val rv = binding.recyclerView
        rv.apply {
            this.adapter = adapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3) //Filmleri listelerken sütun sayısı
        }


        lifecycleScope.launch {
            _movieViewModel.movieResponse.observe(requireActivity()) {
                adapter.setData(it.results)
            }
        }
    }

}


