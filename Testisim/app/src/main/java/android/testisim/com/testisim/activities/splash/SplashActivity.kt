package android.testisim.com.testisim.activities.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.testisim.com.testisim.R
import android.testisim.com.testisim.activities.MainActivity
import android.testisim.com.testisim.singletons.TestisimKeyStore
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        auth = FirebaseAuth.getInstance()
    }

    private fun goNextPage() {

        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val isTutorialShown = preferences.getBoolean(TestisimKeyStore.TUTORIAL_KEY, false)

        val handler = Handler()
        handler.postDelayed({
            if (isTutorialShown) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                val intent = Intent(this, AgeSelectionActivity::class.java)
                startActivity(intent)
            }
        }, 1000)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        currentUser?.let {
            goNextPage()
        } ?: kotlin.run {
            auth.signInAnonymously()
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            goNextPage()
                        } else {
                            // If sign in fails, display a message to the user.
                            finish()
                        }
                    }
        }
    }
}
