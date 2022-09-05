package com.example.cinesaragon.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cinesaragon.R
import com.example.cinesaragon.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}").centerCrop().into(binding.imgMovie)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.backgroundImageUrl}").centerCrop().into(binding.imgBackground)
        binding.txtDescription.text = args.overview
        binding.txtMovieTitle.text = args.title
        binding.txtLenguage.text = "Idioma ${args.language}"
        binding.txtRating.text = "${args.voteAverage} (${args.voteCount} Reviews)"
        binding.txtReleased.text = "Estreno ${args.releaseDate}"

        val buttonMapa = view.findViewById<Button>(R.id.btnMapa)
        buttonMapa.setOnClickListener{
            findNavController().navigate(R.id.action_movieDetailFragment_to_mapsActivity)

        }
        val buttonLogin = view.findViewById<Button>(R.id.btnEntrada)
        buttonLogin.setOnClickListener{
            findNavController().navigate(R.id.action_movieDetailFragment_to_loginFragment2)

        }
    }
}