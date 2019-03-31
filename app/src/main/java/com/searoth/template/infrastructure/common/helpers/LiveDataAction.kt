package com.searoth.template.infrastructure.common.helpers

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class LiveDataAction {

    private val liveData = MutableLiveData<Boolean>()

    @MainThread
    fun observe(owner: LifecycleOwner, observer: () -> Unit) {
        liveData.observe(owner, Observer<Boolean> { value ->
            value?.let {
                observer()
            }
        })
    }

    /**
     * This function allows easy testing without needing a LifecycleOwner.
     */
    @MainThread
    fun observeForever(observer: () -> Unit) {
        liveData.observeForever { value ->
            value?.let {
                observer()
            }
        }
    }

    @MainThread
    fun actionOccurred() {
        liveData.value = true
    }

    fun getValue(): Boolean? {
        return liveData.value
    }

    fun actionOccurredPost() {
        liveData.postValue(true)
    }
}