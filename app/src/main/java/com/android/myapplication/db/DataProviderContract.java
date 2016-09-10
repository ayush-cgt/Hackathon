package com.android.myapplication.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Manish
 */
public class DataProviderContract {

    private DataProviderContract() {
    }

    // The URI scheme used for content URIs
    public static final String SCHEME = "content";

    // The provider's authority
    public static final String AUTHORITY = "com.cgt";

    // The DataProvider content URI
    public static final Uri DATABASE_URI = Uri.parse(SCHEME + "://" + AUTHORITY);

    // The content provider database name
    public static final String DATABASE_NAME = "cgtaccounts";

    // The starting version of the database
    public static final int DATABASE_VERSION = 1;

    /**
     * The MIME type for a content URI that would return multiple rows
     * <P>Type: TEXT</P>
     */
    public static final String MIME_TYPE_ROWS = "vnd.android.cursor.dir/vnd.com.liveeasy";

    public static final class User implements BaseColumns {
        // User table name
        public static final String TABLE_NAME = "user";

        // User table content URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DATABASE_URI, TABLE_NAME);

        // User table column names
        public static final String USER_NAME = "username";
        public static final String PASSWORD = "password";
        public static final String NAME = "name";
        public static final String USER_PICTURE = "photo";

        // create Users table
        public static final String CREATE_TABLE =
                "CREATE TABLE User (_id INTEGER NOT NULL, username TEXT NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, photo TEXT NOT NULL);";
    }



}
