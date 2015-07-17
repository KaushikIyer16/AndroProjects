package com.example.knsi.testactivities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListActivity extends ActionBarActivity {

    private ListView lv;
    ArrayAdapter<String> mListAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String[] data={"python not getting installed","liclipse is how to make py interesting",
                "this sig is run by specas and ali baba","rahul knows only how to use google",
                "specas gets confused what he's programming","check check","1...2...3..","im damn close to cracking the app"};
        List<String> viewList=new ArrayList<>(Arrays.asList(data));
        mListAdapter=new ArrayAdapter<>(this, R.layout.list_text, R.id.list_item_textView, viewList);
        lv=(ListView)findViewById(R.id.List_button);
        lv.setAdapter(mListAdapter);

        fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(lv, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
                fab.show();
            }

            @Override
            public void onScrollUp() {
                fab.hide();
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListActivity.this,"button is working",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
}
