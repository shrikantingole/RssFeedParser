package com.dl.xkcd.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.ccpp.shared.core.result.EventObserver
import com.ccpp.shared.util.viewModelProvider
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
        binding = FragmentHomeBinding.inflate(inflater).apply {
            lifecycleOwner = this@HomeFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)

        Glide.with(this)
            .load("https://imgs.xkcd.com/comics/rip_john_conway.gif")
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
            .into(binding.ivImage)
        viewModel.loginResult.observe(viewLifecycleOwner, EventObserver
        {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
        })
        viewModel.callRssFeedAsync()

    }

}
