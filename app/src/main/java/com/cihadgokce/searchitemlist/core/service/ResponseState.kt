package com.cihadgokce.searchitemlist.core.service


//herhangi bir servis çağırımı dönen cevaplar


sealed class ResponseState<out T> {

    class Success<T>(val data: T) : ResponseState<T>()

    class Error(val errorType: String, val message: String) : ResponseState<Nothing>()

    object Loading : ResponseState<Nothing>()
}