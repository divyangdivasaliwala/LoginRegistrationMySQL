package com.example.admin.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(uname text ,email text,pass text,date text,phone number)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
    public  boolean insert(String uname, String email, String pass, String date, String phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("uname",uname);
        c.put("email",email);
        c.put("pass",pass);
        c.put("date",date);
        c.put("phone",phone);
        long ins=db.insert("user",null,c);
        if(ins==-1) return false;
        else return true;
    }
    public boolean check(String uname,String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from user where uname=? and pass=?",new String[]{uname,pass});
        if (c.getCount()>0) return true;
        else  return  false;
    }
}
