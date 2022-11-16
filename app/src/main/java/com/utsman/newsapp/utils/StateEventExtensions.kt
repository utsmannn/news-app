package com.utsman.newsapp.utils

import com.utsman.newsapp.event.StateEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T, U>Response<T>.asFlowEvent(mapper: (T?) -> U): Flow<StateEvent<U>> {
    return flow {
        if (isSuccessful) {
            val dataResponse = body()
            val data = mapper.invoke(dataResponse)
            val successEvent = StateEvent.Success(data)
            emit(successEvent)
        } else {
            val throwable = Throwable("Failure")
            val failureEvent = StateEvent.Failure<U>(throwable)
            emit(failureEvent)
        }
    }
}

fun <T>StateEvent<T>.onSuccess(block: (T) -> Unit): StateEvent<T> {
    if (this is StateEvent.Success) {
        block.invoke(data)
    }
    return this
}

fun <T>StateEvent<T>.onIdle(block: () -> Unit): StateEvent<T> {
    if (this is StateEvent.Idle) {
        block.invoke()
    }
    return this
}

fun <T>StateEvent<T>.onLoading(block: () -> Unit): StateEvent<T> {
    if (this is StateEvent.Loading) {
        block.invoke()
    }
    return this
}

fun <T>StateEvent<T>.onFailure(block: (Throwable) -> Unit): StateEvent<T> {
    if (this is StateEvent.Failure) {
        block.invoke(throwable)
    }
    return this
}
