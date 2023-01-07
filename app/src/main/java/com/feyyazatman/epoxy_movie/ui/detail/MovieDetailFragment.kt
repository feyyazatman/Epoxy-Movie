package com.feyyazatman.epoxy_movie.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.feyyazatman.epoxy_movie.R
import com.feyyazatman.epoxy_movie.databinding.FragmentMovieDetailBinding
import com.feyyazatman.epoxy_movie.ui.detail.epoxy.MovieDetailController
import com.feyyazatman.epoxy_movie.ui.detail.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val args : MovieDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = args.id
        viewModel.getMovie(movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = MovieDetailController()
        binding.epoxyRecyclerView.setController(controller)
        viewModel.movie.observe(viewLifecycleOwner) {
            it?.body()?.let { movieDetail ->
                controller.submitMovieDetail(movieDetail)
            }
        }
        viewModel.cast.observe(viewLifecycleOwner) {
            it?.body()?.cast?.map { cast ->
                cast
            }?.let { castList -> castList.toMutableList()
                .let { castListMutable -> controller.submitCast(castListMutable) } }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}