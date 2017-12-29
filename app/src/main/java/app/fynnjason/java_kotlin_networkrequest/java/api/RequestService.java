package app.fynnjason.java_kotlin_networkrequest.java.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import app.fynnjason.java_kotlin_networkrequest.App;
import app.fynnjason.java_kotlin_networkrequest.java.base.BaseEnity;
import app.fynnjason.java_kotlin_networkrequest.java.bean.GankBean;
import app.fynnjason.java_kotlin_networkrequest.utils.Utils;
import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FynnJason.
 * Des：request
 */

public class RequestService implements ApiService {

    private static final String BASE_URL = "http://gank.io/api/data/";

    private static ApiService sApiService = null;

    private RequestService() {
        //Cache
        File cacheFile = new File(App.Companion.getInstance().getCacheDir(), "HttpCache");
        int cacheSize = 10 * 10 * 1024 * 1024; // 100M
        Cache cache = new Cache(cacheFile, cacheSize);
        //OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .retryOnConnectionFailure(true)
                .build();
        //Retrofit
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

    //Cache Interceptor
    private static Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (Utils.isNetworkAvailable()) {
                String cacheControl = request.cacheControl().toString();
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", cacheControl)
                        .build();
            }
            return response;
        }
    };

    @Override
    public Observable<BaseEnity<List<GankBean>>> allGank() {
        return sApiService.allGank();
    }

}
