package com.example.androidtvkotlin2.provider

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.*
import com.example.androidtvkotlin2.R
import com.example.androidtvkotlin2.view.MainActivity

class MySliceProvider : SliceProvider() {

    private lateinit var _context: Context
    override fun onCreateSliceProvider(): Boolean {
        _context = context ?: return false
        return true;
    }

    override fun onBindSlice(sliceUri: Uri): Slice? {
        val slice: Slice?

        when(sliceUri.path) {
            "/hello" -> return createSimpleAction(sliceUri)
            "/hello2" -> return createActionWithActionInRow(sliceUri)
            "/hello3" -> return createActionRange(sliceUri)
        }
        return null
    }

    private fun createSimpleAction(sliceUri: Uri): Slice {
        val sampleAction = SliceAction.create(
            PendingIntent.getActivity(_context, sliceUri.hashCode(), Intent(_context, MainActivity::class.java), 0),
            IconCompat.createWithResource(_context, R.drawable.ic_launcher_foreground),
            ListBuilder.ICON_IMAGE,
            "Action title"
        )

        return list(_context, sliceUri, ListBuilder.INFINITY) {
            setAccentColor(0x000000)
            row {
                setTitle("Hello world")
                setSubtitle("Description")
                setPrimaryAction(sampleAction)
                addEndItem(IconCompat.createWithResource(_context, R.drawable.ic_launcher_foreground), ListBuilder.ICON_IMAGE, false)
            }
        }
    }

    private fun createActionWithActionInRow(sliceUri: Uri): Slice {
        // Primary action - open wifi settings.
        val wifiAction = SliceAction.create(
            PendingIntent.getActivity(_context, sliceUri.hashCode(), Intent(_context, MainActivity::class.java), 0),
            IconCompat.createWithResource(_context, R.drawable.ic_launcher_foreground),
            ListBuilder.ICON_IMAGE,
            "Wi-Fi Settings"
        )

        // Toggle action - toggle wifi.
        val toggleAction = SliceAction.createToggle(
            PendingIntent.getActivity(_context, sliceUri.hashCode(), Intent(_context, MainActivity::class.java), 0),
            "Toggle Wi-Fi",
            false
        )

        // Create the parent builder.
        return list(_context, sliceUri, ListBuilder.INFINITY) {
            row {
                setTitle("Sample Toggle")
                setPrimaryAction(wifiAction)
            }
            addAction(toggleAction)
        }
    }

    private fun createActionRange(sliceUri: Uri): Slice {
        val inputRangeAction = PendingIntent.getActivity(_context, sliceUri.hashCode(), Intent(_context, MainActivity::class.java), 0)
        val primaryAction = SliceAction.create(
            PendingIntent.getActivity(_context, sliceUri.hashCode(), Intent(_context, MainActivity::class.java), 0),
            IconCompat.createWithResource(_context, R.drawable.ic_launcher_foreground),
            ListBuilder.ICON_IMAGE,
            "Placeholder primary action"
        )
        return list(_context, sliceUri, ListBuilder.INFINITY) {
            inputRange {
                setTitle("Sample Range")
                setPrimaryAction(primaryAction)
                setInputAction(inputRangeAction)
                value = 0
                max = 100
                min = 0
            }
        }
    }
}