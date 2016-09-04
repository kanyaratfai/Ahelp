package com.example.pareeya.ahelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 3/9/2559.
 */
public class MyManage {
    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String table_name = "userTABLE";
    public static final String column_id = "id";
    public static final String column_Name = "Name";
    public static final String column_MyPhone = "MyPhone";

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }   //Constructor

    //Add Value SQLite ให้เก็บเป็นตัวเลข
    public long addValueToSQLite(String strName, String strMyPhone) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Name,strName);
        contentValues.put(column_MyPhone,strMyPhone);


        return sqLiteDatabase.insert(table_name,null,contentValues);
    }
}   //main class
