package com.example.movieworld.addition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieworld.R
import com.example.movieworld.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(R.drawable.img_1)
            .into(img)

        binding.buttonLogin.setOnClickListener {
            Toast.makeText(requireContext(), "Hatalı Kullanıcı Girişi", Toast.LENGTH_LONG)

            if (editTextName.getText().toString() == "Edanur" && editTextSurname.getText()
                    .toString() == "K"
            ) {
                val action = LoginFragmentDirections.actionLoginFragmentToMovieListFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Hatalı Kullanıcı Girişi", Toast.LENGTH_LONG)
                    .show()

            }
        }
    }
}