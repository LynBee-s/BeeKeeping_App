package com.example.lb_app;

import static com.example.lb_app.HiveDB_Helper.TABLE1;
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
import android.widget.ToggleButton;

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
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Calendar;

import au.com.bytecode.opencsv.CSVWriter;

public class MainActivity3 extends AppCompatActivity {
    HiveListHelper helper;
    public ArrayList<Sales> data;
    Button btninsert2,btnSearch2,btnUpdate2,btnDelete,btnClear,btnSendrecipt;
    EditText id,transid,date2,descrip2,amt2,price2,total2,coment2;
    ImageButton shistory;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

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
                s:
                Sales();
                return true;

            case R.id.gastos:
                s:
                Expenditure();
                return true;
            case R.id.gastoshstry:
                s:
                ExpenditureHistory();
                return true;
            case R.id.salesresum:
                s:
                SalesResume();
                return true;
            case R.id.event:
                s:
                PlanEvent();
                return true;
            case R.id.geoloc:
                s:
                HiveMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        HiveDB_Helper hiveDB_helper=new HiveDB_Helper(MainActivity3.this);
        helper=new HiveListHelper(getApplicationContext(),"LBDB.db",null,1);


        btninsert2=(Button) findViewById(R.id.insert2);
        btnSearch2=(Button) findViewById(R.id.search2);
        btnUpdate2=(Button) findViewById(R.id.update2);
        btnClear=(Button) findViewById(R.id.clear2);
        btnSendrecipt=(Button) findViewById(R.id.send2);
        btnDelete=(Button)findViewById(R.id.delete1);

        shistory=(ImageButton)findViewById(R.id.shistory);

        id=(EditText) findViewById(R.id.id2);
        transid=(EditText) findViewById(R.id.transid);
        date2=(EditText) findViewById(R.id.date2);
        descrip2=(EditText) findViewById(R.id.descrip2);
        amt2=(EditText) findViewById(R.id.amt2);
        price2=(EditText) findViewById(R.id.price2);
        total2=(EditText) findViewById(R.id.total2);
        coment2=(EditText) findViewById(R.id.coment);



        btninsert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(Structure_BBDD.COLUMNA2, transid.getText().toString());
                    values.put(Structure_BBDD.COLUMNA3, date2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA4, descrip2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA5, amt2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA6, price2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA7, total2.getText().toString());
                    values.put(Structure_BBDD.COLUMNA8, coment2.getText().toString());

                    long newRowId = db.insert(TABLE2, null, values);
                    Toast.makeText(getApplicationContext(), "The register was saved with ID: " + newRowId, Toast.LENGTH_LONG).show();

                    //Clear text from fields
                    id.setText("");
                    transid.setText("");
                    date2.setText("");
                    descrip2.setText("");
                    amt2.setText("");
                    price2.setText("");
                    total2.setText("");
                    coment2.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();
                String[] projection = {
                        Structure_BBDD.COLUMNA2,
                        Structure_BBDD.COLUMNA3,
                        Structure_BBDD.COLUMNA4,
                        Structure_BBDD.COLUMNA5,
                        Structure_BBDD.COLUMNA6,
                        Structure_BBDD.COLUMNA7,
                        Structure_BBDD.COLUMNA8
                };

                String selection = Structure_BBDD.COLUMNAID + " = ?";
                String[] selectionArgs = {id.getText().toString()};


                try {
                    Cursor cursor = db.query(
                            TABLE2,   // The table to query
                            projection,             // The array of columns to return (pass null to get all)
                            selection,              // The columns for the WHERE clause
                            selectionArgs,          // The values for the WHERE clause
                            null,                   // don't group the rows
                            null,                   // don't filter by row groups
                            null               // The sort order
                    );
                    cursor.moveToFirst();
                    transid.setText("");
                    date2.setText("");
                    descrip2.setText("");
                    amt2.setText("");
                    price2.setText("");
                    total2.setText("");
                    coment2.setText("");


                    transid.setText(cursor.getString(0));
                    date2.setText(cursor.getString(1));
                    descrip2.setText(cursor.getString(2));
                    amt2.setText(cursor.getString(3));
                    price2.setText(cursor.getString(4));
                    total2.setText(cursor.getString(5));
                    coment2.setText(cursor.getString(6));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR: Could not find the requested register. ", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();
// New value for one column
                ContentValues values = new ContentValues();
                values.put(Structure_BBDD.COLUMNAID, id.getText().toString());
                values.put(Structure_BBDD.COLUMNA2, transid.getText().toString());
                values.put(Structure_BBDD.COLUMNA3, date2.getText().toString());
                values.put(Structure_BBDD.COLUMNA4, descrip2.getText().toString());
                values.put(Structure_BBDD.COLUMNA5, amt2.getText().toString());
                values.put(Structure_BBDD.COLUMNA6, price2.getText().toString());
                values.put(Structure_BBDD.COLUMNA7, total2.getText().toString());
                values.put(Structure_BBDD.COLUMNA8,coment2.getText().toString());
                String selection = Structure_BBDD.COLUMNAID + " LIKE ?";
                String[] selectionArgs = {id.getText().toString()};

                int count = db.update(
                        TABLE2,
                        values,
                        selection,
                        selectionArgs);
                Toast.makeText(getApplicationContext(), "Register " +id.getText()+ " has been successfully updated.", Toast.LENGTH_LONG).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    // Define 'where' part of query.
                    String selection = Structure_BBDD.COLUMNAID + " LIKE ?";
// Specify arguments in placeholder order.
                    String[] selectionArgs = {id.getText().toString()};
// Issue SQL statement.
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                    builder.setMessage("Are you sure you would like to delete this register?").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    db.delete(TABLE2, selection, selectionArgs);
                                    Toast.makeText(getApplicationContext(), "The register has been deleted", Toast.LENGTH_LONG).show();
                                    id.setText("");
                                    transid.setText("");
                                    date2.setText("");
                                    descrip2.setText("");
                                    amt2.setText("");
                                    price2.setText("");
                                    total2.setText("");
                                    coment2.setText("");
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
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    id.setText("");
                    transid.setText("");
                    date2.setText("");
                    descrip2.setText("");
                    amt2.setText("");
                    price2.setText("");
                    total2.setText("");
                    coment2.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnSendrecipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar  calendar=Calendar.getInstance();
                try{
                    transid.getText();
                    date2.getText();
                    descrip2.getText();
                    amt2.getText();
                    price2.getText();
                    total2.getText();

                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("*/*");
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Transaction Notification from LynBee´s");
                    intent.putExtra(Intent.EXTRA_TEXT,"Date: "+calendar.get(Calendar.DAY_OF_MONTH)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR)+" " +
                            "\n Receipt number: "+transid.getText()+" " +
                            "\n Description: "+descrip2.getText()+" " +
                            "\n Amount: "+amt2.getText()+" " +
                            "\n Price: "+price2.getText()+
                            "\n Total: "+total2.getText()+" "+
                            "\n \n  Thank you for your purchase!");
                    if(intent.resolveActivity(getPackageManager()) !=null){
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
        shistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
                    startActivity(intent);
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
    private void ExpenditureHistory() {
        try {
            Intent intent = new Intent(this, MainActivity8.class);
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
    }
    private void PlanEvent() {
        try {
            Intent intent=new Intent(this,MainActivity6.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void HiveMap() {
        try {
            Intent intent=new Intent(this,MainActivity7.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}