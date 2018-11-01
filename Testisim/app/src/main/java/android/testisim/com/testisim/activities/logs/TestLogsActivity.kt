package android.testisim.com.testisim.activities.logs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.support.v7.widget.LinearLayoutManager
import android.testisim.com.testisim.R
import android.testisim.com.testisim.models.TestModelList
import android.testisim.com.testisim.singletons.TestisimKeyStore
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_test_logs.*

class TestLogsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_logs)

        logsGoBackButton.setOnClickListener {
            onBackPressed()
        }
        logsRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val logsAdapter = LogsAdapter()
        logsRecyclerView.adapter = logsAdapter

        val mPrefs = getDefaultSharedPreferences(applicationContext)

        val json = mPrefs.getString(TestisimKeyStore.TESTS_KEY, "")
        val test = Gson().fromJson<TestModelList>(json, TestModelList::class.java)

        Handler().postDelayed({
            try {
                logsAdapter.updateList(test.listOfTest)
                logsProgressBar.visibility = View.GONE
            } catch (e: Exception) {

                logsEmptyState.visibility = View.VISIBLE
                logsProgressBar.visibility = View.GONE
            }
        }, 300)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}
