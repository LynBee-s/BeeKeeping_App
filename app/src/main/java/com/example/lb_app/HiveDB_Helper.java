package com.example.lb_app;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.nio.charset.StandardCharsets;



@SuppressWarnings("ResultOfMethodCallIgnored")
public class HiveDB_Helper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LBDB.db";
    public static final String TABLE1 = "Hive_Rec";
    public static final String TABLE2 = "Sales";
    public static final String TABLE3 = "Expenditure";
    public static final String TABLE4 = "Harvest_Rec";

    public HiveDB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public SQLiteDatabase getReadableDatabase(SQLiteDatabase db, String s) {
        db.execSQL("select * from " + TABLE1);
        db.execSQL("select * from " + TABLE2);
        db.execSQL("select * from " + TABLE3);
        s.getBytes(StandardCharsets.UTF_8);
        return null;
    }

    public Cursor getHiveBar() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE1, null);

}public Cursor getHarvestBar() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE4, null);
    }
}
