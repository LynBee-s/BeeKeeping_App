package com.example.lb_app;

import org.apache.poi.xslf.model.geom.Context;

public class Structure_BBDD {
    public Structure_BBDD(Context context){}
    public static final String DATABASE_NAME = "LBDB.db";
    public static final String TABLE1 = "Hive_Rec";
    public static final String COLUMNID = "ID";
    public static final String COLUMN2 = "Hive_ID";
    public static final String COLUMN3 = "Date";
    public static final String COLUMN4 = "Frames";
    public static final String COLUMN5 = "Hive_Stat";
    public static final String COLUMN6 = "Population";
    public static final String COLUMN7 = "Location";
    public static final String COLUMN8 = "Notes";

    public static final String TABLE2 = "Sales";
    public static final String COLUMNAID = "ID";
    public static final String COLUMNA2 = "Trans_ID";
    public static final String COLUMNA3 = "Date";
    public static final String COLUMNA4 = "Description";
    public static final String COLUMNA5 = "Amount";
    public static final String COLUMNA6 = "Price";
    public static final String COLUMNA7 = "Total";
    public static final String COLUMNA8 = "Notes";

    public static final String TABLE3 = "Expenditure";
    public static final String COLUMNBID = "ID";
    public static final String COLUMNB2 = "Trans_ID";
    public static final String COLUMNB3 = "Date";
    public static final String COLUMNB4 = "Description";
    public static final String COLUMNB5 = "Amount";
    public static final String COLUMNB6 = "Price";
    public static final String COLUMNB7 = "Total";
    public static final String COLUMNB8 = "Notes";

    public static final String TABLE4 = "Honey_Rec";
    public static final String COLUMNCID = "ID";
    public static final String COLUMNC2 = "Hive_ID";
    public static final String COLUMNC3 = "Date";
    public static final String COLUMNC4 = "Amount_H";
    public static final String COLUMNC5 = "Other";
    public static final String COLUMNC6 = "Amount_O";
    public static final String COLUMNC7 = "Notes";



    public static final String SQL_CREATE_ENTRIES1 =

            " CREATE TABLE " + Structure_BBDD.TABLE1 + " ( " +
                    Structure_BBDD.COLUMNID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    Structure_BBDD.COLUMN2 + " TEXT,"+
                    Structure_BBDD.COLUMN3 +" TEXT," +
                    Structure_BBDD.COLUMN4 + " TEXT,"+
                    Structure_BBDD.COLUMN5 + " TEXT,"+
                    Structure_BBDD.COLUMN6 + " TEXT,"+
                    Structure_BBDD.COLUMN7 + " TEXT,"+
                    Structure_BBDD.COLUMN8 + " TEXT ); ";

    // public static final String SQL_DELETE_ENTRIES =
    // "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE_NAME;

    public static final String SQL_CREATE_ENTRIES2 =

            "CREATE TABLE " + Structure_BBDD.TABLE2 + " ( " +
                    Structure_BBDD.COLUMNAID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    Structure_BBDD.COLUMNA2 + " TEXT,"+
                    Structure_BBDD.COLUMNA3 +" TEXT," +
                    Structure_BBDD.COLUMNA4 + " TEXT,"+
                    Structure_BBDD.COLUMNA5 + " TEXT,"+
                    Structure_BBDD.COLUMNA6 + " TEXT,"+
                    Structure_BBDD.COLUMNA7 + " TEXT,"+
                    Structure_BBDD.COLUMNA8 + " TEXT ); ";

    // public static final String SQL_DELETE_ENTRIES =
    // "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE_NAME;

    public static final String SQL_CREATE_ENTRIES3 =

            "CREATE TABLE " + Structure_BBDD.TABLE3 + " ( " +
                    Structure_BBDD.COLUMNBID + " INTEGER PRIMARY KEY ," +
                    Structure_BBDD.COLUMNB2 + " TEXT,"+
                    Structure_BBDD.COLUMNB3 +" TEXT," +
                    Structure_BBDD.COLUMNB4 + " TEXT,"+
                    Structure_BBDD.COLUMNB5 + " TEXT,"+
                    Structure_BBDD.COLUMNB6 + " TEXT,"+
                    Structure_BBDD.COLUMNB7 + " TEXT,"+
                    Structure_BBDD.COLUMNB8 + " TEXT ); ";

    // public static final String SQL_DELETE_ENTRIES =
    // "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE3;

    public static final String SQL_CREATE_ENTRIES4 =

            " CREATE TABLE " + Structure_BBDD.TABLE4 + " ( " +
                    Structure_BBDD.COLUMNCID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    Structure_BBDD.COLUMNC2 + " TEXT,"+
                    Structure_BBDD.COLUMNC3 +" TEXT," +
                    Structure_BBDD.COLUMNC4 + " TEXT,"+
                    Structure_BBDD.COLUMNC5 + " TEXT,"+
                    Structure_BBDD.COLUMNC6 + " TEXT,"+
                    Structure_BBDD.COLUMNC7 + " TEXT ); ";

        // public static final String SQL_DELETE_ENTRIES =
        // "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE4;

}
