package com.example.lb_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HiveListHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LBDB.db";
    public static final String TABLE1= "Hive_Rec";
    public static final String TABLE2 = "Sales";
    public static final String TABLE3 = "Expenditure";
    public HiveListHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES1);
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES2);
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES3);
        onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(Estructura_BBDD.SQL_DELETE_ENTRIES);
    }
    public Cursor exportAll() {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+ TABLE1,null);
        return cur;
    }
}

