package com.testisim.activities.settings

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.testisim.com.testisim.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settingsGoBackButton.setOnClickListener {
            onBackPressed()
        }

        settingsCredit.setOnClickListener {
            startCreditActivity()
        }

        settingsTerms.setOnClickListener {
            startTermActivity()
        }
    }

    private fun startCreditActivity() {
        val intent = Intent(this, CreditsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    private fun startTermActivity() {
        val intent = Intent(this, TermsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

}
