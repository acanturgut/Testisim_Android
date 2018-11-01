package com.testisim.activities.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.testisim.com.testisim.R
import com.testisim.activities.splash.AgeSelectionActivity.Companion.AGE_TYPE_KEY
import kotlinx.android.synthetic.main.activity_age_informative.*

class AgeInformativeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_informative)

        val ageType = AgeType.values()[intent.getIntExtra(AGE_TYPE_KEY, 0)]

        ageInformationInformativeText.text =  when (ageType) {

            AgeType.FIFTEEN -> "15-25 yaş arasındaki erkeklerde görülen kanser türleri arasında birinci sıradadır. Ama erken teşhis ile yaklaşık %100 oranında iyileşme sağlanabilir. İşte bunu başaran ünlüler."
            AgeType.TWENTY_FIVE -> "Testis Kanseri en sık 25-35 yaş arasında görülür. Ama erken teşhis ile yaklaşık %100 oranında iyileşme sağlanabilir. İşte bunu başaran ünlüler."
            AgeType.THIRTY_FIVE -> "Testis Kanseri en sık 15-35 yaş arasında görülür. 35 yaş üstünde nadiren risk teşkil eder. Erken teşhis ile yaklaşık %100 oranında iyileşme sağlanabilir. "
        }

        ageInformationBackButton.setOnClickListener {
            onBackPressed()
        }

        ageInformationContinueButton.setOnClickListener {
            startCelebrityActivity(0)
        }

    }

    private fun startCelebrityActivity(level: Int) {
        val intent = Intent(this, CelebrityActivity::class.java)
        intent.putExtra(CelebrityActivity.LEVEL_KEY, level)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}
