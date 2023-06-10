package com.example.lb_app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    Button btnRead,btnUpdate,btnClear,btnInsert;
    EditText id,hid,date,frame,hivst,pop,locate,hprof,note;
    public  ArrayList<Hives>data;
    HiveDB_Helper hiveDB_helper=null;
    HiveListHelper helper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mmenu:
                MainMenu();
                return true;

            case R.id.hivrec:
                HiveRecords();
                return true;

            case R.id.ventas:
                Sales();
                return true;

            case R.id.gastos:
                Expenditure();
                return true;
            case R.id.geoloc:
                MapHive();
                return true;
            case R.id.salesresum:
                SalesResume();
                return true;
            case R.id.tohiveresum:
                HiveRes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        hiveDB_helper=new HiveDB_Helper(MainActivity2.this);
        helper= new HiveListHelper(getApplicationContext(),HiveListHelper.DATABASE_NAME, null);
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
        hprof=(EditText) findViewById(R.id.ghivestat2);
        locate=(EditText) findViewById(R.id.location);
        note=(EditText)findViewById(R.id.notes);

       btnInsert.setOnClickListener(v -> {
            try {
                Calendar calendar = Calendar.getInstance();
                String DateNow = MonthDay.now().toString() + "-" + calendar.get(Calendar.YEAR);
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Structure_BBDD.COLUMN2, hid.getText().toString());
                values.put(Structure_BBDD.COLUMN3, DateNow);
                values.put(Structure_BBDD.COLUMN4, frame.getText().toString());
                values.put(Structure_BBDD.COLUMN5, hivst.getText().toString());
                values.put(Structure_BBDD.COLUMN6, pop.getText().toString());
                values.put(Structure_BBDD.COLUMN7, hprof.getText().toString());
                values.put(Structure_BBDD.COLUMN8, locate.getText().toString());
               values.put(Structure_BBDD.COLUMN9, note.getText().toString());

                long newRowId = db.insert(Structure_BBDD.TABLE1, null, values);
                Toast.makeText(getApplicationContext(), "The register was saved with ID: " + newRowId, Toast.LENGTH_LONG).show();
                id.setText("");
                hid.setText("");
                date.setText("");
                frame.setText("");
                hivst.setText("");
                pop.setText("");
                hprof.setText("");
                locate.setText("");
                note.setText("");
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });
        btnRead.setOnClickListener(v -> {
            SQLiteDatabase db = helper.getReadableDatabase();
            String[] projection = {
                    Structure_BBDD.COLUMNID,
                    Structure_BBDD.COLUMN2,
                    Structure_BBDD.COLUMN3,
                    Structure_BBDD.COLUMN4,
                    Structure_BBDD.COLUMN5,
                    Structure_BBDD.COLUMN6,
                    Structure_BBDD.COLUMN7,
                    Structure_BBDD.COLUMN8,
                    Structure_BBDD.COLUMN9

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
                hprof.setText("");
                locate.setText("");
                note.setText("");

                id.setText(cursor.getString(0));
                hid.setText(cursor.getString(1));
                date.setText(cursor.getString(2));
                frame.setText(cursor.getString(3));
                hivst.setText(cursor.getString(4));
                pop.setText(cursor.getString(5));
                hprof.setText(cursor.getString(6));
                locate.setText(cursor.getString(7));
                note.setText(cursor.getString(8));
                cursor.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR: Could not find the requested register. ", Toast.LENGTH_LONG).show();
            }
        });
        btnUpdate.setOnClickListener(v -> {
            SQLiteDatabase db = helper.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(Structure_BBDD.COLUMNID, id.getText().toString());
            values.put(Structure_BBDD.COLUMN2, hid.getText().toString());
            values.put(Structure_BBDD.COLUMN3, date.getText().toString());
            values.put(Structure_BBDD.COLUMN4, frame.getText().toString());
            values.put(Structure_BBDD.COLUMN5, hivst.getText().toString());
            values.put(Structure_BBDD.COLUMN6, pop.getText().toString());
            values.put(Structure_BBDD.COLUMN7, hprof.getText().toString());
            values.put(Structure_BBDD.COLUMN8, locate.getText().toString());
            values.put(Structure_BBDD.COLUMN9,note.getText().toString());
            String selection = Structure_BBDD.COLUMNID + " LIKE ?";
            String[] selectionArgs = {id.getText().toString()};

            db.update(Structure_BBDD.TABLE1, values, selection, selectionArgs);
            Toast.makeText(getApplicationContext(), "Register " +id.getText()+ " has been successfully updated.", Toast.LENGTH_LONG).show();
        });

        btnClear.setOnClickListener(v -> {
            try {
                id.setText("");
                hid.setText("");
                date.setText("");
                frame.setText("");
                hivst.setText("");
                pop.setText("");
                hprof.setText("");
                locate.setText("");
                note.setText("");
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
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
    private void HiveRecords() {
        try {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }

    private void Sales() {
        try {
            Intent intent = new Intent(this, MainActivity3.class);
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }

    private void Expenditure() {
        try {
            Intent intent = new Intent(this, MainActivity4.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }

    }
    private void MapHive() {
        try {
            Intent intent = new Intent(this, MainActivity9.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void SalesResume() {
        try {
            Intent intent = new Intent(this, MainActivity5.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    } private void HiveRes() {
        try {
            Intent intent = new Intent(this, MainActivity10.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }

}