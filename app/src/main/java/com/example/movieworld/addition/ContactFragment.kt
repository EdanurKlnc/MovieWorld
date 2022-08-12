package com.example.movieworld.addition

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieworld.R
import com.example.movieworld.databinding.FragmentContactBinding
import com.example.movieworld.singleList.MovieListFragmentDirections


class ContactFragment : Fragment() {
    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(R.drawable.img)
            .into(binding.img)

        binding.homeButton2.setOnClickListener {
            val action = ContactFragmentDirections.actionContactFragmentToMovieListFragment()
            findNavController().navigate(action)
        }
        binding.exitButton2.setOnClickListener{
            val action = ContactFragmentDirections.actionContactFragmentToLoginFragment()
            findNavController().navigate(action)

        }

        binding.buttonSend.setOnClickListener {
            val message =binding.editTextMessage.text.toString().trim()
            sendMail(message)
        }
    }
    private fun sendMail(message:String) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_TEXT,message)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Choose an email"))

    }
}

