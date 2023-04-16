package com.example.lb_app;

import static com.example.lb_app.Structure_BBDD.TABLE2;
import static com.example.lb_app.Structure_BBDD.TABLE3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.commons.collections.functors.StringValueTransformer;
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

public class MainActivity4 extends AppCompatActivity {
    HiveListHelper helper;
    Button btnInsert,btnUpdate,btnSearch,btnDelete,btnClear;
    EditText id3,transid3,date3,descrip3,amt3,price3,total3,coment3;
    private float TotalI;
    private float AmtI;
    private float PriceI;

    @Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_expenses, menu);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tomainmenu10:
                MainMenu();
                return true;
            case R.id.viewexp:
                ViewExp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Sales
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        HiveDB_Helper hiveDB_helper = new HiveDB_Helper(MainActivity4.this);
        helper = new HiveListHelper(getApplicationContext(), "LBDB.db", null, 1);

        btnInsert = (Button) findViewById(R.id.insert3);
        btnUpdate = (Button) findViewById(R.id.update3);
        btnSearch = (Button) findViewById(R.id.search3);
        btnDelete = (Button) findViewById(R.id.delete3);
        btnClear = (Button) findViewById(R.id.clear3);


        id3 = (EditText) findViewById(R.id.id3);
        transid3 = (EditText) findViewById(R.id.transid3);
        date3 = (EditText) findViewById(R.id.date3);
        descrip3 = (EditText) findViewById(R.id.descrip3);
        amt3 = (EditText) findViewById(R.id.amt3);
        price3 = (EditText) findViewById(R.id.price3);
        total3 = (EditText) findViewById(R.id.total3);
        coment3 = (EditText) findViewById(R.id.coment3);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Calendar calendar = Calendar.getInstance();
                    String DateNow = MonthDay.now().toString() + "-" + calendar.get(Calendar.YEAR);
                    date3.setText(DateNow);
                    PriceI = Float.parseFloat(price3.getText().toString());
                    AmtI = Float.parseFloat(amt3.getText().toString());
                    TotalI = PriceI * AmtI;

                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(Structure_BBDD.COLUMNB2, transid3.getText().toString());
                    values.put(Structure_BBDD.COLUMNB3, DateNow);
                    values.put(Structure_BBDD.COLUMNB4, descrip3.getText().toString());
                    values.put(Structure_BBDD.COLUMNB5, amt3.getText().toString());
                    values.put(Structure_BBDD.COLUMNB6, price3.getText().toString());
                    values.put(Structure_BBDD.COLUMNB7, TotalI);
                    values.put(Structure_BBDD.COLUMNB8, coment3.getText().toString());
                    long newRowId = db.insert(TABLE3, null, values);
                    Toast.makeText(getApplicationContext(), "The register was saved with ID: " + newRowId, Toast.LENGTH_LONG).show();

                    //Clear text from fields
                    id3.setText("");
                    transid3.setText("");
                    date3.setText("");
                    descrip3.setText("");
                    amt3.setText("");
                    price3.setText("");
                    total3.setText("");
                    coment3.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();
                String[] projection = {
                        Structure_BBDD.COLUMNB2,
                        Structure_BBDD.COLUMNB3,
                        Structure_BBDD.COLUMNB4,
                        Structure_BBDD.COLUMNB5,
                        Structure_BBDD.COLUMNB6,
                        Structure_BBDD.COLUMNB7,
                        Structure_BBDD.COLUMNB8
                };

                String selection = Structure_BBDD.COLUMNAID + " = ?";
                String[] selectionArgs = {id3.getText().toString()};


                try {
                    Cursor cursor = db.query(
                            TABLE3,   // The table to query
                            projection,             // The array of columns to return (pass null to get all)
                            selection,              // The columns for the WHERE clause
                            selectionArgs,          // The values for the WHERE clause
                            null,                   // don't group the rows
                            null,                   // don't filter by row groups
                            null               // The sort order
                    );
                    cursor.moveToFirst();
                    transid3.setText("");
                    date3.setText("");
                    descrip3.setText("");
                    amt3.setText("");
                    price3.setText("");
                    total3.setText("");
                    coment3.setText("");

                    transid3.setText(cursor.getString(0));
                    date3.setText(cursor.getString(1));
                    descrip3.setText(cursor.getString(2));
                    amt3.setText(cursor.getString(3));
                    price3.setText(cursor.getString(4));
                    total3.setText(cursor.getString(5));
                    coment3.setText(cursor.getString(6));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR: Could not find the requested register. ", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PriceI = Float.parseFloat(price3.getText().toString());
                    AmtI = Float.parseFloat(amt3.getText().toString());
                    TotalI = PriceI * AmtI;
                    SQLiteDatabase db = helper.getReadableDatabase();
// New value for one column
                    ContentValues values = new ContentValues();
                    values.put(Structure_BBDD.COLUMNAID, id3.getText().toString());
                    values.put(Structure_BBDD.COLUMNA2, transid3.getText().toString());
                    values.put(Structure_BBDD.COLUMNA3, date3.getText().toString());
                    values.put(Structure_BBDD.COLUMNA4, descrip3.getText().toString());
                    values.put(Structure_BBDD.COLUMNA5, amt3.getText().toString());
                    values.put(Structure_BBDD.COLUMNA6, price3.getText().toString());
                    values.put(Structure_BBDD.COLUMNA7, TotalI);
                    values.put(Structure_BBDD.COLUMNA8, coment3.getText().toString());
                    String selection = Structure_BBDD.COLUMNAID + " LIKE ?";
                    String[] selectionArgs = {id3.getText().toString()};

                    int count = db.update(
                            TABLE3,
                            values,
                            selection,
                            selectionArgs);
                    Toast.makeText(getApplicationContext(), "Register " + id3.getText() + " has been successfully updated.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR:Please insert ID and try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    // Define 'where' part of query.
                    String selection = Structure_BBDD.COLUMNBID + " LIKE ?";
// Specify arguments in placeholder order.
                    String[] selectionArgs = {id3.getText().toString()};
// Issue SQL statement.
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
                    builder.setMessage("Are you sure you would like to delete this register?").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    db.delete(TABLE3, selection, selectionArgs);
                                    Toast.makeText(getApplicationContext(), "The register has been deleted", Toast.LENGTH_LONG).show();
                                    id3.setText("");
                                    transid3.setText("");
                                    date3.setText("");
                                    descrip3.setText("");
                                    amt3.setText("");
                                    price3.setText("");
                                    total3.setText("");
                                    coment3.setText("");
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }

                                public void onCick(DialogInterface dialogInterface, int i) {

                                }

                            }).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR: Failed to delete the database.", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    id3.setText("");
                    transid3.setText("");
                    date3.setText("");
                    descrip3.setText("");
                    amt3.setText("");
                    price3.setText("");
                    total3.setText("");
                    coment3.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "System Error.Please restart the application and try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void ViewExp() {
        try {
            Intent intent = new Intent(this, MainActivity8.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void MainMenu() {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}