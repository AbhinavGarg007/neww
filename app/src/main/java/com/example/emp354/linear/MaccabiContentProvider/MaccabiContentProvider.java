package com.example.emp354.linear.MaccabiContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.emp354.linear.ContentProviderExample.StudentsProvider;

import java.util.HashMap;


public class MaccabiContentProvider extends ContentProvider {

    static final String PROVIDER_NAME="com.example.emp354.linear.MaccabiContentProvider";
    static final String URL="content://"+PROVIDER_NAME+"/user";
    static final Uri CONTENT_URI=Uri.parse(URL);
    MaccabiContentDatabase maccabiContentDatabase;
    SQLiteDatabase db;


    private static HashMap<String,String> USER_PROJECTION_MAP;
    static final int USER=1;
    static final int USER_ID=2;

    static final UriMatcher uriMatcher;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"user",USER);
        uriMatcher.addURI(PROVIDER_NAME,"user/#",USER_ID);
    }



    @Override
    public boolean onCreate() {
        Context context=getContext();
        maccabiContentDatabase=new MaccabiContentDatabase(context);
        db=maccabiContentDatabase.getWritableDatabase();

        return (db==null)?false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqb=new SQLiteQueryBuilder();
        sqb.setTables(MaccabiContentUserModel.TABLE_NAME);
        switch (uriMatcher.match(uri))
        {
            case USER:
                sqb.setProjectionMap(USER_PROJECTION_MAP);
                break;

            case USER_ID:
                sqb.appendWhere(MaccabiContentUserModel.COLUMN_ID+"="+uri.getPathSegments().get(1));
                break;

            default:
        }
        if(sortOrder==null || sortOrder=="")
        {
            sortOrder=MaccabiContentUserModel.COLUMN_FIRST_NAME;
        }
        Cursor c=sqb.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USER:
                return "vnd.android.cursor.dir/vnd.example.user";

            case USER_ID:
                return "vnd.android.cursor.item/vnd.example.user";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);

        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowId=db.insert(MaccabiContentUserModel.TABLE_NAME,"",values);
        if(rowId>0)
        {
            Uri _uri= ContentUris.withAppendedId(CONTENT_URI,rowId);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }
        throw new SQLException("Failed to add record into "+uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public boolean isMailIdExists(String mailId)
    {
        String[] projection={MaccabiContentUserModel.COLUMN_MAIL_ID};
        Cursor cursor=getContext().getContentResolver().query(MaccabiContentProvider.CONTENT_URI,
                projection,
                MaccabiContentUserModel.COLUMN_MAIL_ID,
                new String[] {mailId},
                null);
        int count=cursor.getCount();
        cursor.close();
        if (count <= 0) {
            return false;
        } else {
            return true;
        }

    }
}
