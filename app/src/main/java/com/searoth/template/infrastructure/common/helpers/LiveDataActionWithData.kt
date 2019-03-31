package com.searoth.template.infrastructure.common.helpers


import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * A lifecycle-aware observable that executes an observer lambda every time an
 * action occurs and used to send back data to observer.
 */
class LiveDataActionWithData<T> {

    private val liveData = MutableLiveData<T>()

    fun getData(): MutableLiveData<T> {
        return liveData
    }

    @MainThread
    fun observe(owner: LifecycleOwner, observer: (T) -> Unit) {
        liveData.observe(owner, Observer<T> { value ->
            value?.let { observer(value) }
        })
    }

    /**
     * This function allows easy testing without needing a LifecycleOwner.
     */
    @MainThread
    fun observeForever(observer: (T) -> Unit) {
        liveData.observeForever({ value ->
            value?.let { observer(value) }
        })
    }

    @MainThread
    fun actionOccurred(value: T?) {
        liveData.value = value
    }

    fun actionOccuredPost(value: T?) {
        liveData.postValue(value)
    }
}