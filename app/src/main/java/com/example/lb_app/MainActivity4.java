package com.example.lb_app;


import static com.example.lb_app.Structure_BBDD.TABLE3;

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
import java.util.Calendar;


public class MainActivity4 extends AppCompatActivity {
    HiveListHelper helper;
    Button btnInsert,btnUpdate,btnSearch,btnClear;
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
    @SuppressLint("NonConstantResourceId")
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
         new HiveDB_Helper(MainActivity4.this);
        helper = new HiveListHelper(getApplicationContext(), "LBDB.db", null);

        btnInsert = findViewById(R.id.insert3);
        btnUpdate = findViewById(R.id.update3);
        btnSearch = findViewById(R.id.search3);
        btnClear = findViewById(R.id.clear3);

        id3 = findViewById(R.id.id3);
        transid3 = findViewById(R.id.transid3);
        date3 = findViewById(R.id.date3);
        descrip3 = findViewById(R.id.descrip3);
        amt3 = findViewById(R.id.amt3);
        price3 = findViewById(R.id.price3);
        total3 = findViewById(R.id.total3);
        coment3 = findViewById(R.id.coment3);

        btnInsert.setOnClickListener(v -> {
            try {
                Calendar calendar = Calendar.getInstance();
                String DateNow = MonthDay.now().toString() + "-" + calendar.get(Calendar.YEAR);
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
        });
        btnSearch.setOnClickListener(v -> {

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
                cursor.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR: Could not find the requested register. ", Toast.LENGTH_LONG).show();
            }
        });
        btnUpdate.setOnClickListener(v -> {
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
                db.update(TABLE3, values, selection, selectionArgs);
                Toast.makeText(getApplicationContext(), "Register " + id3.getText() + " has been successfully updated.", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR:Please insert ID and try again.", Toast.LENGTH_LONG).show();
            }
        });

        btnClear.setOnClickListener(v -> {
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