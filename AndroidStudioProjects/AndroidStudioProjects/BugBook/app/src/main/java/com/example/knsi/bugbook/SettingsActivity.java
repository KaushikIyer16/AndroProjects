package com.example.knsi.bugbook;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_settings);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //display the fragment as the main content
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsClass())
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class SettingsClass extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences);
        }
        //  SharedPreferences.OnSharedPreferenceChangeListener mListener;

        @Override
        public void onSharedPreferenceChanged(SharedPreferences Sharedpreferences,String key) {

            if (key.equals(getString(R.string.PROJECT_NAME))) {
                Preference pref = findPreference(key);
                pref.setSummary(Sharedpreferences.getString(key, ""));
                Sharedpreferences.registerOnSharedPreferenceChangeListener(this);
            }

            else if (key.equals(getString(R.string.TESTER_ID))) {
                Preference pref = findPreference(key);
                pref.setSummary(Sharedpreferences.getString(key, ""));
                Sharedpreferences.registerOnSharedPreferenceChangeListener(this);
            }

        }


    }
}
