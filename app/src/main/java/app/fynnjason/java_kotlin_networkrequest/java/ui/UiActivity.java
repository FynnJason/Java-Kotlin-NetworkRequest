package app.fynnjason.java_kotlin_networkrequest.java.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import app.fynnjason.java_kotlin_networkrequest.R;
import app.fynnjason.java_kotlin_networkrequest.java.adapter.DataAdapter;
import app.fynnjason.java_kotlin_networkrequest.java.api.RequestService;
import app.fynnjason.java_kotlin_networkrequest.java.base.BaseActivity;
import app.fynnjason.java_kotlin_networkrequest.java.base.BaseEnity;
import app.fynnjason.java_kotlin_networkrequest.java.base.BaseObserver;
import app.fynnjason.java_kotlin_networkrequest.java.bean.GankBean;


/**
 * Created by FynnJason.
 * Des：界面
 */

public class UiActivity extends BaseActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        RequestService.getInstance().allGank()
                .compose(this.<BaseEnity<List<GankBean>>>compose())
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
