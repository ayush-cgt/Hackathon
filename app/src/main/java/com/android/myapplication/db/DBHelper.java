package com.android.myapplication.db;

import android.content.Context;

import com.android.myapplication.data.Type;

import java.util.ArrayList;

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


    public ArrayList<Type> getTypes() {
        ArrayList<Type> type = new ArrayList<Type>();

        android.database.Cursor cursor = context.getContentResolver().query(DataProviderContract.IncomeMaster.CONTENT_URI, DataProviderContract.IncomeMaster.PROJECTION,
                null, null, null);
        if (cursor.getCount() > 0) {
            int indexPID = cursor.getColumnIndex(DataProviderContract.IncomeMaster._ID);
            int indexType = cursor.getColumnIndex(DataProviderContract.IncomeMaster.TYPE);

            while (cursor.moveToNext()) {
                Type t = new Type();
                t.setId(cursor.getString(indexPID));
                t.setType(cursor.getString(indexType));
                type.add(t);
            }
        }
        cursor.close();
        return type;
    }


}
