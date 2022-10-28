package com.zelix.biznesplanuzb.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.zelix.biznesplanuzb.R
import com.zelix.biznesplanuzb.util.MySharedPreference

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.statusBarColor = ContextCompat.getColor(this, R.color.main_color)


        Handler(Looper.myLooper()!!).postDelayed({
            if (MySharedPreference.isSigned!!) {
                if (MySharedPreference.isPaid!!) {
                    if (MySharedPreference.isLogged!!) {
                        startActivity(Intent(this, SuccessActivity::class.java))
                    } else {
                        startActivity(Intent(this, MainActivity3::class.java))
                    }
                } else {
                    startActivity(Intent(this, MainActivity2::class.java))
                }
            } else {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
            }
            finish()
        }, 2000)


    }
}