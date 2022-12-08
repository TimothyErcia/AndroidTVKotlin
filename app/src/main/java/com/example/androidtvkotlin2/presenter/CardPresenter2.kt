package com.example.androidtvkotlin2.presenter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.example.androidtvkotlin2.R
import com.example.androidtvkotlin2.model.GamingCard

class CardPresenter2 : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        _context = parent.context
        val imageCardView = ImageCardView(parent.context)
        imageCardView.focusable = View.FOCUSABLE
        imageCardView.isFocusableInTouchMode = true
        imageCardView.setBackgroundColor(parent.context.getColor(R.color.teal_200))
        return ViewHolder(imageCardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        if(item != null && viewHolder != null) {
            val rootView = mViewHolder(viewHolder.view)
            val gamingCard = item as GamingCard
            rootView.mCardView.titleText = gamingCard.title
            rootView.mCardView.contentText = gamingCard.description
            rootView.mCardView.mainImage = rootView.getDefaultImage()
            rootView.mCardView.setMainImageDimensions(800, 420)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        Log.i("UNBIND", "onUnbindViewHolder: ")
    }

    companion object {
        private lateinit var _context: Context
        class mViewHolder(view: View) : ViewHolder(view) {
            var mCardView: ImageCardView
            var mDefaultCardImage: Drawable

            init {
                mCardView = view as ImageCardView
                mDefaultCardImage = _context.resources.getDrawable(R.drawable.ic_launcher_background, null)
            }

            fun getDefaultImage(): Drawable {
                return mDefaultCardImage
            }
        }
    }
}