package com.example.emp354.linear.MaccabiContentProvider;

import android.database.sqlite.SQLiteDatabase;


public class MaccabiContentUserModel {

    static final String COLUMN_ID = "ID";
    static final String COLUMN_MAIL_ID = "MailID";
    static final String COLUMN_FIRST_NAME = "FirstName";
    static final String COLUMN_LAST_NAME = "LastName";
    static final String COLUMN_PHONE_NO = "PhoneNo";
    static final String COLUMN_PASSWORD = "Password";
    static final String COLUMN_DOB = "DOB";
    static final String COLUMN_AGE = "AGE";


    static final  String TABLE_NAME="User";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_MAIL_ID + " TEXT , " + COLUMN_FIRST_NAME + " TEXT , " + COLUMN_LAST_NAME + " TEXT , " +
            COLUMN_PHONE_NO + " TEXT , " + COLUMN_PASSWORD + " TEXT  ," + COLUMN_DOB + " TEXT ," + COLUMN_AGE + " TEXT " + ")";



    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}
