package com.example.lb_app;

import static com.example.lb_app.HiveDB_Helper.DATABASE_NAME;
import static com.example.lb_app.HiveDB_Helper.TABLE4;
import static com.example.lb_app.Structure_BBDD.TABLE2;

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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

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
                export();
            } catch (Exception e) {
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
    private void export() {
        try {
            File dbFile = getDatabasePath(DATABASE_NAME);
            helper= new HiveListHelper(getApplicationContext(),"LBDB.db", null, 1);
            HiveDB_Helper dbhelper = new HiveDB_Helper(getApplicationContext());
            System.out.println(dbFile);
            File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            Calendar calendar=Calendar.getInstance();
            String DateNow= MonthDay.now().toString()+"-"+calendar.get(Calendar.YEAR);
            File file = new File(exportDir, "Harvest_History_"+DateNow+".xls");
            file.createNewFile();
            try {
                if (file.exists()) {
                    System.out.println("file.xls " + exportDir.getAbsolutePath());
                    Toast.makeText(getApplicationContext(), "Writing data to excel file...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "ATTENTION:The file already exists!", Toast.LENGTH_LONG).show();
                }
                HSSFWorkbook wb = new HSSFWorkbook();
                SQLiteDatabase db = helper.getWriteableDatabase();
                Cursor cur = helper.exportAll();
                Sheet sheet = wb.createSheet("Harvest History");

                data = new ArrayList<>();
                db = helper.getReadableDatabase();
                cur = db.rawQuery("select * from " + Structure_BBDD.TABLE4, null);
                Row row = sheet.createRow(0);
                row.setHeightInPoints(12);
                while (cur.moveToNext()) {
                    String arrStr[] = {
                            String.valueOf(cur.getString(0)),
                            String.valueOf(cur.getString(1)),
                            String.valueOf(cur.getString(2)),
                            String.valueOf(cur.getString(3)),
                            String.valueOf(cur.getString(4)),
                            String.valueOf(cur.getString(5)),
                            String.valueOf(cur.getString(6))};
                    for (int j = 0; j < arrStr.length; j++) {
                        while (cur.moveToPosition(j++)) {
                            Row row8 = sheet.createRow(j);
                            Cell cell8 = row8.createCell(0);
                            cell8.setCellValue(cur.getString(0));
                            Cell cell10 = row8.createCell(1);
                            cell10.setCellValue(cur.getString(1));
                            Cell cell11 = row8.createCell(2);
                            cell11.setCellValue(cur.getString(2));
                            Cell cell12 = row8.createCell(3);
                            cell12.setCellValue(cur.getString(3));
                            Cell cell13 = row8.createCell(4);
                            cell13.setCellValue(cur.getString(4));
                            Cell cell14 = row8.createCell(5);
                            cell14.setCellValue(cur.getString(5));
                            Cell cell15 = row8.createCell(6);
                            cell15.setCellValue(cur.getString(6));
                        }
                    }
                }
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(cur.getColumnName(0));
                Cell cell = row.createCell(1);
                cell.setCellValue(cur.getColumnName(1));
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(cur.getColumnName(2));
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(cur.getColumnName(3));
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(cur.getColumnName(4));
                Cell cell5 = row.createCell(5);
                cell5.setCellValue(cur.getColumnName(5));
                Cell cell6 = row.createCell(6);
                cell6.setCellValue(cur.getColumnName(6));

                CellStyle style = wb.createCellStyle();
                style.setBorderBottom(CellStyle.THICK_HORZ_BANDS);
                style.setBottomBorderColor(IndexedColors.YELLOW.getIndex());
                style.setBorderLeft(CellStyle.THICK_HORZ_BANDS);
                style.setLeftBorderColor(IndexedColors.YELLOW.getIndex());
                style.setBorderRight(CellStyle.THICK_HORZ_BANDS);
                style.setRightBorderColor(IndexedColors.YELLOW.getIndex());
                style.setBorderTop(CellStyle.THICK_HORZ_BANDS);
                style.setTopBorderColor(IndexedColors.YELLOW.getIndex());
                cell0.setCellStyle(style);
                cell.setCellStyle(style);
                cell2.setCellStyle(style);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style);
                cell5.setCellStyle(style);
                cell6.setCellStyle(style);
                FileOutputStream fileOut = new FileOutputStream(file);
                wb.write(fileOut);
                fileOut.close();
                Toast.makeText(getApplicationContext(),"Exported to"+exportDir.getAbsolutePath(),Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
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