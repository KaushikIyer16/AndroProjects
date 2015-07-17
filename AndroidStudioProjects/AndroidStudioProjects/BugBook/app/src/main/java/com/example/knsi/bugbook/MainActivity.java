package com.example.knsi.bugbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.knsi.bugbook.data.BugContractClass;


public class MainActivity extends ActionBarActivity {

    public TextView TesterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        TesterId=(TextView)findViewById(R.id.TesterIdValue);
        UsePreferences();

    }

    public void UsePreferences()
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String Id=sharedPref.getString(getString(R.string.TESTER_ID),getString(R.string.TESTER_ID_VALUE));


        TesterId.setText(Id,TextView.BufferType.EDITABLE);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
    //    getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //noinspection SimplifiableIfStatement
      //  if (id == R.id.action_settings) {
        //    return true;
       // }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onResume() {
        UsePreferences();
        super.onResume();
    }

    public void PostBugs(View v)
    {
        Intent intent=new Intent(MainActivity.this,PostBugsActivity.class);
        startActivity(intent);
    }




}
