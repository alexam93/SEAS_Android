package com.example.cinesaragon.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.cinesaragon.R
import com.example.cinesaragon.core.Resource
import com.example.cinesaragon.data.remote.MovieDataSource
import com.example.cinesaragon.databinding.FragmentMovieBinding
import com.example.cinesaragon.presentation.MovieViewModel
import com.example.cinesaragon.presentation.MovieViewModelFactory
import com.example.cinesaragon.repository.MovieRepositoryImpl
import com.example.cinesaragon.repository.RetrofitClient
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.cinesaragon.data.model.Movie
import com.example.cinesaragon.ui.movie.adapters.MovieAdapter
import com.example.cinesaragon.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.cinesaragon.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.cinesaragon.ui.movie.adapters.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {


    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel>{
        MovieViewModelFactory(MovieRepositoryImpl(MovieDataSource(RetrofitClient.webservice)))
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        //viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
        //    when(result){
        //            is Resource.Loading -> { Log.d("LiveData","Cargado...")}
        //            is Resource.Succes -> { Log.d("LiveDataSucces", "UpComing: ${result.data.first} \n \n TopRated: ${result.data.second} \n" +
        //                    " \n Popular: ${result.data.third}")}
        //            is Resource.Failure -> { Log.d("Error", "${result.exception}")}
        //        }
        //    })

        //viewModel.fetchUpcomingMovies().observe(viewLifecycleOwner, Observer { result ->
        //    when(result){
        //        is Resource.Loading -> { Log.d("LiveData","Cargado...")}
        //        is Resource.Succes -> { Log.d("LiveDataSucces", "${result.data}")}
        //        is Resource.Failure -> { Log.d("Error", "${result.exception}")}
        //    }
        //})

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
           //when(result){
           //    is Resource.Loading -> { binding.progressBar.visibility = View.VISIBLE}
           //    is Resource.Succes -> {
           //        Log.d("LiveData1", "${result.data.first.results}")
           //        Log.d("LiveData2", "${result.data.second.results}")
           //        Log.d("LiveData3", "${result.data.third.results}")
           //        binding.progressBar.visibility = View.GONE
           //        concatAdapter.apply {
           //            addAdapter(0,UpcomingConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
           //            addAdapter(1,TopRatedConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
           //            addAdapter(2,PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))
           //        }
           //        binding.rvMovies.adapter = concatAdapter
           //    }
           //    is Resource.Failure -> {binding.progressBar.visibility = View.GONE
           //        Log.d("Error", "${result.exception}")}
           //}
            when(result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Succes -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }

                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Error", "${result.exception}")
                }
            }

        })
    }

    override fun onMovieClick(movie: Movie) {
        //Log.d("Movie", "onMovieClick: $movie ")
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date)
        findNavController().navigate(action)
    }

}