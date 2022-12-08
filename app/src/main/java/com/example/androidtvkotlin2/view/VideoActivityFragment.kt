package com.example.androidtvkotlin2.view

import android.content.Context
import android.media.session.MediaSession
import android.media.session.PlaybackState
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.*
import androidx.leanback.widget.*

class VideoActivityFragment : VideoSupportFragment() {

    companion object {
        private val TAG = "VideoActivityFragment"
    }

    private val constantVideo1 = "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4"
    private val constantVideo2 = "android.resource://com.example.androidtvkotlin2/raw/big_buck_bunny_720p_5mb"

    private lateinit var session: MediaSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val playerGlue = myVideoPlaybackControlGlue(requireContext(), MediaPlayerAdapter(requireContext()))
        playerGlue.host = VideoSupportFragmentGlueHost(this@VideoActivityFragment)
        playerGlue.title = "Sample Title"
        playerGlue.subtitle = "sample subtitle"
        playerGlue.playerAdapter.setDataSource(Uri.parse(constantVideo2))

        session = MediaSession(requireContext(), "PlaybackService").apply {
            setCallback(myMediaCallback())
            setFlags(
                MediaSession.FLAG_HANDLES_MEDIA_BUTTONS or MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS
            )
        }

        if(!session.isActive) {
            session.isActive = true
        }
    }

    override fun onStop() {
        super.onStop()
        activity?.finish()
    }

    class myVideoPlaybackControlGlue(context: Context, val mediaPlayerAdapter: MediaPlayerAdapter) : PlaybackTransportControlGlue<MediaPlayerAdapter>(context, MediaPlayerAdapter(context)) {
        val skipPreviousAction = PlaybackControlsRow.SkipPreviousAction(context)
        val fastForwardAction = PlaybackControlsRow.FastForwardAction(context)
        val rewindAction = PlaybackControlsRow.RewindAction(context)
        val skipNextAction = PlaybackControlsRow.SkipNextAction(context)

        override fun onPreparedStateChanged() {
            super.onPreparedStateChanged()
            if(isPrepared) {
                seekProvider = mySeekProvider()
                play()
            }
        }

        override fun onPlayStateChanged() {
            super.onPlayStateChanged()
            Log.i(TAG, "onPlayStateChanged")
        }

        override fun onCreatePrimaryActions(primaryActionsAdapter: ArrayObjectAdapter?) {
            super.onCreatePrimaryActions(primaryActionsAdapter)
            primaryActionsAdapter?.add(skipPreviousAction)
            primaryActionsAdapter?.add(rewindAction)
            primaryActionsAdapter?.add(fastForwardAction)
            primaryActionsAdapter?.add(skipNextAction)
        }

        override fun onActionClicked(action: Action) {
            super.onActionClicked(action)
            when(action) {
                skipNextAction -> mediaPlayerAdapter.previous()
                rewindAction -> mediaPlayerAdapter.rewind()
                fastForwardAction -> mediaPlayerAdapter.fastForward()
                skipNextAction -> mediaPlayerAdapter.next()
            }
        }
    }

    class myMediaCallback : MediaSession.Callback() {

    }

    class mySeekProvider() : PlaybackSeekDataProvider() {
        override fun getSeekPositions(): LongArray {

            return super.getSeekPositions()
        }

        override fun getThumbnail(index: Int, callback: ResultCallback?) {
            super.getThumbnail(index, callback)
        }

        override fun reset() {
            super.reset()
        }
    }
}
