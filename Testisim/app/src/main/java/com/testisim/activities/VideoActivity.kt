package com.testisim.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.testisim.com.testisim.R
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        videoBackButton.setOnClickListener {
            onBackPressed()
        }

        videoWebView.settings.javaScriptEnabled = true
        videoWebView.loadUrl("https://www.youtube.com/embed/xZ6-ZoZIaqc")

    }



    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }




}
