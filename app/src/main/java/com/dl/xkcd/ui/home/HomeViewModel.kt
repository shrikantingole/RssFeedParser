package com.dl.xkcd.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ccpp.shared.core.result.Event
import com.ccpp.shared.core.result.Results
import com.ccpp.shared.domain.model.RssFeed
import com.ccpp.shared.network.repository.RssFeedRepository
import com.dl.xkcd.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: RssFeedRepository) :
    BaseViewModel() {

    private val _loginResult = MutableLiveData<Event<RssFeed>>()
    val loginResult: LiveData<Event<RssFeed>> = _loginResult

    fun callRssFeedAsync() {
        if (loading.value?.getContentIfNotHandled() == false) return
        loading.postValue(Event(true))
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.callRssFeedAsync()) {
                is Results.Success -> _loginResult.postValue(Event(result.data))
                is Results.Error -> failure.postValue(Event(result.exception.message.toString()))
            }
            loading.postValue(Event(false))
        }
    }

}
