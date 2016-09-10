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
    public static final String MIME_TYPE_ROWS = "vnd.android.cursor.dir/vnd.com.cgt";

    public static final class User implements BaseColumns {
        // User table name
        public static final String TABLE_NAME = "user";

        // User table content URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DATABASE_URI, TABLE_NAME);

        // User table column names
        public static final String USER_NAME = "username";
        public static final String PASSWORD = "password";
        public static final String NAME = "name";
        public static final String PHOTO = "photo";
        public static final String EMAIL = "email";

        public static final String[] PROJECTION =
                {
                        _ID,
                        USER_NAME,
                        PASSWORD,
                        NAME,
                        PHOTO,
                        EMAIL
                };

        // create Users table
        public static final String CREATE_TABLE =
                "CREATE TABLE user (_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, photo TEXT NOT NULL, email TEXT NOT NULL);";
    }

    public static final class Client implements BaseColumns {
        // Client table name
        public static final String TABLE_NAME = "client";

        // Client table content URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DATABASE_URI, TABLE_NAME);

        // Client table column names
        public static final String NAME = "name";
        public static final String COMPANY = "company";
        public static final String COUNTRY = "country";
        public static final String CITY = "city";
        public static final String ZIP = "zip";
        public static final String ADDRESS = "address";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";

        public static final String[] PROJECTION =
                {
                        _ID,
                        NAME,
                        COMPANY,
                        COUNTRY,
                        CITY,
                        ZIP,
                        ADDRESS,
                        PHONE,
                        EMAIL
                };


        // create Client table
        public static final String CREATE_TABLE =
                "CREATE TABLE client (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, company TEXT NOT NULL, country TEXT NOT NULL, city TEXT NOT NULL, zip TEXT NOT NULL, address TEXT NOT NULL, phone TEXT NOT NULL, email TEXT NOT NULL);";
    }

    public static final class IncomeMaster implements BaseColumns {
        // IncomeMaster table name
        public static final String TABLE_NAME = "income_master";

        // IncomeMaster table content URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DATABASE_URI, TABLE_NAME);

        // IncomeMaster table column names
        public static final String TYPE = "type";

        public static final String[] PROJECTION =
                {
                        _ID,
                        TYPE,
                };


        // create IncomeMaster table
        public static final String CREATE_TABLE =
                "CREATE TABLE income_master (_id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT NOT NULL);";
    }

    public static final class IncomeSubType implements BaseColumns {
        // IncomeSubType table name
        public static final String TABLE_NAME = "income_subtype";

        // IncomeSubType table content URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DATABASE_URI, TABLE_NAME);

        // IncomeSubType table column names
        public static final String NAME = "name";
        public static final String MASTER_ID = "master_id";

        public static final String[] PROJECTION =
                {
                        _ID,
                        NAME,
                        MASTER_ID
                };


        // create IncomeSubType table
        public static final String CREATE_TABLE =
                "CREATE TABLE income_subtype (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, master_id INTEGER NOT NULL);";
    }

    public static final class Income implements BaseColumns {
        // Income table name
        public static final String TABLE_NAME = "income";

        // Income table content URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DATABASE_URI, TABLE_NAME);

        // Income table column names
        public static final String CLIENT_ID = "client_id";
        public static final String SUBTYPE_ID = "subtype_id";
        public static final String PROJECT = "project";
        public static final String AMOUNT_FOREIGN = "amount_foreign";
        public static final String AMOUNT_INR = "amount_inr";
        public static final String EXCHANGE_RATE = "exchange_rate";
        public static final String PAYMENT_MODE = "payment_mode";
        public static final String FINANCIAL_YEAR = "financial_year";
        public static final String MONTH = "month";

        public static final String[] PROJECTION =
                {
                        _ID,
                        CLIENT_ID,
                        SUBTYPE_ID,
                        PROJECT,
                        AMOUNT_FOREIGN,
                        AMOUNT_INR,
                        EXCHANGE_RATE,
                        PAYMENT_MODE,
                        FINANCIAL_YEAR,
                        MONTH
                };


        // create Income table
        public static final String CREATE_TABLE =
                "CREATE TABLE income (_id INTEGER PRIMARY KEY AUTOINCREMENT, client_id INTEGER NOT NULL, subtype_id INTEGER NOT NULL, project TEXT NOT NULL, amount_foreign REAL NOT NULL, amount_inr REAL NOT NULL, exchange_rate REAL NOT NULL, payment_mode INTEGER NOT NULL, financial_year INTEGER NOT NULL, month INTEGER NOT NULL);";
    }

    public static final class ExpenseMaster implements BaseColumns {
        // ExpenseMaster table name
        public static final String TABLE_NAME = "expense_master";

        // ExpenseMaster table content URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DATABASE_URI, TABLE_NAME);

        // ExpenseMaster table column names
        public static final String TYPE = "type";

        public static final String[] PROJECTION =
                {
                        _ID,
                        TYPE
                };


        // create ExpenseMaster table
        public static final String CREATE_TABLE =
                "CREATE TABLE expense_master (_id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT NOT NULL);";
    }

    public static final class ExpenseSubType implements BaseColumns {
        // ExpenseSubType table name
        public static final String TABLE_NAME = "expense_subtype";

        // ExpenseSubType table content URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DATABASE_URI, TABLE_NAME);

        // ExpenseSubType table column names
        public static final String NAME = "name";
        public static final String MASTER_ID = "master_id";

        public static final String[] PROJECTION =
                {
                        _ID,
                        NAME,
                        MASTER_ID
                };


        // create ExpenseSubType table
        public static final String CREATE_TABLE =
                "CREATE TABLE expense_subtype (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, master_id INTEGER NOT NULL);";
    }

    public static final class Expense implements BaseColumns {
        // Expense table name
        public static final String TABLE_NAME = "expense";

        // Expense table content URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DATABASE_URI, TABLE_NAME);

        // Expense table column names
        public static final String SUBTYPE_ID = "subtype_id";
        public static final String AMOUNT = "amount";
        public static final String INVOICE_IMAGE_PATH = "invoice_image_path";
        public static final String PAYMENT_MODE = "payment_mode";
        public static final String TITLE = "title";
        public static final String FINANCIAL_YEAR = "financial_year";
        public static final String MONTH = "month";
        public static final String INCOME_ID = "income_id";


        public static final String[] PROJECTION =
                {
                        _ID,
                        SUBTYPE_ID,
                        AMOUNT,
                        INVOICE_IMAGE_PATH,
                        PAYMENT_MODE,
                        TITLE,
                        FINANCIAL_YEAR,
                        MONTH,
                        INCOME_ID
                };


        // create Expense table
        public static final String CREATE_TABLE =
                "CREATE TABLE expense (_id INTEGER PRIMARY KEY AUTOINCREMENT, subtype_id INTEGER NOT NULL, amount TEXT NOT NULL, invoice_image_path TEXT NOT NULL, payment_mode INTEGER NOT NULL, title TEXT NOT NULL, financial_year INTEGER NOT NULL, month INTEGER NOT NULL, income_id INTEGER NOT NULL);";
    }



}
