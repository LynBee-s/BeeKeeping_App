package com.example.lb_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.nio.charset.StandardCharsets;


public class HiveDB_Helper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LBDB.db";
    public static final String TABLE1 = "Hive_Rec";
    public static final String TABLE2 = "Sales";
    public static final String TABLE3 = "Expenditure";
    public static final String TABLE4 = "Harvest_Rec";


    public HiveDB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public HiveDB_Helper(Context context, String s, Object o, int i) {
        super(context.getApplicationContext(),DATABASE_NAME,null,1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES1);
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES2);
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES3);
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES4);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(Estructura_BBDD.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public SQLiteDatabase getReadableDatabase(SQLiteDatabase db, String s) {
        db.execSQL("select * from " + TABLE1);
        db.execSQL("select * from " + TABLE2);
        db.execSQL("select * from " + TABLE3);
        db.execSQL("select * from " + TABLE4);
        s.getBytes(StandardCharsets.UTF_8).toString();
        return null;
    }

    public SQLiteDatabase getWriteableDatabase(SQLiteDatabase db, String s) {
        db.execSQL("select * from " + TABLE1);
        db.execSQL("select * from " + TABLE2);
        db.execSQL("select * from " + TABLE3);
        db.execSQL("select * from " + TABLE4);
        return null;

    }
    public Cursor getHiveBar(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT Hive_ID, sum(General_Stat) FROM "+ TABLE1+" GROUP By Hive_ID", null);
        return cur;
    }
    public Cursor getHarvestBar(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT Hive_ID, sum(Amount_H) FROM "+ TABLE4+" GROUP By Amount_H ", null);
        return cur;
    }
}
