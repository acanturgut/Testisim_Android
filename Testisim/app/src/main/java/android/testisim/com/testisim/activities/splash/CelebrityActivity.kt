package android.testisim.com.testisim.activities.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.testisim.com.testisim.R
import android.testisim.com.testisim.activities.MainActivity
import kotlinx.android.synthetic.main.activity_celebrity.*

class CelebrityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_celebrity)

        when (intent.getIntExtra(LEVEL_KEY, 0)) {

            0 -> {
                celebrityImages.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.robert_de_niro))
                celebrityText.text = "Jamie ‘Taboo’ Gomez’ e 2014 yılında testis kanseri belirtilerinin ortaya çıkmasının ardından testis kanseri teşhisi koyuldu. Tanı koyulduğunda kanser yayılmış olduğu için cerrahi operasyon ve kemoterapi tedavisi gördü. Şuanda tamamıyla iyileşti."
            }
            1 -> {
                celebrityImages.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.arjenrobben_cropped_1_wjzpa_24_f_04_x_71_sx_0_ixy_3_bp_4_gv))
                celebrityText.text = "2004 yılında testisinde farkettiği bir kitle sonrasında tanı koyulan Arjen Robben, geçirdiği cerrahi operasyon sonrasında sağlıklı bir şekilde yaşamına devam ediyor."
            }
            2 -> {
                celebrityImages.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.jamie_taboo))
                celebrityText.text = "1996 yılında yayılımlı testis kanseri teşhisi koyuldu. Tedavilerinin ardından sağlığına kavuşan Lance Armstrong, kendi adını taşıyan ve kanser savaşçılarını destekleyen bir vakıf kurdu."
            }
            3 -> {
                celebrityImages.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.lance_armstrong))
                celebrityText.text = "Yıllarca hayatını şakalar üzerine kuran komedyen Tom Green’ e 2000 yılında testis kanseri teşhisi koyuldu. Geçirdiği cerrahi operasyonlar sonrasında sağlığına kavuştu. Halen, testis kanseri hakkında farkındalığı arttırmaya yönelik gösteriler düzenliyor. "
            }
        }

        celebrityBackButton.setOnClickListener {
            onBackPressed()
        }

        celebrityImagesContinueButton.setOnClickListener {
            when (intent.getIntExtra(LEVEL_KEY, 0)) {

                0 -> {
                    startCelebrityActivity(1)
                }
                1 -> {
                    startCelebrityActivity(2)
                }
                2 -> {
                    startCelebrityActivity(3)
                }
                3 -> {
                    startMainMenuActivity()
                }
            }
        }
    }

    private fun startCelebrityActivity(level: Int) {
        val intent = Intent(this, CelebrityActivity::class.java)
        intent.putExtra(LEVEL_KEY, level)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    private fun startMainMenuActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    companion object {
        const val LEVEL_KEY = "levelKey"
    }
}
