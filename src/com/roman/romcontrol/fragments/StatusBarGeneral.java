
package com.roman.romcontrol.fragments;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.util.Log;

import com.roman.romcontrol.R;
import com.roman.romcontrol.SettingsPreferenceFragment;

public class StatusBarGeneral extends SettingsPreferenceFragment {

    private static final String PREF_SETTINGS_BUTTON_BEHAVIOR = "settings_behavior";
    private static final String PREF_AUTO_HIDE_TOGGLES = "auto_hide_toggles";
    private static final String PREF_BRIGHTNESS_TOGGLE = "status_bar_brightness_toggle";

    CheckBoxPreference mDefaultSettingsButtonBehavior;
    CheckBoxPreference mAutoHideToggles;
    CheckBoxPreference mStatusBarBrightnessToggle;

    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity().getApplicationContext();

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.prefs_statusbar_general);

        mDefaultSettingsButtonBehavior = (CheckBoxPreference) findPreference(PREF_SETTINGS_BUTTON_BEHAVIOR);
        mDefaultSettingsButtonBehavior.setChecked(Settings.System.getInt(mContext
                .getContentResolver(), Settings.System.STATUSBAR_SETTINGS_BEHAVIOR,
                0) == 1);

        mAutoHideToggles = (CheckBoxPreference) findPreference(PREF_AUTO_HIDE_TOGGLES);
        mAutoHideToggles.setChecked(Settings.System.getInt(mContext
                .getContentResolver(), Settings.System.STATUSBAR_QUICKTOGGLES_AUTOHIDE,
                0) == 1);

        mStatusBarBrightnessToggle = (CheckBoxPreference) findPreference(PREF_BRIGHTNESS_TOGGLE);
        mStatusBarBrightnessToggle.setChecked(Settings.System.getInt(mContext
                .getContentResolver(), Settings.System.STATUS_BAR_BRIGHTNESS_TOGGLE,
                0) == 1);

        if (mTablet) {
            PreferenceScreen prefs = getPreferenceScreen();
            prefs.removePreference(mStatusBarBrightnessToggle);
            prefs.removePreference(mAutoHideToggles);
            prefs.removePreference(mDefaultSettingsButtonBehavior);
        }

    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
            Preference preference) {
        if (preference == mDefaultSettingsButtonBehavior) {

            Log.e("LOL", "b");
            Settings.System.putInt(mContext.getContentResolver(),
                    Settings.System.STATUSBAR_SETTINGS_BEHAVIOR,
                    ((CheckBoxPreference) preference).isChecked() ? 1 : 0);
            return true;

        } else if (preference == mAutoHideToggles) {

            Log.e("LOL", "m");
            Settings.System.putInt(mContext.getContentResolver(),
                    Settings.System.STATUSBAR_QUICKTOGGLES_AUTOHIDE,
                    ((CheckBoxPreference) preference).isChecked() ? 1 : 0);
            return true;

        } else if (preference == mStatusBarBrightnessToggle) {

            Log.e("LOL", "m");
            Settings.System.putInt(mContext.getContentResolver(),
                    Settings.System.STATUS_BAR_BRIGHTNESS_TOGGLE,
                    ((CheckBoxPreference) preference).isChecked() ? 1 : 0);
            return true;

        }

        return super.onPreferenceTreeClick(preferenceScreen, preference);

    }

}
