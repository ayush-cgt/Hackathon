package com.android.myapplication.db;

import android.content.ContentValues;
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

    public void addExpense(ContentValues values) {
        context.getContentResolver().insert(DataProviderContract.Expense.CONTENT_URI, values);
    }

    public void insertClient(String name, String company, String country,
                             String city, String zip, String address, String phone, String email) {
        ContentValues values = new ContentValues();
        values.put(DataProviderContract.Client.NAME, name);
        values.put(DataProviderContract.Client.COMPANY, company);
        values.put(DataProviderContract.Client.COUNTRY, country);
        values.put(DataProviderContract.Client.CITY, city);
        values.put(DataProviderContract.Client.ZIP, zip);
        values.put(DataProviderContract.Client.ADDRESS, address);
        values.put(DataProviderContract.Client.PHONE, phone);
        values.put(DataProviderContract.Client.EMAIL, email);

        context.getContentResolver().insert(DataProviderContract.Client.CONTENT_URI, values);
        values.clear();
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
    public ArrayList<Type> getSubTypes(String id) {
        ArrayList<Type> type = new ArrayList<Type>();

        android.database.Cursor cursor = context.getContentResolver().query(DataProviderContract.IncomeSubType.CONTENT_URI, DataProviderContract.IncomeSubType.PROJECTION,
                DataProviderContract.IncomeSubType.MASTER_ID + "= ?", new String[]{id}, null);

        if (cursor.getCount() > 0) {
            int indexPID = cursor.getColumnIndex(DataProviderContract.IncomeSubType._ID);
            int indexType = cursor.getColumnIndex(DataProviderContract.IncomeSubType.NAME);

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
