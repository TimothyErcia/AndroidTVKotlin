package com.example.androidtvkotlin2.view

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.GuidanceStylist
import androidx.leanback.widget.GuidedAction
import androidx.leanback.widget.GuidedActionsStylist
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.slice.Slice
import androidx.slice.widget.SliceLiveData
import androidx.slice.widget.SliceView
import com.example.androidtvkotlin2.R
import kotlin.math.log

class SliceActivityFragment(val mcontext: Context) : GuidedStepSupportFragment() {

    private lateinit var liveData: LiveData<Slice>
    private lateinit var sliceView: SliceView
    companion object {
        private lateinit var _context: Context
    }

    init {
        _context = mcontext
    }

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        return GuidanceStylist.Guidance("Sample Step fragment with Slice", "mock description", null, null)
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        super.onCreateActions(actions, savedInstanceState)
        val sliceAction1 = GuidedAction.Builder(_context)
            .id(0)
            .focusable(true)
            .build()

        val sliceAction2 = GuidedAction.Builder(_context)
            .id(1)
            .focusable(true)
            .build()

        val sliceAction3 = GuidedAction.Builder(_context)
            .id(2)
            .focusable(true)
            .build()

        actions.add(sliceAction1)
        actions.add(sliceAction2)
        actions.add(sliceAction3)
    }

    override fun onCreateActionsStylist(): GuidedActionsStylist {
        return object : GuidedActionsStylist() {
            override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
                val view = LayoutInflater.from(_context).inflate(R.layout.slice_items, parent, false)
                return ViewHolder(view)
            }

            override fun onBindViewHolder(vh: ViewHolder, action: GuidedAction) {
                super.onBindViewHolder(vh, action)

                sliceView = vh?.itemView?.findViewById<SliceView>(R.id.sliceView)
                sliceView?.mode = SliceView.MODE_SMALL
                sliceView?.setAccentColor(Color.MAGENTA)

                var uri: String
                if(action.id.toInt() == 0) {
                    uri = "content://com.example.androidtvkotlin2/hello"
                    liveData = SliceLiveData.fromUri(_context, Uri.parse(uri))
                    liveData.observe(this@SliceActivityFragment, sliceView!!)
                } else if(action.id.toInt() == 1) {
                    uri = "content://com.example.androidtvkotlin2/hello2"
                    liveData = SliceLiveData.fromUri(_context, Uri.parse(uri))
                    liveData.observe(this@SliceActivityFragment, sliceView!!)
                } else {
                    uri = "content://com.example.androidtvkotlin2/hello3"
                    liveData = SliceLiveData.fromUri(_context, Uri.parse(uri))
                    liveData.observe(this@SliceActivityFragment, sliceView!!)
                }
            }
        }
    }

    override fun onGuidedActionClicked(action: GuidedAction?) {
        super.onGuidedActionClicked(action)
        if(action != null) {
            Log.i("ONCLICKED", "onGuidedActionClicked: ")
            liveData.observe(this@SliceActivityFragment, sliceView!!)
        }
    }

}