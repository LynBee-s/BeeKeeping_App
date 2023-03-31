package com.example.lb_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    private void expt() {
        File dbFile = getDatabasePath("CBG_Datos.db");
        HiveDB_Helper helper = new HiveDB_Helper(getApplicationContext());
        System.out.println(dbFile);
        File exportDir = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet fsheet = wb.createSheet("Registry");
            createColumns();
            insertItem();
            FileOutputStream fos = null;
            try {
                String inFilePath = Environment.getExternalStorageDirectory().toString() + "/Documents/datos.xls";
                File file = new File(inFilePath);
                wb.write(fos);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
            Toast.makeText(getApplicationContext(), "Exported", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void createColumns() {
        HSSFSheet fsheet = null;
        HSSFRow rowA = fsheet.createRow(0);
        for (int k = 0; k < data.size(); k++) {
            HSSFCell cellA = rowA.createCell(k);
            cellA.setCellValue(new HSSFRichTextString("" + data.get(k)));
        }
    }
    private  void  insertItem(){
        String obj=null;
        HiveDB_Helper helper=null;
        HSSFSheet fsheet = null;
        Cursor cursor= helper.exportAll();
        cursor.moveToFirst();
        int n=1;
        while (!cursor.isAfterLast()){
            HSSFRow rowA=fsheet.createRow(n);
            for (int i = 0; i < data.size(); i++){
                HSSFCell cellA=rowA.createCell(i);
                cellA.setCellValue(new HSSFRichTextString(cursor.getString(i)));
            }
            System.out.println();
            n++;
            cursor.moveToNext();
        }
    }

}