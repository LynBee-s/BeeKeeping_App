package com.example.lb_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class HiveListHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LBDB.db";
    public static final String TABLE1= "Hive_Rec";
    public static final String TABLE3 = "Expenditure";
    public static final String TABLE4 = "Harvest_Rec";
    public HiveListHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES1);
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES2);
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES3);
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES4);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(Estructura_BBDD.SQL_DELETE_ENTRIES);
    }
    public void exportAll() {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+ TABLE1,null);
        cur.close();
    }

    public void getWriteableDatabase() {
    }
}

