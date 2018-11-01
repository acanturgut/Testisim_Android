package android.testisim.com.testisim.activities.test

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity;
import android.testisim.com.testisim.R
import android.testisim.com.testisim.activities.MainActivity
import kotlinx.android.synthetic.main.activity_test_information.*
import android.speech.tts.TextToSpeech
import android.widget.Toast
import java.util.*
import android.content.ActivityNotFoundException
import android.speech.RecognizerIntent
import android.speech.tts.UtteranceProgressListener
import android.view.View

class TestInformationActivity : AppCompatActivity() {

    private lateinit var tts: TextToSpeech
    private var fromHandFreeMode: Boolean = false

    private val CODE_SPEECH_INPUT = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_information)

        fromHandFreeMode = intent.getBooleanExtra(MainActivity.IS_COMES_FROM_VOICE_KEY, false)

        testInformationCloseViceButton.visibility = if (fromHandFreeMode) View.VISIBLE else View.GONE

        testInformationBackButton.setOnClickListener {
            onBackPressed()
        }

        testInformationStartButton.setOnClickListener {
            startFirstTestActivity()
        }

        testInformationCloseViceButton.setOnClickListener {
            stopSpeaking()
        }

        if (fromHandFreeMode) {
            initTTS()
        }
    }

    override fun onStart() {
        super.onStart()

        Handler().postDelayed({

            if (fromHandFreeMode) {
                speak()
            }

        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (fromHandFreeMode) {
            tts.stop()
            tts.shutdown()
        }
    }

    override fun onStop() {
        super.onStop()
        if (fromHandFreeMode) {
            tts.stop()
            tts.shutdown()
        }
    }

    private fun stopSpeaking() {
        if (fromHandFreeMode) {
            tts.stop()
        }
    }

    private fun initTTS() {

        tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {

            if (it == TextToSpeech.LANG_MISSING_DATA || it == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show()
            }
        })

        tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onError(utteranceId: String?) {}

            override fun onDone(utteranceId: String?) {
                startSTT()
            }

            override fun onStart(utteranceId: String?) {}
        })

        tts.language = Locale("tr", "TR")
    }

    private fun startSTT() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something")
        try {
            startActivityForResult(intent, CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
        }
    }

    private fun speak() {

        val text = "${testInformationTextInit.text} ${testInformationTextNext.text}"
        val textToSpeak = " $text Devam etmek için devam diyebilir yada sonlandır diyerek ana sayfaya dönebilirsin."

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, "TEST")
        } else {
            tts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data) {

                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                    if (result[0].toLowerCase().contains("devam et")) {
                        startFirstTestActivity(true)
                    } else if (result[0].toLowerCase().contains("ana sayfa")) {
                        onBackPressed()
                    }
                }
            }
        }
    }

    private fun startFirstTestActivity(isComeFromVoice: Boolean = false) {
        val intent = Intent(this, TestFirstStepActivity::class.java)
        intent.putExtra(MainActivity.IS_COMES_FROM_VOICE_KEY, isComeFromVoice)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
        finish()
    }
}
