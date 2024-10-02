package com.example.sqlitedbex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Users.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 =  "EMAIL";
    private static final String COL_4 = "PASSWORD";
    private static final String COL_5 = "GENDER";
    private static final String COL_6 = "NEWSLETTER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT, GENDER TEXT, NEWSLETTER TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Insert Data
    public boolean insertData(String name, String email, String password, String gender, String newsletter)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,password);
        contentValues.put(COL_5,gender);
        contentValues.put(COL_6,newsletter);

        long result = db.insert(TABLE_NAME,null,contentValues);
        return result!= -1;

    }

    //Update Data
    public boolean updateData(String id, String name, String email, String password, String gender, String newsletter)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,password);
        contentValues.put(COL_5,gender);
        contentValues.put(COL_6,newsletter);

        int result = db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return result>0;

    }

    //Delete Data
    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

    //View Data
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * from " + TABLE_NAME,null);
    }
}
