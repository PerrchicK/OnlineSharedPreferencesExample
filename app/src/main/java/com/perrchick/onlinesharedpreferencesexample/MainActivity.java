package com.perrchick.onlinesharedpreferencesexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.ParseException;
import com.perrchick.onlinesharedpreferences.OnlineSharedPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();

        /* Demonstrated in fluent code:
         * (1) Get a new instance of OnlineSharedPreferences, pass the context to promise your own data storage
         * (2) Put a string (on version 1.0.0 it saves strings only, you can always export a pojo to a JSON string)
         * (3) Commit asynchronously
         */
        OnlineSharedPreferences.getOnlineSharedPreferences(this).putString("some key", "yo").commitInBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();

        /* Demonstrated in fluent code:
         * (1) Get a new instance of OnlineSharedPreferences, pass the context to promise your own data storage
         * (2) Get a string asynchronously
         * (3) Callback
         */
        OnlineSharedPreferences.getOnlineSharedPreferences(this).getString("some key", new OnlineSharedPreferences.GetStringCallback() {
            @Override
            public void done(String s, ParseException e) {
                if (e == null) {
                    ((TextView)findViewById(R.id.txtTitle)).setText(s != null ? s : "");
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
}
