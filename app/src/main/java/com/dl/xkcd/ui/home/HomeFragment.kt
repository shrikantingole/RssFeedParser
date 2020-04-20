package com.dl.xkcd.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ccpp.shared.core.result.EventObserver
import com.ccpp.shared.util.viewModelProvider
import com.dl.xkcd.app.App
import com.dl.xkcd.base.BaseFragment
import com.dl.xkcd.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelProvider(viewModelFactory)
        binding = FragmentHomeBinding.inflate(inflater).apply {
            model = viewModel
            lifecycleOwner = this@HomeFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.imageObserver.observe(viewLifecycleOwner, EventObserver {

            Glide.with(App.instance)
                .load(it.image)
                .into(binding.ivImage)

        })
        binding.btnPrevious.setOnClickListener { viewModel.getNextImage() }
        binding.btnNext.setOnClickListener { viewModel.getPreImage() }
        binding.btnTopPrevious.setOnClickListener { viewModel.getNextImage() }
        binding.btnTopNext.setOnClickListener { viewModel.getPreImage() }
        viewModel.callRssFeedAsync()

    }

}
