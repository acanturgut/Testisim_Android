package android.testisim.com.testisim.activities.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.testisim.com.testisim.R
import kotlinx.android.synthetic.main.activity_test_decision.*
import com.google.gson.Gson
import android.content.Context
import android.preference.PreferenceManager
import android.testisim.com.testisim.models.TestModel
import android.testisim.com.testisim.models.TestModelList
import android.testisim.com.testisim.singletons.TestisimKeyStore
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class TestDecisionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_decision)

        testDecisionBackButton.setOnClickListener {
            onBackPressed()
        }

        testDecisionNoButton.setOnClickListener {
            setDataToSharedPref(TestResultType.No)
            openTestResultActivity(TestResultType.No)
        }

        testDecisionYesButton.setOnClickListener {
            setDataToSharedPref(TestResultType.Yes)
            openTestResultActivity(TestResultType.Yes)
        }
    }

    private fun setDataToSharedPref(testResultType: TestResultType) {

        val mPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        var listOfTests = TestModelList(arrayListOf())
        val isTestResultYes = testResultType == TestResultType.Yes

        val df = SimpleDateFormat("EEE, d MMM yyyy, HH:mm")
        val date = df.format(Calendar.getInstance().getTime())

        try {

            val json = mPrefs.getString(TestisimKeyStore.TESTS_KEY, "")
            val test = Gson().fromJson<TestModelList>(json, TestModelList::class.java)
            listOfTests = test.copy()

        } catch (e: Exception) {
            println("Exception")
        }

        listOfTests.listOfTest.add(TestModel(date = date, isSuccess = isTestResultYes))

        val prefsEditor = mPrefs.edit()
        val json = Gson().toJson(listOfTests)
        prefsEditor.putString(TestisimKeyStore.TESTS_KEY, json)
        prefsEditor.apply()
    }

    private fun openTestResultActivity(testResultType: TestResultType) {
        val intent = Intent(this, TestResultActivity::class.java)
        intent.putExtra(TEST_DECISION, testResultType.ordinal)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
        finish()
    }

    companion object {
        const val TEST_DECISION = "testDecisionKey"
    }
}
