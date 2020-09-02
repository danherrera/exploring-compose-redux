package com.example.composetest.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

typealias LiveDataEvent<T> = LiveData<Event<T>>
typealias MutableLiveDataEvent<T> = MutableLiveData<Event<T>>

fun <T> MutableLiveDataEvent<T>.setEvent(newValue: T) = run { value = Event(newValue) }

fun <T> LiveDataEvent<T>.observeEvents(lifecycleOwner: LifecycleOwner, handle: (T) -> Unit) {
    observe(lifecycleOwner, { nullableEvent ->
        nullableEvent?.let { event ->
            event.getContentIfNotHandled()?.let { unhandledEvent ->
                handle(unhandledEvent)
            }
        }
    })
}
}