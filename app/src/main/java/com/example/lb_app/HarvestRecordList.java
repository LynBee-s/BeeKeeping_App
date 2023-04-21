package com.example.lb_app;

import static com.example.lb_app.HiveDB_Helper.DATABASE_NAME;
import static com.example.lb_app.HiveDB_Helper.TABLE4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class HarvestRecordList extends AppCompatActivity {
    private RecyclerView recyclerView;
    HiveListHelper helper;
    Button btnExport;
    public ArrayList<Harvest> data;
    HarvestAdapter harvestAdapter=null;
    HarvestAdapter.ViewHolder viewHolder=null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_harvestreclist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tomainmenu6:
                MainMenu();
                return true;
            case R.id.recevent:
                SetReminder();
                return true;

            case R.id.tohiverec:
                HiveRecords();
                return true;
            case R.id.insertnewharvrec:
                NewHarvRec();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_record_list);
        HiveDB_Helper hiveDB_helper=new HiveDB_Helper(HarvestRecordList.this);
        helper= new HiveListHelper(getApplicationContext(),"LBDB.db", null, 1);
        recyclerView= findViewById(R.id.recycler_v3);
        btnExport= findViewById(R.id.btnexport8);
        data=new ArrayList<>();

        getAdapter();
        getInfo();
        btnExport.setOnClickListener(v -> {
            try {
                SQLCSV();
                csv2xl();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void getAdapter(){
        HarvestAdapter adapter = new HarvestAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private  void  getInfo(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Harvest harvest = null;
        Cursor cur = db.rawQuery("select * from "+ HiveDB_Helper.TABLE4 ,null);
        while (cur.moveToNext()) {
            harvest=new Harvest();
            harvest.setID(cur.getString(0));
            harvest.setHive_ID(cur.getString(1));
            harvest.setDate(cur.getString(2));
            harvest.setAmount_H(cur.getString(3));
            harvest.setOther(cur.getString(4));
            harvest.setAmount_O(cur.getString(5));
            harvest.setNotes(cur.getString(6));
            data.add(harvest);
        }
    }
    private void SQLCSV() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        HiveDB_Helper dbhelper = new HiveDB_Helper(getApplicationContext());
        System.out.println(dbFile);  // displays the data base path in your logcat
        File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "HaRdatos.csv");
        try {
            if (file.createNewFile()) {
                System.out.println("file.csv " + file.getAbsolutePath());
                Toast.makeText(getApplicationContext(), "Writing data to excel file...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "ATTENTION:The file already exists!", Toast.LENGTH_LONG).show();
            }
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM " + TABLE4, null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                String arrStr[] = {
                        curCSV.getString(0),
                        curCSV.getString(1),
                        curCSV.getString(2),
                        curCSV.getString(3),
                        curCSV.getString(4),
                        curCSV.getString(5),
                        curCSV.getString(6)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void csv2xl() throws IOException {
        ArrayList arList = null;
        ArrayList al = null;
        try {
            Calendar calendar=Calendar.getInstance();
            calendar.getTime();
            calendar.getTimeZone();
            String inFilePath = Environment.getExternalStorageDirectory().toString() + "/Documents/HaRdatos.csv";
            String outFilePath = Environment.getExternalStorageDirectory().toString() + "/Documents/HarvestRecords_"+ MonthDay.now() +"_"+calendar.get(Calendar.YEAR)+".xls";
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
                HSSFSheet sheet = hwb.createSheet("Records");
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
                Toast.makeText(getApplicationContext(), "Harvest Record has been successfully exported", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(getApplicationContext(), "ERROR: Failed to export Harvest Record document.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void delete()throws SecurityException{
        try {
            String inFilePath = Environment.getExternalStorageDirectory().toString() + "/Documents/HaRdatos.csv";
            File file2 = new File(inFilePath);
            file2.delete();
        }catch (Exception exc){
            exc.printStackTrace();
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
    private void SetReminder() {
        try {
            Intent intent=new Intent(this,MainActivity8.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void NewHarvRec() {
        try {
            Intent intent=new Intent(this,MainActivity9.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}