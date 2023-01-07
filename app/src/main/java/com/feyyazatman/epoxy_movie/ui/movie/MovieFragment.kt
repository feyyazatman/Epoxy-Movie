package com.feyyazatman.epoxy_movie.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.feyyazatman.epoxy_movie.databinding.FragmentMovieBinding
import com.feyyazatman.epoxy_movie.ui.movie.epoxy.ButtonOnClickListener
import com.feyyazatman.epoxy_movie.ui.movie.epoxy.MovieController
import com.feyyazatman.epoxy_movie.ui.movie.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieFragment : Fragment() , ButtonOnClickListener{

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = MovieController(this@MovieFragment)
        binding.epoxyRecyclerView.setController(controller)
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            it?.body()?.movies?.map { movie ->
                movie
            }?.let { movieList -> movieList.toMutableList()
                .let { movieListMutable -> controller.submitPopularMovies(movieListMutable) } }
        }
        viewModel.inTheatre.observe(viewLifecycleOwner) {
            it?.body()?.movies?.map { movie ->
                movie
            }?.let { movieList -> movieList.toMutableList()
                .let { movieListMutable -> controller.submitNowPlayingMovies(movieListMutable) } }
        }
        viewModel.upcomingMovie.observe(viewLifecycleOwner) {
            it?.body()?.movies?.map { movie ->
                movie
            }?.let { movieList -> movieList.toMutableList()
                .let { movieListMutable -> controller.submitUpcomingMovies(movieListMutable) } }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(id: String?) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(id)
        findNavController().navigate(action)
    }

}