package com.dl.xkcd.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ccpp.shared.core.result.Event
import com.ccpp.shared.core.result.Results
import com.ccpp.shared.network.repository.RssFeedRepository
import com.dl.xkcd.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: RssFeedRepository) :
    BaseViewModel() {

    private val imageList = arrayListOf<RssItem>()
    private var position = 0

    fun callRssFeedAsync() {
        if (loading.value?.getContentIfNotHandled() == false) return
        loading.postValue(Event(true))
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.callRssFeedAsync()) {
                is Results.Success -> handleResult(result.data)
                is Results.Error -> failure.postValue(Event(result.exception.message.toString()))
            }
            loading.postValue(Event(false))
        }
    }

    private fun handleResult(rssFeed: RssFeed) {
        imageList.clear()
        rssFeed.items?.let {
            imageList.addAll(it)
        }
        position = imageList.size  //4
        _imageObserver.postValue(Event(imageList[--position]))
    }

    private val _imageObserver = MutableLiveData<Event<RssItem>>()
    val imageObserver: LiveData<Event<RssItem>> = _imageObserver

    fun getNextImage() {
        if (imageList.lastIndex > position)
            _imageObserver.value = Event(imageList[++position])
    }

    fun getPreImage() {
        if (0 < position)
            _imageObserver.value = Event(imageList[--position])
    }

}
