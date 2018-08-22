package com.example.emp354.linear.Employee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;

    private static final String DATABASE_NAME="EmployeeRecord";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL(EmployeeModelClass.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + EmployeeModelClass.TABLE_NAME);

        //create table again
        onCreate(db);
    }


    public long insertEmployee(String name,int age,String profile,int salary,String address)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
       /* values.put(EmployeeModelClass.COLUMN_ID,emp_id);*/
        values.put(EmployeeModelClass.COLUMN_NAME,name);
        values.put(EmployeeModelClass.COLUMN_AGE,age);
        values.put(EmployeeModelClass.COLUMN_PROFILE,profile);
        values.put(EmployeeModelClass.COLUMN_SALARY,salary);
        values.put(EmployeeModelClass.COLUMN_ADDRESS,address);


        //insert row
        long id=db.insert(EmployeeModelClass.TABLE_NAME,null,values);
        //close db connection
        db.close();
        //return new inserted row id
        return id;
    }

    public EmployeeModelClass getEmployee(long id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(EmployeeModelClass.TABLE_NAME,
                new String[]{EmployeeModelClass.COLUMN_ID,EmployeeModelClass.COLUMN_NAME,EmployeeModelClass.COLUMN_AGE,EmployeeModelClass.COLUMN_PROFILE,EmployeeModelClass.COLUMN_SALARY,EmployeeModelClass.COLUMN_ADDRESS},
                EmployeeModelClass.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor !=null)
        {
            cursor.moveToFirst();
        }

        //prepare employee object
        EmployeeModelClass employeeModelClass=new EmployeeModelClass(
                cursor.getInt(cursor.getColumnIndex(EmployeeModelClass.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(EmployeeModelClass.COLUMN_NAME)),
                cursor.getInt(cursor.getColumnIndex(EmployeeModelClass.COLUMN_AGE)),
                cursor.getString(cursor.getColumnIndex(EmployeeModelClass.COLUMN_PROFILE)),
                cursor.getInt(cursor.getColumnIndex(EmployeeModelClass.COLUMN_SALARY)),
                cursor.getString(cursor.getColumnIndex(EmployeeModelClass.COLUMN_ADDRESS))
        );

        //close db connection
        cursor.close();
        return employeeModelClass;

    }
    public List<EmployeeModelClass> getAllEmployees()
    {
        List<EmployeeModelClass> lemployeeModelClasses=new ArrayList<>();

        //Select all query
        String selectQuery=" SELECT * FROM " + EmployeeModelClass.TABLE_NAME +" ORDER BY " +
                EmployeeModelClass.COLUMN_SALARY + " DESC ";

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        //looping through all rows and adding to list

        if(cursor.moveToFirst())
        {
            do{
                EmployeeModelClass employeeModelClass=new EmployeeModelClass();
                employeeModelClass.setId(cursor.getInt(cursor.getColumnIndex(EmployeeModelClass.COLUMN_ID)));
                employeeModelClass.setName(cursor.getString(cursor.getColumnIndex(EmployeeModelClass.COLUMN_NAME)));
                employeeModelClass.setAge(cursor.getInt(cursor.getColumnIndex(EmployeeModelClass.COLUMN_AGE)));
                employeeModelClass.setProfile(cursor.getString(cursor.getColumnIndex(EmployeeModelClass.COLUMN_PROFILE)));
                employeeModelClass.setSalary(cursor.getInt(cursor.getColumnIndex(EmployeeModelClass.COLUMN_SALARY)));
                employeeModelClass.setAddress(cursor.getString(cursor.getColumnIndex(EmployeeModelClass.COLUMN_ADDRESS)));

                lemployeeModelClasses.add(employeeModelClass);
            }while (cursor.moveToNext());
        }

        db.close();

        //return employee list
        return lemployeeModelClasses;
    }


    public int getEmployeeCount()
    {
        String countQuery="SELECT * FROM " + EmployeeModelClass.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);

        int count=cursor.getCount();
        cursor.close();

        return count;
    }

    public int updateEmployee(EmployeeModelClass employeeModelClass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        /*values.put(EmployeeModelClass.COLUMN_ID,employeeModelClass.getId());*/
        values.put(EmployeeModelClass.COLUMN_NAME,employeeModelClass.getName());
        values.put(EmployeeModelClass.COLUMN_AGE,employeeModelClass.getAge());
        values.put(EmployeeModelClass.COLUMN_PROFILE,employeeModelClass.getProfile());
        values.put(EmployeeModelClass.COLUMN_SALARY,employeeModelClass.getSalary());
        values.put(EmployeeModelClass.COLUMN_ADDRESS,employeeModelClass.getAddress());

        return db.update(EmployeeModelClass.TABLE_NAME,values,EmployeeModelClass.COLUMN_ID +" = ? ",
                new String[]{String.valueOf(employeeModelClass.getId())});
    }

    public void deleteEmployee(EmployeeModelClass employeeModelClass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(EmployeeModelClass.TABLE_NAME,EmployeeModelClass.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(employeeModelClass.getId())}
                );
        db.close();

    }

}
