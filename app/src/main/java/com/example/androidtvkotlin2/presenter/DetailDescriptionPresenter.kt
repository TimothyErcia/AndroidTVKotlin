package com.example.androidtvkotlin2.presenter

import android.view.ViewGroup
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import androidx.leanback.widget.Presenter
import com.example.androidtvkotlin2.model.GamingCard

class DetailDescriptionPresenter : AbstractDetailsDescriptionPresenter() {
    override fun onBindDescription(vh: ViewHolder, item: Any?) {
        if(item != null) {
            val gamingCard = item as GamingCard
            vh.title.text = gamingCard.title
            vh.subtitle.text = gamingCard.backgroundImage.toString()
            vh.body.text = gamingCard.description
        }
    }
}