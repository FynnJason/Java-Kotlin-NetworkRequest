package app.fynnjason.java_kotlin_networkrequest.kotlin.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import app.fynnjason.java_kotlin_networkrequest.R
import app.fynnjason.java_kotlin_networkrequest.java.adapter.DataAdapter
import app.fynnjason.java_kotlin_networkrequest.java.api.RequestService
import app.fynnjason.java_kotlin_networkrequest.java.base.BaseActivity
import app.fynnjason.java_kotlin_networkrequest.java.base.BaseObserver
import app.fynnjason.java_kotlin_networkrequest.java.bean.GankBean
import kotlinx.android.synthetic.main.activity_ui.*

/**
 * Created by FynnJason.
 * Des：
 */
class UiActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)
        recycler_view.layoutManager = LinearLayoutManager(this)

        RequestService.getInstance().allGank()
                .compose(this.compose())
                .subscribe(object : BaseObserver<List<GankBean>>() {
                    override fun onSuccess(gankBeans: List<GankBean>) {
                        val adapter = DataAdapter(gankBeans)
                        recycler_view.adapter = adapter
                    }

                    override fun onFail(e: Throwable) {
                        Log.e("UiActivity", "onFail：" + e.message)
                    }
                })
    }
}