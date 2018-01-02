package app.fynnjason.java_kotlin_networkrequest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.fynnjason.java_kotlin_networkrequest.java.ui.UiActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_java.setOnClickListener { startActivity(Intent(this, UiActivity::class.java)) }
        btn_kotlin.setOnClickListener { startActivity(Intent(this, app.fynnjason.java_kotlin_networkrequest.kotlin.ui.UiActivity::class.java)) }
    }
}
