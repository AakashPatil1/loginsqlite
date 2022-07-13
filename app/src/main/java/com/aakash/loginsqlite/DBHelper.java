package com.aakash.loginsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    //This is Name our Database
    public static final String DBNAME = "Logina.db";

    //Constructor
    public DBHelper( Context context) {
        super(context,"Logina.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //Create Table
        MyDB.execSQL("Create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        //drop Table
        MyDB.execSQL("Drop Table if exists users");
    }

    //Method insertData
    public Boolean insertData(String username, String password){
        //
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null, contentValues);
        //condition
        if (result==-1) return false;
        else
            return true;


    }

    //Function checkusername
    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        //use rawQuery for checkusername
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?",new String[] {username});

        //condition
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //Function checkusernamepassword
    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        //use rawQuery for checkusernamepassword
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?",new String[] {username,password});
        //condition
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
