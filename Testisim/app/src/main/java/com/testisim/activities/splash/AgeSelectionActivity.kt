package com.testisim.activities.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.testisim.com.testisim.R
import kotlinx.android.synthetic.main.activity_age_selection.*

class AgeSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_selection)

        fifteenButton.setOnClickListener {
            startAgeInformationActivity(AgeType.FIFTEEN)
        }

        twentyFiveButton.setOnClickListener {
            startAgeInformationActivity(AgeType.TWENTY_FIVE)
        }

        thirtyFiveButton.setOnClickListener {
            startAgeInformationActivity(AgeType.THIRTY_FIVE)
        }
    }

    private fun startAgeInformationActivity(ageType: AgeType) {
        val intent = Intent(this, AgeInformativeActivity::class.java)
        intent.putExtra(AGE_TYPE_KEY, ageType.ordinal)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    companion object {
        const val AGE_TYPE_KEY = "ageTypeKey"
    }
}

enum class AgeType {
    FIFTEEN, TWENTY_FIVE, THIRTY_FIVE
}
