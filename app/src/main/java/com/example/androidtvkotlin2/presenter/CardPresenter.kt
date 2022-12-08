package com.example.androidtvkotlin2.presenter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.Presenter
import com.example.androidtvkotlin2.R
import com.example.androidtvkotlin2.model.GamingCard

class CardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_header_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val rootView = viewHolder.view

        rootView.findViewById<TextView>(R.id.textView1).text = item.toString()
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        Log.i("UNBIND", "onUnbindViewHolder: ")
    }
}