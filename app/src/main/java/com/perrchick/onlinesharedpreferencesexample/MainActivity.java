package com.perrchick.onlinesharedpreferencesexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.exceptions.BackendlessException;
import com.perrchick.onlinesharedpreferences.OnlineSharedPreferences;
import com.perrchick.onlinesharedpreferences.SyncedSharedPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Always synchronized with Firebase server
        SyncedSharedPreferences.getSyncedSharedPreferences(this, new SyncedSharedPreferences.SyncedSharedPreferencesListener() {
            @Override
            public void onSyncedSharedPreferencesChanged(SyncedSharedPreferencesChangeType changeType, String key, String value) {
                Toast.makeText(getApplicationContext(), "Firebase key value changed (" + changeType + "): <" + key + "," + value + ">", Toast.LENGTH_LONG).show();
            }
        });
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

        SyncedSharedPreferences.getSyncedSharedPreferences(this).remove("some key");
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
            public void done(String s, BackendlessException e) {
                if (e == null) {
                    ((TextView)findViewById(R.id.txtTitle)).setText(s != null ? s : "");
                } else {
                    e.printStackTrace();
                }
            }
        });

        SyncedSharedPreferences.getSyncedSharedPreferences(this).putString("some key", "yo");
    }
}
