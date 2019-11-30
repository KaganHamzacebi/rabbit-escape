package rabbitescape.ui.android.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;


import net.artificialworlds.rabbitescape.R;

import rabbitescape.engine.config.Config;
import rabbitescape.engine.config.ConfigTools;
import rabbitescape.ui.android.AndroidConfigSetup;

import static android.content.Context.MODE_PRIVATE;

public class MySettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        findPreference("share").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, MySettingsFragment.this.getActivity().getString(R.string.share_app_context));
                sendIntent.setType("text/plain");
                MySettingsFragment.this.getActivity().startActivity(Intent.createChooser(sendIntent, MySettingsFragment.this.getActivity().getString(R.string.share_app)));
                // do something
                return true;
            }
        });

        findPreference("feedback").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String uriText =
                        "mailto:rabbitescape@artificialworlds.net" +
                                "?subject=" + MySettingsFragment.this.getActivity().getString(R.string.feedback_subject) + " " + MySettingsFragment.this.getActivity().getString(R.string.app_name);
                Uri uri = Uri.parse(uriText);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                MySettingsFragment.this.getActivity().startActivity(Intent.createChooser(sendIntent, MySettingsFragment.this.getActivity().getString(R.string.send_feedback)));
                // do something
                return true;
            }
        });

        findPreference("privacyPolicy").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String url = "http://artificialworlds.net/rabbit-escape/privacy.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                MySettingsFragment.this.startActivity(i);
                return true;
            }
        });

        // gallery EditText change listener
        bindPreferenceSummaryToValue(findPreference(getString(R.string.musicSound)));
    }

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference
                        .setSummary(index >= 0 ? listPreference.getEntries()[index]
                                : null);

            }  else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private  void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference
                .setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        Config config = AndroidConfigSetup.createConfig(
                getActivity().getSharedPreferences( "rabbitescape", MODE_PRIVATE )  );
        boolean muted = ConfigTools.getBool( config, AndroidConfigSetup.CFG_MUTED );
        sBindPreferenceSummaryToValueListener.onPreferenceChange(
                preference,
                !muted);
    }
}