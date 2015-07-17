package com.example.knsi.bugbook;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knsi.bugbook.data.BugContentProvider;
import com.example.knsi.bugbook.data.BugContractClass;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PostBugsActivity extends ActionBarActivity {

    public EditText desc,func;
    public ProgressBar pb;
    public String pName,severity,Tester_Id;
    public Spinner sev;

    public static final String TAG_MESSAGE="message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_bugs);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        desc=(EditText)findViewById(R.id.descriptionText);
        func=(EditText)findViewById(R.id.functionalityText);
        sev=(Spinner)findViewById(R.id.severity_spinner);


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        // With the help of the SharedPreferences class i will take the value based on the key that is present in the settings activity
        pName = sharedPref.getString(getString(R.string.PROJECT_NAME), "no_name");
        Tester_Id=sharedPref.getString(getString(R.string.TESTER_ID),getString(R.string.TESTER_ID_VALUE));
        InsertUsingContentProviders();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.severity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sev.setAdapter(adapter);

        sev.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // sev.setSelection(position);
                // severity=sev.getItemAtPosition(position).toString();
                TextView text=(TextView)view;
                severity=text.getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Please Choose The Severity", Toast.LENGTH_LONG).show();
            }


        });

        pb=(ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_bugs, menu);
     //   getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i=new Intent(PostBugsActivity.this,SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void InsertUsingContentProviders(){

        ContentValues newValues=new ContentValues();
        newValues.put(BugContractClass.BugBookDb.PROJECT_NAME,pName);
        newValues.put(BugContractClass.BugBookDb.TESTER_ID,Tester_Id);

        ContentResolver cr= getContentResolver();
        Uri InsertDetails=cr.insert(BugContractClass.BugBookDb.CONTENT_URI,newValues);
    }









    public class postBug extends AsyncTask<String,Integer,Double>{


        public Double doInBackground(String... params){
            postData(params[0]);

            return null;
        }
        protected void onPostExecute(Double result){
            pb.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Bug Posted", Toast.LENGTH_LONG).show();
        }
        protected void onProgressUpdate(Integer... progress){
            pb.setProgress(progress[0]);
        }
        public void postData(String valueIWantToSend) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://bmsceieee.com/lab/BugBookScripts/post_bugs.php");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair(TAG_MESSAGE, valueIWantToSend));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }

    }





    public void sToasts(View v)
    {
        if(desc.getText().toString().length()<1) {
            // out of range
            Toast.makeText(PostBugsActivity.this, "Please Enter The Description Of The Bug", Toast.LENGTH_LONG).show();
        }
        else if("SEVERITY".equals(severity)){
            Toast.makeText(getApplicationContext(), "Please Choose An Appropriate Value", Toast.LENGTH_LONG).show();
        }
        else {
            String description=desc.getText().toString();
            String functionality=func.getText().toString();
            String message=description+"|"+functionality+"|"+severity+"|"+pName;
            pb.setVisibility(View.VISIBLE);
            new postBug().execute(message);
        }

    }

}
