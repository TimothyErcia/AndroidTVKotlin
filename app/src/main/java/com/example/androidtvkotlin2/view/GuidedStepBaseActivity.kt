package com.example.androidtvkotlin2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.leanback.app.GuidedStepSupportFragment
import com.example.androidtvkotlin2.R

class GuidedStepBaseActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GuidedStepSupportFragment.addAsRoot(this, GuidedStepActivityFragment(), android.R.id.content)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}