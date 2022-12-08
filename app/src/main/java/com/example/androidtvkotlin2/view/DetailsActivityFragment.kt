package com.example.androidtvkotlin2.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.*
import com.example.androidtvkotlin2.R
import com.example.androidtvkotlin2.model.GamingCard
import com.example.androidtvkotlin2.presenter.CardPresenter2
import com.example.androidtvkotlin2.presenter.DetailDescriptionPresenter
import com.example.androidtvkotlin2.presenter.backgroundmanager.mBackgroundManager

class DetailsActivityFragment : DetailsSupportFragment() {

    private lateinit var customFullWidthDetailsOverviewRowPresenter: FullWidthDetailsOverviewRowPresenter
    private lateinit var selectedGamingCard: GamingCard
    private lateinit var mBackgroundManager: mBackgroundManager
    private lateinit var rowAdapter: ArrayObjectAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBackgroundManager = mBackgroundManager(requireActivity())
        setupData()
        setupUI()
    }

    private fun setupData() {
        val data = requireActivity().intent
        selectedGamingCard = GamingCard(data.getStringExtra("selectedTitle"), data.getStringExtra("selectedDesc"), data.getIntExtra("selectedBG", 0))
    }

    private fun setupUI() {
        mBackgroundManager.updateBackground(selectedGamingCard.backgroundImage)

        customFullWidthDetailsOverviewRowPresenter = FullWidthDetailsOverviewRowPresenter(DetailDescriptionPresenter())
        customFullWidthDetailsOverviewRowPresenter.initialState = FullWidthDetailsOverviewRowPresenter.STATE_SMALL
//        customFullWidthDetailsOverviewRowPresenter = DetailsOverviewRowPresenter(DetailDescriptionPresenter())

        // 1st Row, Left side Image, and Details
        val sparseArrayObjectAdapter = SparseArrayObjectAdapter()
        for (i in 0..4) {
            sparseArrayObjectAdapter.set(i, Action(i.toLong(), "Label $i", "Sub-label $i"))
        }
        val detailsOverviewRow = DetailsOverviewRow(selectedGamingCard)
        detailsOverviewRow.actionsAdapter = sparseArrayObjectAdapter
        detailsOverviewRow.setImageBitmap(context, context?.resources?.getDrawable(R.drawable.ic_launcher_foreground, null)?.toBitmap())

        // Bottom Row
        rowAdapter = ArrayObjectAdapter(CardPresenter2())
        rowAdapter.add(GamingCard("Title 1", "Desc 1", Color.RED))
        rowAdapter.add(GamingCard("Title 2", "Desc 2", Color.CYAN))
        rowAdapter.add(GamingCard("Title 3", "Desc 3", Color.GREEN))

        // ArrayObjectAdapter to complete all the rows
        val classPresenterSelector = ClassPresenterSelector()
        classPresenterSelector.addClassPresenter(DetailsOverviewRow::class.java, customFullWidthDetailsOverviewRowPresenter)
        classPresenterSelector.addClassPresenter(ListRow::class.java, ListRowPresenter())

        val myAdapter = ArrayObjectAdapter(classPresenterSelector)
        myAdapter.add(detailsOverviewRow)
        myAdapter.add(ListRow(null, rowAdapter))
        adapter = myAdapter
    }

    override fun onStop() {
        super.onStop()
        activity?.finish()
    }
}