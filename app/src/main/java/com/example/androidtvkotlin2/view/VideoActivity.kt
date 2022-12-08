package com.example.androidtvkotlin2.view

import android.media.MediaPlayer
import android.media.session.PlaybackState
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.fragment.app.FragmentActivity
import com.example.androidtvkotlin2.R

class VideoActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
    }
}