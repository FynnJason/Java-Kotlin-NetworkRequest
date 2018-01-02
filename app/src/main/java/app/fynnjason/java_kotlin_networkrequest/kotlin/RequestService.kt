package app.fynnjason.java_kotlin_networkrequest.kotlin

import app.fynnjason.java_kotlin_networkrequest.App
import app.fynnjason.java_kotlin_networkrequest.java.base.BaseEnity
import app.fynnjason.java_kotlin_networkrequest.java.bean.GankBean
import app.fynnjason.java_kotlin_networkrequest.kotlin.api.ApiService
import app.fynnjason.java_kotlin_networkrequest.utils.Utils
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Created by FynnJason.
 * Des：request
 */
class RequestService private constructor() : ApiService {
    override fun allGank(): Observable<BaseEnity<List<GankBean>>> = apiService.allGank()

    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = RequestService()
    }

    val BASE_URL = "http://gank.io/api/data/"

    var apiService: ApiService? = null

    init {
        //cache
        val REWRITE_CACHE_CONTROL_INTERCEPTOR = Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            if (Utils.isNetworkAvailable()) {
                val cacheControl = request.cacheControl().toString()
                return@Interceptor response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", cacheControl)
                        .build()
            }
            response
        }
        val cacheFile = File(App.Instance!!.cacheDir, "HttpCache")
        val cacheSize: Long = 10 * 10 * 1024 * 1024
        val cache = Cache(cacheFile, cacheSize)
        //OkHttpClient
        val client = OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .retryOnConnectionFailure(true)
                .build();
        //Retrofit
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        apiService = retrofit.create(ApiService::class.java)
    }

}