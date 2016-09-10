package com.android.myapplication;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.myapplication.db.DataProviderContract;

import java.util.ArrayList;

/**
 * Created by birbal on 10/9/16.
 */
public class SplashActivity extends AppCompatActivity {

    SharedPreferences prefs;
    String mDataFilledPref = "isDataFilled";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean(mDataFilledPref, false)) {
            addMasterData();
            prefs.edit().putBoolean(mDataFilledPref, true).commit();
        }

        splashThread();
    }

    void splashThread() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 2000);
    }

    void addMasterData() {

        addToDatabase(DataProviderContract.IncomeMaster.CONTENT_URI, DataProviderContract.IncomeMaster.TYPE, R.array.IncomeMasterArray);
        addToDatabase(DataProviderContract.ExpenseMaster.CONTENT_URI, DataProviderContract.ExpenseMaster.TYPE, R.array.ExpenseMasterArray);

    }

    void addToDatabase(Uri uri, String data, int resourceId) {
        ContentValues values = new ContentValues();
        String[] dataArray = getResources().getStringArray(resourceId);


        for (int i = 0; i < dataArray.length; i++) {
            try {
                values.put(data, dataArray[i]);
                Uri insertedUri = getContentResolver().insert(uri, values);
                if (insertedUri != null) {
                    long masterId = ContentUris.parseId(insertedUri);

                    if (uri == DataProviderContract.IncomeMaster.CONTENT_URI) {
                        addToDatabase(DataProviderContract.IncomeSubType.CONTENT_URI, DataProviderContract.IncomeSubType.MASTER_ID, masterId, DataProviderContract.IncomeSubType.NAME, R.array.IncomeSubTypeArray);
                    } else if (uri == DataProviderContract.ExpenseMaster.CONTENT_URI) {
                        addToDatabase(DataProviderContract.ExpenseSubType.CONTENT_URI, DataProviderContract.ExpenseSubType.MASTER_ID, masterId, DataProviderContract.ExpenseSubType.NAME, i);
                    }
                }
                values.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    int[ ] expSubTypes = { R.array.oneSubTypeArray, R.array.twoSubTypeArray, R.array.threeSubTypeArray, R.array.fourSubTypeArray};

    void addToDatabase(Uri uri, String masterColumn, long masterId, String dataColumn, int position) {
        ContentValues values = new ContentValues();
        String[] dataArray = getResources().getStringArray(expSubTypes[position]);
        for (int i = 0; i < dataArray.length; i++) {
            try {
                values.put(dataColumn, dataArray[i]);
                values.put(masterColumn, masterId);


                getContentResolver().insert(uri, values);
                values.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
