package app.fynnjason.java_kotlin_networkrequest.java.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import app.fynnjason.java_kotlin_networkrequest.R;
import app.fynnjason.java_kotlin_networkrequest.java.adapter.DataAdapter;
import app.fynnjason.java_kotlin_networkrequest.java.api.RequestService;
import app.fynnjason.java_kotlin_networkrequest.java.base.BaseEnity;
import app.fynnjason.java_kotlin_networkrequest.java.base.BaseObserver;
import app.fynnjason.java_kotlin_networkrequest.java.bean.GankBean;
import app.fynnjason.java_kotlin_networkrequest.utils.ToastUtils;
import app.fynnjason.java_kotlin_networkrequest.utils.Utils;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by FynnJason.
 * Des：界面
 */

public class UiActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        RequestService.getInstance().allGank()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (!Utils.isNetworkAvailable(UiActivity.this)) {
                            ToastUtils.show("没开网");
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<GankBean>>() {
                    @Override
                    public void onSuccess(List<GankBean> gankBeans) {
                        DataAdapter adapter = new DataAdapter(gankBeans);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        Log.e("UiActivity", "onFail：" + e.getMessage());
                    }
                });
    }
}
