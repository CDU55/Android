package com.example.laborator2;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class Fragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }
}
