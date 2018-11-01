package com.testisim.activities

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.testisim.com.testisim.R
import android.view.InputEvent
import android.view.KeyEvent
import android.view.MotionEvent
import com.unity3d.player.UnityPlayer
import kotlinx.android.synthetic.main.activity_three_d_object.*
import android.widget.FrameLayout


class ThreeDObjectActivity : AppCompatActivity() {

    private lateinit var player : UnityPlayer
    private lateinit var fl : FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        requestWindowFeature(1)

        super.onCreate(savedInstanceState)

        window.setFormat(2)

        player = UnityPlayer(this)

        setContentView(R.layout.activity_three_d_object)

        threeDimensionGoBackButton.setOnClickListener {
            onBackPressed()
        }

        fl = findViewById(R.id.fl_unity)
        fl.addView(player.view)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    override fun onDestroy() {
        player.quit()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onResume() {
        super.onResume()
        player.resume()
    }

    override fun onConfigurationChanged(configuration: Configuration) {
        super.onConfigurationChanged(configuration)
        player.configurationChanged(configuration)
    }

    override fun onWindowFocusChanged(bl: Boolean) {
        super.onWindowFocusChanged(bl)
        player.windowFocusChanged(bl)
    }

    override fun dispatchKeyEvent(keyEvent: KeyEvent): Boolean {
        return if (keyEvent.getAction() === 2) {
            player.injectEvent(keyEvent as InputEvent)
        } else super.dispatchKeyEvent(keyEvent)
    }

    override fun onKeyUp(n2: Int, keyEvent: KeyEvent): Boolean {
        return player.injectEvent(keyEvent as InputEvent)
    }

    override fun onKeyDown(n2: Int, keyEvent: KeyEvent): Boolean {
        return player.injectEvent(keyEvent as InputEvent)
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        return player.injectEvent(motionEvent as InputEvent)
    }

    override fun onGenericMotionEvent(motionEvent: MotionEvent): Boolean {
        return player.injectEvent(motionEvent as InputEvent)
    }
}
