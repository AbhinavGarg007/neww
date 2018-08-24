package com.example.emp354.linear.DatabaseAssignmentMaccabi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MaccabiDataBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MaccabiRecord";
    String existLoginPassword;
    String[] data;

    public MaccabiDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MaccabiUserModel.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + MaccabiUserModel.TABLE_NAME);

        //create table again
        onCreate(db);

    }

    //for inserting user in db
    public long insertUser(String emailId, String firstName, String lastName, int phoneNo, String password) {
        this.db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MaccabiUserModel.COLUMN_MAIL_ID, emailId);
        values.put(MaccabiUserModel.COLUMN_FIRST_NAME, firstName);
        values.put(MaccabiUserModel.COLUMN_LAST_NAME, lastName);
        values.put(MaccabiUserModel.COLUMN_PHONE_NO, phoneNo);
        values.put(MaccabiUserModel.COLUMN_PASSWORD, password);

        long id = db.insert(MaccabiUserModel.TABLE_NAME, null, values);
        db.close();
        return id;
    }


    //to get user
    /*public MaccabiUserModel getUser(long id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(MaccabiUserModel.TABLE_NAME,
                new String[]{MaccabiUserModel.COLUMN_ID,MaccabiUserModel.COLUMN_MAIL_ID,MaccabiUserModel.COLUMN_FIRST_NAME,
                MaccabiUserModel.COLUMN_LAST_NAME,MaccabiUserModel.COLUMN_PHONE_NO,MaccabiUserModel.COLUMN_PASSWORD},
                MaccabiUserModel.COLUMN_ID,new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        MaccabiUserModel maccabiUserModel=new MaccabiUserModel(
                cursor.getInt(cursor.getColumnIndex(MaccabiUserModel.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_MAIL_ID)),
                cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_FIRST_NAME)),
                cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_LAST_NAME)),
                cursor.getInt(cursor.getColumnIndex(MaccabiUserModel.COLUMN_PHONE_NO)),
                cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_PASSWORD))
                );
        cursor.close();
        return maccabiUserModel;
    }*/


    //to get all users


    public ArrayList<MaccabiUserModel> getAllUser() {


        ArrayList<MaccabiUserModel> maccabiUserModelList = new ArrayList<>();

        //to select all user query
        String selectQuery = " SELECT * FROM " + MaccabiUserModel.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MaccabiUserModel maccabiUserModel = new MaccabiUserModel();
                maccabiUserModel.setId(cursor.getInt(cursor.getColumnIndex(MaccabiUserModel.COLUMN_ID)));
                maccabiUserModel.setEmailId(cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_MAIL_ID)));
                maccabiUserModel.setFirstName(cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_FIRST_NAME)));
                maccabiUserModel.setLastName(cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_LAST_NAME)));
                maccabiUserModel.setPhoneNo(cursor.getInt(cursor.getColumnIndex(MaccabiUserModel.COLUMN_PHONE_NO)));
                maccabiUserModel.setPassword(cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_PASSWORD)));


                maccabiUserModelList.add(maccabiUserModel);
            } while (cursor.moveToNext());
        }
        db.close();
        return maccabiUserModelList;
    }

    public int getUserCount() {

        String countQuery = " SELECT * FROM " + MaccabiUserModel.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    /*public int updateUser(MaccabiUserModel maccabiUserModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MaccabiUserModel.COLUMN_MAIL_ID, maccabiUserModel.getEmailId());
        values.put(MaccabiUserModel.COLUMN_FIRST_NAME, maccabiUserModel.getFirstName());
        values.put(MaccabiUserModel.COLUMN_LAST_NAME, maccabiUserModel.getLastName());
        values.put(MaccabiUserModel.COLUMN_PHONE_NO, maccabiUserModel.getPhoneNo());
        values.put(MaccabiUserModel.COLUMN_PASSWORD, maccabiUserModel.getPassword());

        return db.update(MaccabiUserModel.TABLE_NAME, values, MaccabiUserModel.COLUMN_ID + " = ?",
                new String[]{String.valueOf(maccabiUserModel.getId())});

    }*/

    public void deleteUser(MaccabiUserModel maccabiUserModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MaccabiUserModel.TABLE_NAME, MaccabiUserModel.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(maccabiUserModel.getId())}
        );
        db.close();

    }

    public int updateUser(String emailId,String firstName,String lastName,int phoneNo)
    {
        String updateUserQuery=" UPDATE " + MaccabiUserModel.TABLE_NAME+ " SET " + MaccabiUserModel.COLUMN_FIRST_NAME+ " ='"+firstName+"' , "+
                MaccabiUserModel.COLUMN_LAST_NAME+ " = '"+lastName+"' , "+ MaccabiUserModel.COLUMN_PHONE_NO + "= "+phoneNo+
                " WHERE "+MaccabiUserModel.COLUMN_MAIL_ID + " = '"+emailId+"'";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(updateUserQuery,null);
        int count =cursor.getCount();
        return count;



    }

    public boolean isMailIdExists(String emailId) {
        String isValidQuery = " SELECT * FROM " + MaccabiUserModel.TABLE_NAME +
                " WHERE " + MaccabiUserModel.COLUMN_MAIL_ID + " = '" + emailId + "'";
       SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(isValidQuery, null);
        int count = cursor.getCount();
        cursor.close();

        if (count <= 0) {
            return false;
        } else {
            return true;
        }

    }

    public boolean isPasswordCorrect(String emailId,String enteredloginPassword)
    {
      String isValidPassword= " SELECT " + MaccabiUserModel.COLUMN_PASSWORD + " FROM " + MaccabiUserModel.TABLE_NAME +
              " WHERE " + MaccabiUserModel.COLUMN_MAIL_ID + " = '" + emailId + "'";
      SQLiteDatabase db=this.getReadableDatabase();
      Cursor cursor=db.rawQuery(isValidPassword,null);
      if(cursor.moveToFirst()) {
           existLoginPassword = cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_PASSWORD));
      }
      cursor.close();

      if(enteredloginPassword.equals(existLoginPassword))
      {
          return true;
      }
      else
          return false;
    }


    public String[] getUserData(String userMailId)
    {
        String getDataQuery="SELECT " + MaccabiUserModel.COLUMN_FIRST_NAME+ " , "+MaccabiUserModel.COLUMN_LAST_NAME+" , " +
                MaccabiUserModel.COLUMN_PHONE_NO+ " FROM " + MaccabiUserModel.TABLE_NAME + " WHERE " +
                MaccabiUserModel.COLUMN_MAIL_ID+ " ='"+userMailId+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(getDataQuery,null);
        if(cursor.moveToFirst())
        {
         data= new String[]{cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_FIRST_NAME)),
                 cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_LAST_NAME)),
                 cursor.getString(cursor.getColumnIndex(MaccabiUserModel.COLUMN_PHONE_NO))};
        }
        cursor.close();
        return data;

    }


}
