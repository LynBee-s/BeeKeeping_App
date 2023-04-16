package com.example.lb_app;

import static com.example.lb_app.HiveDB_Helper.DATABASE_NAME;
import static com.example.lb_app.HiveDB_Helper.TABLE1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;

import au.com.bytecode.opencsv.CSVWriter;

public class MainActivity2 extends AppCompatActivity {
    Button btnRead,btnUpdate,btnClear,btnInsert;
    EditText id,hid,date,frame,hivst,pop,locate,note;
    public  ArrayList<Hives>data;
    HiveListHelper helper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tomainmenu12:
                MainMenu();
                return true;
            case R.id.viewlist:
                ActivityList();
                return true;
            case R.id.setevent:
                Gotoevent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//Hive Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        HiveDB_Helper hiveDB_helper=new HiveDB_Helper(MainActivity2.this);
        helper= new HiveListHelper(getApplicationContext(),"LBDB.db", null, 1);
        data=new ArrayList<>();

//Declare Buttons..................................---------------------------------------------------
        btnInsert=(Button) findViewById(R.id.insert);
        btnUpdate=(Button) findViewById(R.id.actualizar);
        btnRead=(Button) findViewById(R.id.leer);
        btnClear=(Button) findViewById(R.id.limpiar);
//Declare text fields..................................--------------------------------------------------
        id=(EditText) findViewById(R.id.id);
        hid=(EditText)findViewById(R.id.hivid);
        date=(EditText)findViewById(R.id.date6);
        hivst=(EditText)findViewById(R.id.hivstat);
        frame=(EditText)findViewById(R.id.frames);
        pop=(EditText) findViewById(R.id.popu);
        locate=(EditText) findViewById(R.id.location);
        note=(EditText)findViewById(R.id.notes);

//Button actions when pressed------------------------------------------------------------------------------
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Calendar calendar=Calendar.getInstance();
                    String DateNow= MonthDay.now().toString()+"-"+calendar.get(Calendar.YEAR);
                    date.setText(DateNow);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(Structure_BBDD.COLUMN2, hid.getText().toString());
                    values.put(Structure_BBDD.COLUMN3, date.getText().toString());
                    values.put(Structure_BBDD.COLUMN4, frame.getText().toString());
                    values.put(Structure_BBDD.COLUMN5, hivst.getText().toString());
                    values.put(Structure_BBDD.COLUMN6, pop.getText().toString());
                    values.put(Structure_BBDD.COLUMN7, locate.getText().toString());
                    values.put(Structure_BBDD.COLUMN8, note.getText().toString());
                    long newRowId = db.insert(Structure_BBDD.TABLE1, null, values);
                    Toast.makeText(getApplicationContext(), "The register was saved with ID: " + newRowId, Toast.LENGTH_LONG).show();
                    //Clear text from fields
                    id.setText("");
                    hid.setText("");
                    date.setText("");
                    frame.setText("");
                    hivst.setText("");
                    pop.setText("");
                    locate.setText("");
                    note.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SQLiteDatabase db = helper.getReadableDatabase();
                String[] projection = {
                        Structure_BBDD.COLUMN2,
                        Structure_BBDD.COLUMN3,
                        Structure_BBDD.COLUMN4,
                        Structure_BBDD.COLUMN5,
                        Structure_BBDD.COLUMN6,
                        Structure_BBDD.COLUMN7,
                        Structure_BBDD.COLUMN8
                };
                String selection = Structure_BBDD.COLUMNID + " = ?";
                String[] selectionArgs = {id.getText().toString()};
                try {
                    Cursor cursor = db.query(
                            Structure_BBDD.TABLE1,   // The table to query
                            projection,             // The array of columns to return (pass null to get all)
                            selection,              // The columns for the WHERE clause
                            selectionArgs,          // The values for the WHERE clause
                            null,                   // don't group the rows
                            null,                   // don't filter by row groups
                            null               // The sort order
                    );
                    cursor.moveToFirst();
                    hid.setText("");
                    date.setText("");
                    frame.setText("");
                    hivst.setText("");
                    pop.setText("");
                    locate.setText("");
                    note.setText("");

                    hid.setText(cursor.getString(0));
                    date.setText(cursor.getString(1));
                    frame.setText(cursor.getString(2));
                    hivst.setText(cursor.getString(3));
                    pop.setText(cursor.getString(4));
                    locate.setText(cursor.getString(5));
                    note.setText(cursor.getString(6));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR: Could not find the requested register. ", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR: Could not find the requested register. ", Toast.LENGTH_LONG).show();
                }
                }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try {
                        SQLiteDatabase db = helper.getReadableDatabase();
// New value for one column
                        ContentValues values = new ContentValues();
                        values.put(Structure_BBDD.COLUMNID, id.getText().toString());
                        values.put(Structure_BBDD.COLUMN2, hid.getText().toString());
                        values.put(Structure_BBDD.COLUMN3, date.getText().toString());
                        values.put(Structure_BBDD.COLUMN4, frame.getText().toString());
                        values.put(Structure_BBDD.COLUMN5, hivst.getText().toString());
                        values.put(Structure_BBDD.COLUMN6, pop.getText().toString());
                        values.put(Structure_BBDD.COLUMN7, locate.getText().toString());
                        values.put(Structure_BBDD.COLUMN8, note.getText().toString());
                        String selection = Structure_BBDD.COLUMNID + " LIKE ?";
                        String[] selectionArgs = {id.getText().toString()};
                        int count = db.update(
                                Structure_BBDD.TABLE1,
                                values,
                                selection,
                                selectionArgs);

                        Toast.makeText(getApplicationContext(), "Register "+id.getText()+" has been updated. ", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR: Please insert ID and try again. ", Toast.LENGTH_LONG).show();
                    }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    id.setText("");
                    hid.setText("");
                    date.setText("");
                    frame.setText("");
                    hivst.setText("");
                    pop.setText("");
                    locate.setText("");
                    note.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void MainMenu() {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void Gotoevent() {
        try {
            Intent intent = new Intent(this, MainActivity6.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void ActivityList() {
        try {
            Intent intent = new Intent(this, HiveRecordList.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}