package com.example.androidtvkotlin2.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.leanback.preference.LeanbackPreferenceFragmentCompat
import androidx.leanback.preference.LeanbackSettingsFragmentCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import com.example.androidtvkotlin2.R

class SettingActivityFragment : LeanbackSettingsFragmentCompat() {

    companion object {
        private val PREFERENCE_RESOURCE_ID = "preferenceResource"
        private val PREFERENCE_ROOT = "preferenceRoot"
        private val TAG = "SettingActivityFragment"
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: Preference
    ): Boolean {
        return false
    }

    override fun onPreferenceStartScreen(
        caller: PreferenceFragmentCompat?,
        pref: PreferenceScreen
    ): Boolean {
        startPreferenceFragment(buildPreference(R.xml.settings, pref.key))
        return true
    }

    override fun onPreferenceStartInitialScreen() {
        startPreferenceFragment(buildPreference(R.xml.settings, null))
    }

    private fun buildPreference(resourceId: Int, root: String?): PreferenceFragmentCompat {
        val fragment = DemoFragment()
        val args = Bundle()
        args.putInt(PREFERENCE_RESOURCE_ID, resourceId)
        args.putString(PREFERENCE_ROOT, root)
        fragment.arguments = args
        return fragment
    }

    class DemoFragment : LeanbackPreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            val root = arguments?.getString(PREFERENCE_ROOT)
            val resourceId = arguments?.getInt(PREFERENCE_RESOURCE_ID)
            Log.i(TAG, "$root, $resourceId")

            if(root != null) {
                setPreferencesFromResource(resourceId!!, root)
            } else {
                addPreferencesFromResource(resourceId!!)
            }
        }

        override fun onPreferenceTreeClick(preference: Preference?): Boolean {
            if(preference?.key.equals("dialogFragment")) {
                startActivity(Intent(requireContext(), GuidedStepBaseActivity::class.java))
                activity?.finish()
            }

            return super.onPreferenceTreeClick(preference)
        }

    }
}