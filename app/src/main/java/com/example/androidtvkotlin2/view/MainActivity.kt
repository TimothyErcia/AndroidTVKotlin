package com.example.androidtvkotlin2.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.androidtvkotlin2.R

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}