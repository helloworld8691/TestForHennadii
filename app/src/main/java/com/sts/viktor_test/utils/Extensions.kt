package com.sts.viktor_test.utils

import android.app.Activity
import android.content.Intent

fun Activity.startIntent(intent: Intent, finish : Boolean = true){
    startActivity(intent)
    if (finish) finish()
}