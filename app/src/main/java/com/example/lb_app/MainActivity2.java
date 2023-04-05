package com.example.lb_app;

import static com.example.lb_app.HiveDB_Helper.DATABASE_NAME;
import static com.example.lb_app.HiveDB_Helper.TABLE1;
import static com.example.lb_app.Structure_BBDD.COLUMN2;
import static com.example.lb_app.Structure_BBDD.COLUMN3;
import static com.example.lb_app.Structure_BBDD.COLUMN4;
import static com.example.lb_app.Structure_BBDD.COLUMN5;
import static com.example.lb_app.Structure_BBDD.COLUMN6;
import static com.example.lb_app.Structure_BBDD.COLUMN7;
import static com.example.lb_app.Structure_BBDD.COLUMN8;
import static com.example.lb_app.Structure_BBDD.COLUMNID;
import static com.example.lb_app.Structure_BBDD.TABLE2;

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
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.examples.HSSFReadWrite;
import org.apache.poi.ss.usermodel.Cell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import au.com.bytecode.opencsv.CSVWriter;

public class MainActivity2 extends AppCompatActivity {
    Button btnRead,btnUpdate,btnClear,btnExport,btnInsert;
    EditText id,hid,date,frame,hivst,pop,locate,note;
    public  ArrayList<Hives>data;
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
        setContentView(R.layout.activity_main2);
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
                    values.put(COLUMN4, frame.getText().toString());
                    values.put(COLUMN5, hivst.getText().toString());
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
                        COLUMN4,
                        COLUMN5,
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
                values.put(COLUMN4, frame.getText().toString());
                values.put(COLUMN5, hivst.getText().toString());
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
                    SQLCSV();
                    csv2xl();
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
    private void SQLCSV() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        HiveDB_Helper dbhelper = new HiveDB_Helper(getApplicationContext());
        System.out.println(dbFile);  // displays the data base path in your logcat
        File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "LBdatos.csv");
        try {
            if (file.createNewFile()) {
                System.out.println("file.csv " + file.getAbsolutePath());
                Toast.makeText(getApplicationContext(), "Writing data to excel file...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "ATTENTION:The file already exists!", Toast.LENGTH_LONG).show();
            }
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM " + TABLE1, null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                String arrStr[] = {
                        curCSV.getString(0),
                        curCSV.getString(1),
                        curCSV.getString(2),
                        curCSV.getString(3),
                        curCSV.getString(4),
                        curCSV.getString(5),
                        curCSV.getString(6),
                        curCSV.getString(7)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void csv2xl() throws IOException  {
        ArrayList arList = null;
        ArrayList al = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.getTime();
            calendar.getTimeZone();
            String inFilePath = Environment.getExternalStorageDirectory().toString() + "/Documents/LBdatos.csv";

            String outFilePath = Environment.getExternalStorageDirectory().toString() + "/Documents/HiveActivity"+calendar.get(Calendar.DAY_OF_MONTH)+"_"+calendar.get(Calendar.MONTH)+"_"+calendar.get(Calendar.YEAR)+".xls";
            String thisLine;
            int count = 0;
            try {
                FileInputStream fis = new FileInputStream(inFilePath);
                BufferedReader myInput = new BufferedReader(new InputStreamReader(fis));
                int i = 0;
                arList = new ArrayList();
                while ((thisLine = myInput.readLine()) != null) {
                    al = new ArrayList();
                    String strar[] = thisLine.split(",");
                    for (int j = 0; j < strar.length; j++) {
                        al.add(strar[j]);
                    }
                    arList.add(al);
                    System.out.println();
                    i++;
                }
            } catch (Exception e) {
                System.out.println();
            }
            try {
                HSSFWorkbook hwb = new HSSFWorkbook();
                HSSFSheet sheet = hwb.createSheet("Activity");
                for (int k = 0; k < arList.size(); k++) {
                    ArrayList ardata = (ArrayList) arList.get(k);
                    HSSFRow row = sheet.createRow((short) 0 + k);
                    for (int p = 0; p < ardata.size(); p++) {
                        HSSFCell cell = row.createCell((int) p);
                        String data = ardata.get(p).toString();
                        if (data.startsWith("=")) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            data = data.replaceAll("\"", "");
                            data = data.replaceAll("=", "");
                            cell.setCellValue(data);
                        } else if (data.startsWith("\"")) {
                            data = data.replaceAll("\"", "");
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            cell.setCellValue(data);
                        } else {
                            data = data.replaceAll("\"", "");
                            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(data);
                        }

                    }
                    System.out.println();
                }
                FileOutputStream fileOut = new FileOutputStream(outFilePath);
                hwb.write(fileOut);
                fileOut.close();
                System.out.println("Your excel file has been generated");
                delete();
                Toast.makeText(getApplicationContext(), "´Hive Records´ has been successfully exported to "+getFilesDir(), Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(getApplicationContext(), "ERROR: Failed to export file ´Hive Records´ .", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void complete() throws ArrayIndexOutOfBoundsException {
        Calendar calendar=null;
        //Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
    }
    private void delete()throws SecurityException{
        try {
            String inFilePath = Environment.getExternalStorageDirectory().toString() + "/Documents/LBdatos.csv";
            File file2 = new File(inFilePath);
            file2.delete();
        }catch (Exception exc){
            exc.printStackTrace();
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
    private void HiveMap() {
        try {
            Intent intent=new Intent(this,MainActivity7.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}