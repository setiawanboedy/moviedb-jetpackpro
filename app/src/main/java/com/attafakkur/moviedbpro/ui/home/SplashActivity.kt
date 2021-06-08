package com.attafakkur.moviedbpro.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.utils.Constants.TIME_DELAY

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                val mainIntent = Intent(this, HomeActivity::class.java)
                startActivity(mainIntent)
                finish()
            }, TIME_DELAY)
        }
    }
}