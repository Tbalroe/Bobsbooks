package com.example.bobsbooks.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.bobsbooks.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({

            startActivity(Intent(this, NavigationActivity::class.java))

            finish()
        },2000)
    }
}