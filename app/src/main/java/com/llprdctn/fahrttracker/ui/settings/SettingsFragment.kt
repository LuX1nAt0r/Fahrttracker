package com.llprdctn.fahrttracker.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.other.Constants.SHARED_PREFERENCES

class SettingsFragment : PreferenceFragmentCompat() {

    private val sharedPreferences = activity?.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
    private val fuelUsagePreference: EditTextPreference? = findPreference("carFuel")

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)






    }

    override fun onResume() {
        super.onResume()
        val name = sharedPreferences?.getString("carFuel", "4,0")
        fuelUsagePreference?.text = name

    }
}