package android.testisim.com.testisim.activities.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.testisim.com.testisim.R
import android.testisim.com.testisim.activities.MainActivity
import kotlinx.android.synthetic.main.activity_test_result.*

class TestResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_result)

        val resultType = TestResultType.values()[intent.getIntExtra(TestDecisionActivity.TEST_DECISION, 0)]

        when (resultType) {

            TestResultType.Yes -> {
                testResultIcon.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.yes))
                testResultText.text = "1 Ay sonra görüşmek üzere :)"

            }
            TestResultType.No -> {
                testResultIcon.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.no))
                testResultText.text = "Şüphelendiğiniz bir durum var. \u2028Mutlaka vakit geçirmeden doktorunuza başvurun!"
            }
        }

        testResultGoBackMenuButton.setOnClickListener {
            startMainMenuActivity()
        }
    }

    private fun startMainMenuActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
        finish()
    }
}

enum class TestResultType {
    Yes, No
}
