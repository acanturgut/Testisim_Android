package android.testisim.com.testisim.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.testisim.com.testisim.R
import android.testisim.com.testisim.activities.test.TestInformationActivity
import android.testisim.com.testisim.singletons.TestisimKeyStore
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AlertDialog
import android.testisim.com.testisim.activities.dictionary.DictionaryActivity
import android.testisim.com.testisim.activities.logs.TestLogsActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val editor = preferences.edit()
        editor.putBoolean(TestisimKeyStore.TUTORIAL_KEY, true)
        editor.apply()

        mainMenuSymptomButton.setOnClickListener {
            startSymptomsActivity()
        }

        mainMenuStartTestButton.setOnClickListener {
            startTestActivity()
        }

        mainMenuVideoButton.setOnClickListener {
            startVideoActivity()
        }

        mainMenuDictionary.setOnClickListener {
            startDictionaryActivity()
        }

        mainMenuThreeDimension.setOnClickListener {
            startThreeDimensionObjectActivity()
        }

        mainMenuLogs.setOnClickListener {
            startLogsActivity()
        }

        mainMenuSettingsButton.setOnClickListener {
            startSettingsActivity()
        }

        mainMenuVoice.setOnClickListener {
            openVoicePrompt()
        }
    }

    private fun startSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    private fun startTestActivity() {
        val intent = Intent(this, TestInformationActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    private fun startThreeDimensionObjectActivity() {
        val intent = Intent(this, ThreeDObjectActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    private fun startVideoActivity() {
        val intent = Intent(this, VideoActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    private fun openVoicePrompt() {
        val builder1 = AlertDialog.Builder(this)
        builder1.setTitle("Sesle Çalıştır")
        builder1.setMessage("Sesle çalıştır özelliği ile bütün testi sana sorulan soruları cevaplayarak tamamlayabilirsin.")
        builder1.setCancelable(true)
        builder1.setPositiveButton(
                "Testi Başlat"
        ) {
            dialog, id -> dialog.cancel()

            startTestActivity()
        }

        builder1.setNegativeButton(
                "İptal"
        ) { dialog, id -> dialog.cancel() }

        val alert11 = builder1.create()
        alert11.show()
    }

    private fun startLogsActivity() {
        val intent = Intent(this, TestLogsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    private fun startSymptomsActivity() {
        val intent = Intent(this, SymptomActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    private fun startDictionaryActivity() {
        val intent = Intent(this, DictionaryActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
       // super.onBackPressed()
    }
}
