package com.android.myapplication.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class MyContentProvider extends ContentProvider {

    // Database specific constant declarations
    private SQLiteDatabase db;
    public static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DataProviderContract.AUTHORITY, DataProviderContract.User.TABLE_NAME, Constants.USER);
        uriMatcher.addURI(DataProviderContract.AUTHORITY, DataProviderContract.Client.TABLE_NAME, Constants.CLIENT);
        uriMatcher.addURI(DataProviderContract.AUTHORITY, DataProviderContract.IncomeMaster.TABLE_NAME, Constants.INCOME_MASTER);
        uriMatcher.addURI(DataProviderContract.AUTHORITY, DataProviderContract.IncomeSubType.TABLE_NAME, Constants.INCOME_SUBTYPE);
        uriMatcher.addURI(DataProviderContract.AUTHORITY, DataProviderContract.Income.TABLE_NAME, Constants.INCOME);
        uriMatcher.addURI(DataProviderContract.AUTHORITY, DataProviderContract.ExpenseMaster.TABLE_NAME, Constants.EXPENSE_MASTER);
        uriMatcher.addURI(DataProviderContract.AUTHORITY, DataProviderContract.ExpenseSubType.TABLE_NAME, Constants.EXPENSE_SUBTYPE);
        uriMatcher.addURI(DataProviderContract.AUTHORITY, DataProviderContract.Expense.TABLE_NAME, Constants.EXPENSE);

    }

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        SQLiteDatabase.loadLibs(context);
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */
        db = dbHelper.getReadableDatabase("123456789");
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case Constants.USER:
                count = db.delete(DataProviderContract.User.TABLE_NAME, selection, selectionArgs);
                break;

        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long id;

        switch (uriMatcher.match(uri)) {
            case Constants.USER:
                // Inserts the row into the table and returns the new row's _id value
                id = db.insert(DataProviderContract.User.TABLE_NAME, "", values);

                // If the insert succeeded, notify a change and return the new row's content URI.
                if (-1 != id) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return Uri.withAppendedPath(uri, Long.toString(id));
                } else {
                    throw new SQLiteException("Insert error:" + uri);
                }

            case Constants.CLIENT:
                // Inserts the row into the table and returns the new row's _id value
                id = db.insert(DataProviderContract.Client.TABLE_NAME, "", values);

                // If the insert succeeded, notify a change and return the new row's content URI.
                if (-1 != id) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return Uri.withAppendedPath(uri, Long.toString(id));
                } else {
                    throw new SQLiteException("Insert error:" + uri);
                }

            case Constants.INCOME_MASTER:
                // Inserts the row into the table and returns the new row's _id value
                id = db.insert(DataProviderContract.IncomeMaster.TABLE_NAME, "", values);

                // If the insert succeeded, notify a change and return the new row's content URI.
                if (-1 != id) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return Uri.withAppendedPath(uri, Long.toString(id));
                } else {
                    throw new SQLiteException("Insert error:" + uri);
                }

            case Constants.INCOME_SUBTYPE:
                // Inserts the row into the table and returns the new row's _id value
                id = db.insert(DataProviderContract.IncomeSubType.TABLE_NAME, "", values);

                // If the insert succeeded, notify a change and return the new row's content URI.
                if (-1 != id) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return Uri.withAppendedPath(uri, Long.toString(id));
                } else {
                    throw new SQLiteException("Insert error:" + uri);
                }

            case Constants.INCOME:
                // Inserts the row into the table and returns the new row's _id value
                id = db.insert(DataProviderContract.Income.TABLE_NAME, "", values);

                // If the insert succeeded, notify a change and return the new row's content URI.
                if (-1 != id) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return Uri.withAppendedPath(uri, Long.toString(id));
                } else {
                    throw new SQLiteException("Insert error:" + uri);
                }

            case Constants.EXPENSE_MASTER:
                // Inserts the row into the table and returns the new row's _id value
                id = db.insert(DataProviderContract.ExpenseMaster.TABLE_NAME, "", values);

                // If the insert succeeded, notify a change and return the new row's content URI.
                if (-1 != id) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return Uri.withAppendedPath(uri, Long.toString(id));
                } else {
                    throw new SQLiteException("Insert error:" + uri);
                }

            case Constants.EXPENSE_SUBTYPE:
                // Inserts the row into the table and returns the new row's _id value
                id = db.insert(DataProviderContract.ExpenseSubType.TABLE_NAME, "", values);

                // If the insert succeeded, notify a change and return the new row's content URI.
                if (-1 != id) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return Uri.withAppendedPath(uri, Long.toString(id));
                } else {
                    throw new SQLiteException("Insert error:" + uri);
                }

            case Constants.EXPENSE:
                // Inserts the row into the table and returns the new row's _id value
                id = db.insert(DataProviderContract.Expense.TABLE_NAME, "", values);

                // If the insert succeeded, notify a change and return the new row's content URI.
                if (-1 != id) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return Uri.withAppendedPath(uri, Long.toString(id));
                } else {
                    throw new SQLiteException("Insert error:" + uri);
                }

            default:
                throw new IllegalArgumentException("Insert: Invalid URI" + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor returnCursor = null;
        switch (uriMatcher.match(uri)) {
            case Constants.USER:
                // Does the query against a read-only version of the database
                returnCursor = db.query(
                        DataProviderContract.User.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                // Sets the ContentResolver to watch this content URI for data changes
                returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
                return returnCursor;


            case Constants.CLIENT:
                // Does the query against a read-only version of the database
                returnCursor = db.query(
                        DataProviderContract.Client.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                // Sets the ContentResolver to watch this content URI for data changes
                returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
                return returnCursor;

            case Constants.INCOME_MASTER:
                // Does the query against a read-only version of the database
                returnCursor = db.query(
                        DataProviderContract.IncomeMaster.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                // Sets the ContentResolver to watch this content URI for data changes
                returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
                return returnCursor;

            case Constants.INCOME_SUBTYPE:
                // Does the query against a read-only version of the database
                returnCursor = db.query(
                        DataProviderContract.IncomeSubType.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                // Sets the ContentResolver to watch this content URI for data changes
                returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
                return returnCursor;

            case Constants.INCOME:
                // Does the query against a read-only version of the database
                returnCursor = db.query(
                        DataProviderContract.Income.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                // Sets the ContentResolver to watch this content URI for data changes
                returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
                return returnCursor;

            case Constants.EXPENSE_MASTER:
                // Does the query against a read-only version of the database
                returnCursor = db.query(
                        DataProviderContract.ExpenseMaster.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                // Sets the ContentResolver to watch this content URI for data changes
                returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
                return returnCursor;

            case Constants.EXPENSE_SUBTYPE:
                // Does the query against a read-only version of the database
                returnCursor = db.query(
                        DataProviderContract.ExpenseSubType.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                // Sets the ContentResolver to watch this content URI for data changes
                returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
                return returnCursor;

            case Constants.EXPENSE:
                // Does the query against a read-only version of the database
                returnCursor = db.query(
                        DataProviderContract.Expense.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                // Sets the ContentResolver to watch this content URI for data changes
                returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
                return returnCursor;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case Constants.USER:
                count = db.update(DataProviderContract.User.TABLE_NAME, values, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // If the update succeeded, notify a change and return the number of updated rows.
        if (0 != count) {
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        } else {
            throw new SQLiteException("Update error:" + uri);
        }
    }

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DataProviderContract.DATABASE_NAME, null, DataProviderContract.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DataProviderContract.User.CREATE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        }
    }
}
