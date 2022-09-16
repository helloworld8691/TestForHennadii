package com.sts.viktor_test.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.sts.viktor_test.MainActivity
import com.sts.viktor_test.R
import com.sts.viktor_test.utils.startIntent
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launchWhenCreated {
            delay(1000)
            startIntent(MainActivity.getIntent(this@SplashActivity))
        }

    }
}