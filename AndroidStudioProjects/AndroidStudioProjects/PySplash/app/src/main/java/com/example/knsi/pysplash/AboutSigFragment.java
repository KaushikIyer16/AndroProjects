package com.example.knsi.pysplash;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
//import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class AboutSigFragment extends ActionBarActivity {
    static DrawerLayout drLayout;
    static ListView listView;
    static String[] contextFiles;
    static ActionBarDrawerToggle drawerListener;
    static String prefs;

    static SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_sig_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
         sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        contextFiles=getResources().getStringArray(R.array.nav_draw_items);

        getSupportActionBar().setTitle(prefs);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_sig, menu);
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
        else if(drawerListener.onOptionsItemSelected(item)){
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {




        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);

            drawerListener.onConfigurationChanged(newConfig);
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {



            View rootView = inflater.inflate(R.layout.fragment_about_sig, container, false);

            drLayout=(DrawerLayout) rootView.findViewById(R.id.drawer_layout);
            listView=(ListView) rootView.findViewById(R.id.list_Slider_menu);
            listView.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,contextFiles));


            drawerListener=new ActionBarDrawerToggle(getActivity(),drLayout,R.drawable.ic_drawer,R.string.OpenDrawerContentDescRes,
                    R.string.CloseDrawerContentDescRes){

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    //Toast.makeText(getActivity(),"DRAWER OPENED",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                   // Toast.makeText(getActivity(),"DRAWER CLOSED",Toast.LENGTH_SHORT).show();
                }
            };

            drLayout.setDrawerListener(drawerListener);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setItem(position,contextFiles[position]);
                    listView.setItemChecked(position, true);
                    listView.setSelection(position);
                    //view.setSelected(true);

                }
            });



            return rootView;
        }
        

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            drawerListener.syncState();
        }

        public void setItem(int position,String title){

            listView.setItemChecked(position,true);
            Fragment obj=null;
            if(position==0) {
                obj = new SampleFragment();
                prefs=sharedPref.getString("heading",contextFiles[0]);

            }
            else if(position == 1)
            {
                obj= new SampleFragment2();
                prefs=sharedPref.getString("heading",contextFiles[1]);
            }


            android.support.v4.app.FragmentManager fm= getFragmentManager();
            fm.beginTransaction().replace(R.id.frame_container,obj).commit();


                    ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(prefs);

            }

        }
    }

