package com.feyyazatman.epoxy_movie.ui.tvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.feyyazatman.epoxy_movie.databinding.FragmentTvShowBinding
import com.feyyazatman.epoxy_movie.ui.tvShow.epoxy.TvShowController
import com.feyyazatman.epoxy_movie.ui.tvShow.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<TvShowViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = TvShowController()
        binding.epoxyRecyclerView.setController(controller)
        viewModel.tvShowPopular.observe(viewLifecycleOwner) {
            it?.body()?.results?.map { tvShow ->
                tvShow
            }?.let { tvShowList -> tvShowList.toMutableList()
                .let { tvShowListMutable -> controller.submitTvShow(tvShowListMutable) } }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}