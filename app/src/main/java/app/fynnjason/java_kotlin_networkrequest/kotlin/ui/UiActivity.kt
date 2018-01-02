package app.fynnjason.java_kotlin_networkrequest.kotlin.ui

import android.os.Bundle
import app.fynnjason.java_kotlin_networkrequest.R
import app.fynnjason.java_kotlin_networkrequest.java.base.BaseActivity
import app.fynnjason.java_kotlin_networkrequest.kotlin.RequestService

/**
 * Created by FynnJason.
 * Des：
 */
class UiActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)
        RequestService.getInstance()
    }
}