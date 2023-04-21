package com.example.lb_app;


import static com.example.lb_app.HiveDB_Helper.TABLE4;
import static com.example.lb_app.Structure_BBDD.TABLE2;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity9 extends AppCompatActivity {
    public ArrayList<Harvest> data;
   EditText id9,hiveid9,date9,amt9,other9,amtt9,notes9;
   Button btnInsert;
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
            case R.id.tomainmenu9:
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
    //Expenditure
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        HiveDB_Helper hiveDB_helper=new HiveDB_Helper(MainActivity9.this);
        helper= new HiveListHelper(getApplicationContext(),"LBDB.db", null, 1);
        data=new ArrayList<>();

        id9= findViewById(R.id.id9);
        hiveid9= findViewById(R.id.hiveid9);
        date9= findViewById(R.id.date9);
        amt9= findViewById(R.id.amt9);
        other9= findViewById(R.id.other9);
        amtt9= findViewById(R.id.amtt9);
        notes9= findViewById(R.id.notes9);

        btnInsert=(Button) findViewById(R.id.btninsert8);


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
                    values.put(Structure_BBDD.COLUMNC7,notes9.getText().toString());
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