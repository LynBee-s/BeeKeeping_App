package com.example.lb_app;



import androidx.appcompat.app.AppCompatActivity;

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
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;



public class MainActivity2 extends AppCompatActivity {
    Button btnRead,btnUpdate,btnClear,btnInsert;
    EditText id,hid,date,frame,hivst,pop,locate,note,ghivestat;
    public  ArrayList<Hives>data;
    HiveListHelper helper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hactivity, menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
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
            case R.id.tohiveresum:
                HiveResumen();
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
       new HiveDB_Helper(MainActivity2.this);
        helper= new HiveListHelper(getApplicationContext(),"LBDB.db", null, 1);
        data=new ArrayList<>();
//Declare Buttons..................................---------------------------------------------------
        btnInsert= findViewById(R.id.insert);
        btnUpdate= findViewById(R.id.actualizar);
        btnRead=(Button) findViewById(R.id.leer);
        btnClear=(Button) findViewById(R.id.limpiar);
//Declare text fields..................................--------------------------------------------------
        id=(EditText) findViewById(R.id.id);
        hid=(EditText)findViewById(R.id.hivid);
        date=(EditText)findViewById(R.id.date6);
        hivst=(EditText)findViewById(R.id.hivstat);
        frame=(EditText)findViewById(R.id.frames);
        pop=(EditText) findViewById(R.id.popu);
        ghivestat=(EditText)findViewById(R.id.ghivestat2);
        locate=(EditText) findViewById(R.id.location);
        note=(EditText)findViewById(R.id.notes);
//Button actions when pressed------------------------------------------------------------------------------
        btnInsert.setOnClickListener(v -> {
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
                values.put(Structure_BBDD.COLUMN7, ghivestat.getText().toString());
                values.put(Structure_BBDD.COLUMN8, locate.getText().toString());
                values.put(Structure_BBDD.COLUMN9, note.getText().toString());
                long newRowId = db.insert(Structure_BBDD.TABLE1, null, values);
                Toast.makeText(getApplicationContext(), "The register was saved with ID: " + newRowId, Toast.LENGTH_LONG).show();
                //Clear text from fields
                id.setText("");
                hid.setText("");
                date.setText("");
                frame.setText("");
                hivst.setText("");
                pop.setText("");
                ghivestat.setText("");
                locate.setText("");
                note.setText("");
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });
        btnRead.setOnClickListener(v -> {
            try{
            SQLiteDatabase db = helper.getReadableDatabase();
            String[] projection = {
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
                ghivestat.setText("");
                locate.setText("");
                note.setText("");

                hid.setText(cursor.getString(0));
                date.setText(cursor.getString(1));
                frame.setText(cursor.getString(2));
                hivst.setText(cursor.getString(3));
                pop.setText(cursor.getString(4));
                ghivestat.setText(cursor.getString(5));
                locate.setText(cursor.getString(6));
                note.setText(cursor.getString(7));
                cursor.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR: Could not find the requested register. ", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR: Could not find the requested register. ", Toast.LENGTH_LONG).show();
            }
            });
        btnUpdate.setOnClickListener(v -> {
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
                    values.put(Structure_BBDD.COLUMN7, ghivestat.getText().toString());
                    values.put(Structure_BBDD.COLUMN8, note.getText().toString());
                    values.put(Structure_BBDD.COLUMN9, note.getText().toString());
                    String selection = Structure_BBDD.COLUMNID + " LIKE ?";
                    String[] selectionArgs = {id.getText().toString()};
                   db.update(Structure_BBDD.TABLE1,values, selection,selectionArgs);

                    Toast.makeText(getApplicationContext(), "Register "+id.getText()+" has been updated. ", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR: Please insert ID and try again. ", Toast.LENGTH_LONG).show();
                }
        });

        btnClear.setOnClickListener(v -> {
            try {
                id.setText("");
                hid.setText("");
                date.setText("");
                frame.setText("");
                hivst.setText("");
                pop.setText("");
                ghivestat.setText("");
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
    private void HiveResumen() {
        try {
            Intent intent = new Intent(this,MainActivity10.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}