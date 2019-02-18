package com.eajy.materialdesigndemo.activity

import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceManager
import android.preference.RingtonePreference
import android.support.v4.app.NavUtils
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import com.eajy.materialdesigndemo.R

class SettingsActivity : AppCompatPreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addPreferencesFromResource(R.xml.preferences_settings)

        bindPreferenceSummaryToValue(findPreference("example_text"))
        bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"))
        bindPreferenceSummaryToValue(findPreference("sync_frequency"))

        val pref = findPreference("clear_cache")
        pref.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            Toast.makeText(this@SettingsActivity, getString(R.string.pref_on_preference_click), Toast.LENGTH_SHORT)
                    .show()
            false
        }

    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            if (!super.onMenuItemSelected(featureId, item)) {
                NavUtils.navigateUpFromSameTask(this)
                //startActivity(new Intent(this, SettingsActivity.class));
                //OR USING finish();
            }
            return true
        }
        return super.onMenuItemSelected(featureId, item)
    }

    companion object {

        /**
         * A preference value change listener that updates the preference's summary to reflect its new value.
         */
        private val sBindPreferenceSummaryToValueListener = Preference.OnPreferenceChangeListener { preference, value ->
            val stringValue = value.toString()

            when (preference) {
                is ListPreference -> {
                    // For list preferences, look up the correct display value in the preference's 'entries' list.
                    val index = preference.findIndexOfValue(stringValue)

                    // Set the summary to reflect the new value.
                    preference.setSummary(if (index >= 0) preference.entries[index] else null)

                }
                is RingtonePreference -> // For ringtone preferences, look up the correct display value using RingtoneManager.
                    if (TextUtils.isEmpty(stringValue)) {
                        // Empty values correspond to 'silent' (no ringtone).
                        preference.setSummary(R.string.pref_ringtone_silent)

                    } else {
                        val ringtone = RingtoneManager.getRingtone(preference.getContext(), Uri.parse(stringValue))

                        if (ringtone == null) {
                            // Clear the summary if there was a lookup error.
                            preference.setSummary(null)
                        } else {
                            // Set the summary to reflect the new ringtone display name.
                            val name = ringtone.getTitle(preference.getContext())
                            preference.setSummary(name)
                        }
                    }
                else -> // For all other preferences, set the summary to the value's simple string representation.
                    preference.summary = stringValue
            }
            true
        }


        /**
         * Binds a preference's summary to its value. More specifically, when the preference's value is changed,
         * its summary (line of text below the preference title) is updated to reflect the value. The summary is also
         * immediately updated upon calling this method. The exact display format is dependent on the type of preference.
         */
        private fun bindPreferenceSummaryToValue(preference: Preference) {
            // Set the listener to watch for value changes.
            preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

            // Trigger the listener immediately with the preference's current value.
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    PreferenceManager.getDefaultSharedPreferences(preference.context).getString(preference.key, ""))
        }
    }

}
