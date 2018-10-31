package android.testisim.com.testisim.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.testisim.com.testisim.R
import android.testisim.com.testisim.activities.splash.AgeSelectionActivity
import kotlinx.android.synthetic.main.activity_symptom.*

class SymptomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom)

        symptomBackButton.setOnClickListener {
            onBackPressed()
        }

        symptomTutorialButton.setOnClickListener {
            startTutorialsActivity()
        }
    }

    private fun startTutorialsActivity() {
        val intent = Intent(this, AgeSelectionActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}
