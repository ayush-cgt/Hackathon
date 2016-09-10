package com.android.myapplication.db;

import android.content.Context;

/**
 * Created by Ayush
 */
public class DBHelper {

    private Context context = null;
    private static DBHelper instance = null;

    private DBHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }

        return instance;
    }


}
