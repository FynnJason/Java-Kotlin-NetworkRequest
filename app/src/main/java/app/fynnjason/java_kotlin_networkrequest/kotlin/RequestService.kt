package app.fynnjason.java_kotlin_networkrequest.kotlin

import app.fynnjason.java_kotlin_networkrequest.kotlin.api.ApiService
import app.fynnjason.java_kotlin_networkrequest.utils.ToastUtils

/**
 * Created by FynnJason.
 * Des：request
 */
class RequestService private constructor() {
    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = RequestService()
    }

    val BASE_URL = "http://gank.io/api/data/"

    var apiService: ApiService? = null

    init {
        ToastUtils.show("hello")
    }
}