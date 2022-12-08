package com.example.androidtvkotlin2.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.example.androidtvkotlin2.R
import com.example.androidtvkotlin2.model.GamingCard
import com.example.androidtvkotlin2.presenter.CardPresenter
import com.example.androidtvkotlin2.presenter.CardPresenter2
import com.example.androidtvkotlin2.presenter.backgroundmanager.mBackgroundManager

class MainActivityFragment : BrowseSupportFragment() {

    companion object {
        private lateinit var backgroundManager: mBackgroundManager
        private lateinit var _context: Context
    }

    private val mRowAdapter = ArrayObjectAdapter(ListRowPresenter())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _context = requireContext()
        setupTitle()
        loadData()
        loadData2()
        loadVideoData()

        backgroundManager = mBackgroundManager(requireActivity())
        onItemViewSelectedListener = setupOnselectListener()
        onItemViewClickedListener = setupOnClickListener(requireActivity())
        adapter = mRowAdapter
    }

    private fun setupTitle() {
        title = "Hello world"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(
            requireContext(),
            R.color.purple_200
        )
    }

    private fun loadData() {
        val gridHeader = HeaderItem(0, "Simple Card view")

        val cardPresenter = CardPresenter()
        val gridRowAdapter = ArrayObjectAdapter(cardPresenter)
        gridRowAdapter.add("ITEM 1")
        gridRowAdapter.add("ITEM 2")
        gridRowAdapter.add("ITEM 3")

        mRowAdapter.add(ListRow(gridHeader, gridRowAdapter))
    }

    private fun loadData2() {
        // Header Item (To right text)
        val gridHeader = HeaderItem(1, "Custom Card view with Details")

        // Items per Row
        val cardPresenter = CardPresenter2()
        val gridRowAdapter = ArrayObjectAdapter(cardPresenter)
        gridRowAdapter.add(GamingCard("Title 1", "Desc 1", Color.RED))
        gridRowAdapter.add(GamingCard("Title 2", "Desc 2", Color.CYAN))
        gridRowAdapter.add(GamingCard("Title 3", "Desc 3", Color.GREEN))
        gridRowAdapter.add(GamingCard("Title 4", "Desc 4", Color.YELLOW))
        gridRowAdapter.add(GamingCard("Title 5", "Desc 5", Color.BLUE))
        gridRowAdapter.add(GamingCard("Title 6", "Desc 6", Color.MAGENTA))
        mRowAdapter.add(ListRow(gridHeader, gridRowAdapter))
    }

    private fun loadVideoData() {
        val gridHeaderItem = HeaderItem(3, "Video Playback")

        val cardPresenter = CardPresenter()
        val gridRowAdapter = ArrayObjectAdapter(cardPresenter)
        gridRowAdapter.add("Video Sample")
        gridRowAdapter.add("Slice samples")
        gridRowAdapter.add("Setting & Preference")
        mRowAdapter.add(ListRow(gridHeaderItem, gridRowAdapter))
    }

    private class setupOnselectListener() : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if(item is String) {
                //backgroundManager.updateBackground(null)
            } else if(item is GamingCard) {
                backgroundManager.updateBackground(item.backgroundImage)
            }
        }
    }

    private class setupOnClickListener(val activity: Activity) : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if(item is GamingCard) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("selectedTitle", item.title)
                intent.putExtra("selectedDesc", item.description)
                intent.putExtra("selectedBG", item.backgroundImage)
                activity.startActivity(intent)
            } else {
                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle()
                lateinit var intent: Intent
                val seletedItem = item as String

                if(seletedItem.contains("Video Sample")) {
                    intent = Intent(activity, VideoActivity::class.java)
                } else if(seletedItem.contains("Setting & Preference")) {
                    intent = Intent(activity, SettingActivity::class.java)
                } else if(seletedItem.contains("Slice samples")) {
                    intent = Intent(activity, SliceActivity::class.java)
                } else {
                    Toast.makeText(_context, "$item", Toast.LENGTH_SHORT).show()
                }

                activity.startActivity(intent, bundle)
            }
        }
    }



}