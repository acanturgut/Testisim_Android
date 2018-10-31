package android.testisim.com.testisim.activities.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.testisim.com.testisim.R
import kotlinx.android.synthetic.main.activity_test_third_step.*

class TestThirdStepActivity : AppCompatActivity() {

    private var fromHandFreeMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_third_step)
        if (fromHandFreeMode) {
            startSpeaking()
        }

        testThirdStepBackButton.setOnClickListener {
            onBackPressed()
        }

        testThirdStepCompleteTest.setOnClickListener {
            openDecisionActivity()
        }

        testThirdStepStopSpeakingButton.setOnClickListener {
            stopSpeaking()
        }
    }

    private fun openDecisionActivity() {
        val intent = Intent(this, TestDecisionActivity::class.java)
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
