package app.fynnjason.java_kotlin_networkrequest.kotlin

import app.fynnjason.java_kotlin_networkrequest.java.base.BaseEnity
import app.fynnjason.java_kotlin_networkrequest.java.bean.GankBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by FynnJason.
 * Des：api
 */
interface ApiService {
    @Headers("Cache-Control:public ,max-age=60")
    @GET("all/20/1")
    fun allGank(): Observable<BaseEnity<List<GankBean>>>
}