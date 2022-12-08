package com.example.androidtvkotlin2.presenter.backgroundmanager

import android.app.Activity
import android.graphics.Color
import androidx.leanback.app.BackgroundManager
import kotlin.random.Random

class mBackgroundManager(val activity: Activity) {

    private val backgroundManager: BackgroundManager = BackgroundManager.getInstance(activity)

    init {
        backgroundManager.attach(activity.window)
    }

    fun updateBackground(color: Int?) {
        if(color != null) {
            backgroundManager.color = color
        } else {
            backgroundManager.color = Color.rgb(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
        }
    }
}