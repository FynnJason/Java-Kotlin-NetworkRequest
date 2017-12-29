package app.fynnjason.java_kotlin_networkrequest.java.api;

import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import app.fynnjason.java_kotlin_networkrequest.java.base.BaseEnity;
import app.fynnjason.java_kotlin_networkrequest.java.bean.GankBean;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FynnJason.
 * Des：request
 */

public class RequestService implements ApiService {

    private static final String BASE_URL ="http://gank.io/api/data/";

    private static ApiService sApiService = null;

    private RequestService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        sApiService = retrofit.create(ApiService.class);
    }

    private static class Holder {
        private static RequestService sInstance = new RequestService();
    }

    public static RequestService getInstance() {
        return Holder.sInstance;
    }


    @Override
    public Observable<BaseEnity<List<GankBean>>> allGank() {
        return sApiService.allGank();
    }

}
