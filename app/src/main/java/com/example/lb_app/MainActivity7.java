package com.example.lb_app;

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
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity7 extends AppCompatActivity {

    Button btnRead,btnUpdate,btnClear,btnExport,btnInsert;
    EditText id,hid,date,frame,hivst,pop,locate,note;
    public ArrayList<Hives> data;
    private RecyclerView recyclerView;
    HiveListHelper helper;

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        HiveDB_Helper hiveDB_helper=new HiveDB_Helper(MainActivity2.this);
        helper= new HiveListHelper(getApplicationContext(),"LBDB.db", null, 1);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        data=new ArrayList<>();

        getAdapter();
        getInfo();


//Declare Buttons..................................---------------------------------------------------
        btnInsert=(Button) findViewById(R.id.insert);
        btnUpdate=(Button) findViewById(R.id.actualizar);
        btnExport=(Button) findViewById(R.id.exportar);
        btnRead=(Button) findViewById(R.id.leer);
        btnClear=(Button) findViewById(R.id.limpiar);
//Declare text fields..................................--------------------------------------------------
        id=(EditText) findViewById(R.id.id);
        hid=(EditText)findViewById(R.id.hivid);
        date=(EditText)findViewById(R.id.date);
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
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(String.valueOf(Structure_BBDD.COLUMNID), id.getText().toString());
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
                try{
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
                    values.put(Structure_BBDD.COLUMN8,note.getText().toString());
                    String selection = Structure_BBDD.COLUMNID + " LIKE ?";
                    String[] selectionArgs = {id.getText().toString()};

                    int count = db.update(
                            Structure_BBDD.TABLE1,
                            values,
                            selection,
                            selectionArgs);
                    Toast.makeText(getApplicationContext(), "Register " +id.getText()+ " has been successfully updated.", Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR: Could not update the selected data. Please try again. ", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    exprt();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR: Failed to export the database.", Toast.LENGTH_LONG).show();
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
    private void exprt(){
        File dbFile = getDatabasePath("LBDB.db");
        HiveListHelper helper;
        helper= new HiveListHelper(getApplicationContext(),"LBDB.db", null, 1);
        System.out.println(dbFile);
        File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "T1.xls");
        try {
            if (file.createNewFile()) {
                System.out.println("file.xls " + file.getAbsolutePath());
                Toast.makeText(getApplicationContext(), "Generating xls file...", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "ATTENCION: The file already exists!", Toast.LENGTH_LONG).show();
            }
            String inFilePath = Environment.getExternalStorageDirectory().toString() + "/Documents/T1.xls";
            String outFilePath = Environment.getExternalStorageDirectory().toString() + "/Documents/TW.xls";
            FileInputStream fis = new FileInputStream(inFilePath);
            BufferedReader myInput = new BufferedReader(new InputStreamReader(fis));
            HSSFWorkbook wb=new HSSFWorkbook();
            FileWriter fw=new FileWriter(file);
            HSSFSheet fsheet=wb.createSheet("registry");
            HSSFRow rowA=fsheet.createRow(0);
            for (int k = 0; k < data.size(); k++) {
                HSSFCell cellA=rowA.createCell(k);
                cellA.setCellValue(new HSSFRichTextString(""+data.get(k)));
                Cursor cur=helper.exportAll();
                cur.moveToFirst();
                int n=1;
                while(!cur.isAfterLast()){
                    rowA=fsheet.createRow(n);
                    for (int l = 0; l < data.size(); l++){
                        cellA=rowA.createCell(l);
                        cellA.setCellValue(new HSSFRichTextString(cur.getColumnName(n)));
                        wb.write(new OutputStream() {
                            @Override
                            public void write(int b) throws IOException {
                                FileOutputStream fileOut = new FileOutputStream(outFilePath);
                                wb.write(fileOut);
                                fileOut.close();
                            }
                        });
                    }
                    n++;
                    cur.moveToNext();
                    fw.write(cur.getString(n));
                }
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
        }
    }
    private void getAdapter(){
        HiveAdapter adapter = new HiveAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private  void  getInfo(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Hives hives = null;
        Cursor cur = db.rawQuery("select * from "+TABLE1 ,null);
        while (cur.moveToNext()) {
            hives= new Hives();
            hives.setID(cur.getString(0));
            hives.setHiveID(cur.getString(1));
            hives.setDate(cur.getString(2));
            hives.setHive_Stat(cur.getString(3));
            hives.setFrames(cur.getString(4));
            hives.setPopulation(cur.getString(5));
            hives.setLocation(cur.getString(6));
            hives.setNotes(cur.getString(7));
            data.add(hives);
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

}