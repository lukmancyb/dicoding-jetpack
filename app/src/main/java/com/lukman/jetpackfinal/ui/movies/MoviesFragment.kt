package com.lukman.jetpackfinal.ui.movies

import android.os.Bundle
import android.util.Log.e
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lukman.jetpackfinal.R
import com.lukman.jetpackfinal.data.source.remote.network.ApiResponse
import com.lukman.jetpackfinal.databinding.FragmentMoviesBinding
import com.lukman.jetpackfinal.ui.movies.adapter.MovieAdapter
import com.lukman.jetpackfinal.utilities.isShown
import com.lukman.jetpackfinal.vo.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesFragment : Fragment() {

    companion object {
        const val TAG = "MoviesFragment"
    }

    private var _movieFragmentBinding: FragmentMoviesBinding? = null
    private val binding get() = _movieFragmentBinding!!
    private val viewModel: MoviesViewModel by viewModels()
    private val movieAdapter : MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _movieFragmentBinding =
            FragmentMoviesBinding.inflate(LayoutInflater.from(inflater.context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.loadMovies().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    movieAdapter.submitList(result.data)
                }

                is Resource.Error -> {
                    binding.progressBar.isVisible = false

                    e(TAG, result.message ?: "")
                }

                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    fun setupRecyclerView(){
        binding.rvMovie.adapter = movieAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _movieFragmentBinding = null
    }


}