package com.example.android_doctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBlink extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DBlink(Context context) {

        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(mobilenumber TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public Boolean insert_value(String mobile,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mobilenumber",mobile);
        values.put("password",password);
        long result = db.insert("users",null,values);

        return result != -1;

    }
    public  Boolean check_mobile(String mobile){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE mobilenumber = ?",new String[]{mobile});

        return cursor.getCount() > 0;
    }

    public  Boolean check_mobile_pass(String mobile, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE mobilenumber = ? AND password = ?",new String[]{mobile, password});

        return cursor.getCount() > 0;
    }
}
