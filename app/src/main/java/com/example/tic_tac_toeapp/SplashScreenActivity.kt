package com.example.tic_tac_toeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity() {

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val animation1 = AnimationUtils.loadAnimation(this,R.anim.rotate)
        tv_splash.startAnimation(animation1)
        tv_splash.startAnimation(animation1)

        val runable = Runnable { kotlin.run {
            val intent = Intent(this,PlayerNamesActivity::class.java)
            startActivity(intent)
            finish()
        } }

        handler.postDelayed(runable,4500)
    }
}


