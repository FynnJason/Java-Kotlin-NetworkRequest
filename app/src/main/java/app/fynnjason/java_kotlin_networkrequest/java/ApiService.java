package app.fynnjason.java_kotlin_networkrequest.java;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by FynnJason.
 * Des：Api
 */

public interface ApiService {
    @GET("all/20/1")
    Observable<BaseEnity<GankInfo>> allGank();
}
