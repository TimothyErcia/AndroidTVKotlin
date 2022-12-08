package com.example.androidtvkotlin2.view

import android.app.slice.Slice
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.customview.widget.ExploreByTouchHelper
import androidx.fragment.app.FragmentActivity
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.slice.widget.SliceLiveData
import androidx.slice.widget.SliceView
import com.example.androidtvkotlin2.R

class SliceActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GuidedStepSupportFragment.addAsRoot(this, SliceActivityFragment(this), android.R.id.content)
    }

}