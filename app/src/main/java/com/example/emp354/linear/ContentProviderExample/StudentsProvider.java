package com.example.emp354.linear.ContentProviderExample;

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
import android.text.TextUtils;

import java.util.HashMap;

public class StudentsProvider extends ContentProvider{

    static final String PROVIDER_NAME="com.example.emp354.linear.ContentProviderExample";
    static final String URL="content://"+PROVIDER_NAME+"/students";
    static final Uri CONTENT_URI=Uri.parse(URL);

    static final String _ID="_id";
    static final String NAME="name";
    static final String GRADE="grade";

    private static HashMap<String,String> STUDENTS_PROJECTION_MAP;
    static final int STUDENTS=1;
    static final int STUDENT_ID=2;

    static final UriMatcher uriMatcher;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"students",STUDENTS);
        uriMatcher.addURI(PROVIDER_NAME,"students/#",STUDENT_ID);
    }


    /**
     * Database specification
     * @return
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME="College";
    static final  String TABLE_NAME="students";
    static final int DATABASE_VERSION=1;
    static final String CREATE_DB_TABLE=" CREATE TABLE " +TABLE_NAME+
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " name TEXT NOT NULL, "+
            " grade TEXT NOT NULL);";

    //Helper class that create and manage provider's  data

    private static class ContentHelper extends SQLiteOpenHelper
    {
        public ContentHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }


    }


    @Override
    public boolean onCreate() {
        Context context=getContext();
        ContentHelper contentHelper=new ContentHelper(context);
        db=contentHelper.getWritableDatabase();

        return (db==null)?false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqb=new SQLiteQueryBuilder();
        sqb.setTables(TABLE_NAME);
        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                sqb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;

            case STUDENT_ID:
                sqb.appendWhere(_ID+"="+uri.getPathSegments().get(1));
                break;

            default:
        }
        if(sortOrder==null || sortOrder=="")
        {
            sortOrder=NAME;
        }
        Cursor c=sqb.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.example.students";

            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.example.students";

            default:
                throw new IllegalArgumentException("Unsupported URI: "+uri);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        long rowId=db.insert(TABLE_NAME,"",values);
        if(rowId>0)
        {
            Uri _uri= ContentUris.withAppendedId(CONTENT_URI,rowId);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }
        throw new SQLException("Failed to add record into "+uri);
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {

        switch (uriMatcher.match(uri))
        {
            case STUDENTS:

                /*for(ContentValues value : values)
                {

                    long rowId=db.insert(TABLE_NAME,"",value);
                    if(rowId>0)
                    {
                        Uri _uri=ContentUris.withAppendedId(CONTENT_URI,rowId);
                        getContext().getContentResolver().notifyChange(_uri,null);
                        return values.length;
                    }
                }*/
                for(int i=0;i<values.length;i++)
                {
                   ContentValues value=values[i];
                    long rowId=db.insert(TABLE_NAME,"",value);
                    if(rowId>0)
                    {
                        Uri _uri=ContentUris.withAppendedId(CONTENT_URI,rowId);
                        getContext().getContentResolver().notifyChange(_uri,null);

                    }
                }
                break;
        }
        return values.length;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count=0;
        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                count=db.delete(TABLE_NAME,selection,selectionArgs);
                break;

            case STUDENT_ID:
                String id=uri.getPathSegments().get(1);
                count=db.delete(TABLE_NAME,_ID+" = "+ id+
                        (!TextUtils.isEmpty(selection)?" AND (" + selection + ')' : ""),selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI "+uri);

        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count=0;
        switch (uriMatcher.match(uri))
        {
            case STUDENTS:
                count=db.update(TABLE_NAME,values,selection,selectionArgs);
                break;


            case STUDENT_ID:
                count=db.update(TABLE_NAME,values,_ID+" = "+uri.getPathSegments().get(1)+
                        (!TextUtils.isEmpty(selection)?" AND("+selection+')' : ""),selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;

    }
}
