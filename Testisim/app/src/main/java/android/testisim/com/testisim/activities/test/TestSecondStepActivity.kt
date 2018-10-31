package android.testisim.com.testisim.activities.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.testisim.com.testisim.R
import kotlinx.android.synthetic.main.activity_test_second_step.*

class TestSecondStepActivity : AppCompatActivity() {

    private var fromHandFreeMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_second_step)
        if (fromHandFreeMode) {
            startSpeaking()
        }

        testSecondStepBackButton.setOnClickListener {
            onBackPressed()
        }

        testSecondStepToThirdStepButton.setOnClickListener {
            startThirdTestActivity()
        }

        testSecondStepStopSpeakingButton.setOnClickListener {
            stopSpeaking()
        }
    }

    private fun startThirdTestActivity() {
        val intent = Intent(this, TestThirdStepActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    private fun stopSpeaking() { }

    private fun startSpeaking() { }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
        finish()
    }
}
