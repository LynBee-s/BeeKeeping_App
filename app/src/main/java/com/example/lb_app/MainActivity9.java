package com.example.lb_app;

import static com.example.lb_app.HiveDB_Helper.DATABASE_NAME;
import static com.example.lb_app.HiveDB_Helper.TABLE4;
import static com.example.lb_app.Structure_BBDD.TABLE2;
import static com.example.lb_app.Structure_BBDD.TABLE3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
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
import android.widget.ImageButton;
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

public class MainActivity9 extends AppCompatActivity {
    public ArrayList<Harvest> data;
   EditText id9,hiveid9,date9,amt9,other9,amtt9,notes9;
   Button btnDelete,btnInsert;
   HiveListHelper helper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_harvestrec, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tomainmenu:
                MainMenu();
                return true;

            case R.id.recevent:
                SetReminder();
                return true;

            case R.id.tohiverec:
                HiveRecords();
                return true;
            case R.id.toharvrec:
                HarvestReclist();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        HiveDB_Helper hiveDB_helper=new HiveDB_Helper(MainActivity9.this);
        data=new ArrayList<>();

        id9=(EditText) findViewById(R.id.id9);
        hiveid9=(EditText) findViewById(R.id.hiveid9);
        date9=(EditText) findViewById(R.id.date9);
        amt9=(EditText) findViewById(R.id.amt9);
        other9=(EditText) findViewById(R.id.other9);
        amtt9=(EditText) findViewById(R.id.amtt9) ;
        notes9=(EditText) findViewById(R.id.notes9);

        btnInsert=(Button) findViewById(R.id.btninsert8);
        btnDelete=(Button)findViewById(R.id.btnDelete);



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    // Define 'where' part of query.
                    String selection = Structure_BBDD.COLUMNAID + " LIKE ?";
// Specify arguments in placeholder order.
                    String[] selectionArgs = {id9.getText().toString()};
// Issue SQL statement.
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity9.this);
                    builder.setMessage("Are you sure you would like to delete this register?").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    db.delete(TABLE2, selection, selectionArgs);
                                    Toast.makeText(getApplicationContext(), "The register has been deleted", Toast.LENGTH_SHORT).show();
                                    id9.setText("");
                                    hiveid9.setText("");
                                    date9.setText("");
                                    amt9.setText("");
                                    other9.setText("");
                                    amtt9.setText("");
                                    notes9.setText("");
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
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Calendar calendar=Calendar.getInstance();
                    String DateNow= MonthDay.now().toString()+"-"+calendar.get(Calendar.YEAR);

                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(Structure_BBDD.COLUMNC2, hiveid9.getText().toString());
                    values.put(Structure_BBDD.COLUMNC3, DateNow);
                    values.put(Structure_BBDD.COLUMNC4, amt9.getText().toString());
                    values.put(Structure_BBDD.COLUMNC5, other9.getText().toString());
                    values.put(Structure_BBDD.COLUMNC6,amtt9.getText().toString());
                    long newRowId = db.insert(TABLE4, null, values);
                    Toast.makeText(getApplicationContext(), "The register was saved with ID: " + newRowId, Toast.LENGTH_SHORT).show();

                    //Clear text from fields
                    id9.setText("");
                    hiveid9.setText("");
                    date9.setText("");
                    amt9.setText("");
                    other9.setText("");
                    amtt9.setText("");
                    notes9.setText("");
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
    private void HiveRecords() {
        try {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void SetReminder() {
        try {
            Intent intent=new Intent(this,MainActivity6.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void HarvestReclist() {
        try {
            Intent intent=new Intent(this,HarvestRecordList.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}