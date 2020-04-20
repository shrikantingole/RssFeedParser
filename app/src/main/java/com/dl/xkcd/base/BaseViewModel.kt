package com.dl.xkcd.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ccpp.shared.core.result.Event

open class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Event<String>> = MutableLiveData()
    var loading: MutableLiveData<Event<Boolean>> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = Event(failure.javaClass.toString())
    }

}