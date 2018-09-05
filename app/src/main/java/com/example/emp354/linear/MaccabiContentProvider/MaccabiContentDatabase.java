package com.example.emp354.linear.MaccabiContentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaccabiContentDatabase extends SQLiteOpenHelper {

    static final String DATABASE_NAME="MaccabiContent";

    static final int DATABASE_VERSION=1;


    public MaccabiContentDatabase(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        MaccabiContentUserModel.onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        MaccabiContentUserModel.onUpgrade(db,oldVersion,newVersion);

    }
}
