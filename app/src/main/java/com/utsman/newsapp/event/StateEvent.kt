package com.utsman.newsapp.event

sealed class StateEvent<T> {
    class Idle<T> : StateEvent<T>()
    class Loading<T> : StateEvent<T>()
    class Success<T>(val data: T) : StateEvent<T>()
    class Failure<T>(val throwable: Throwable) : StateEvent<T>()
}