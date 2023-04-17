package com.example.lb_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity10 extends AppCompatActivity {
    public ArrayList<Hives> data;
    HiveDB_Helper hivedb_helper;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_resumen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tomainmenu13:
                MainMenu();
                return true;
            case R.id.tohiverec2:
                HiveRecords();
                return true;
            case R.id.toreclist:
                ActivityList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        BarChart chart = (BarChart) findViewById(R.id.chart);
        BarDataSet set=new BarDataSet(getData(),"Hive Performance Rating");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(10f);

        BarData data=new BarData(set);
        data.setBarWidth(0.5f);
        chart.setData(data);
        chart.setFitBars(true);
        chart.getDescription().setText("Hive ID");
        chart.invalidate();


        XAxis xAxis= chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);



        YAxis yAxis= chart.getAxisLeft();
        yAxis.setAxisMinimum(0f);

        YAxis raxis=chart.getAxisRight();
        raxis.setLabelCount(0,false);
//-------------------------------------------------------------------------------------------------------------------
        BarChart hchart = (BarChart) findViewById(R.id.barChart);
        BarDataSet hset=new BarDataSet(getHdata(),"Total Honey produced per Hive (L)");
        hset.setColors(ColorTemplate.COLORFUL_COLORS);
        hset.setValueTextColor(Color.BLACK);
        hset.setValueTextSize(10f);

        BarData hdata=new BarData(hset);
        hdata.setBarWidth(0.5f);
        hchart.setData(hdata);
        hchart.setFitBars(true);
        hchart.getDescription().setText("Hive ID");

        hchart.invalidate();

        XAxis xAxish= hchart.getXAxis();
        xAxish.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxish.setAxisMinimum(0f);


        YAxis yAxish= hchart.getAxisLeft();
        yAxish.setAxisMinimum(0f);

        YAxis raxish=hchart.getAxisRight();
        raxish.setLabelCount(0,false);

    }
    private ArrayList<BarEntry>getData(){
        hivedb_helper=new HiveDB_Helper(this);
        SQLiteDatabase db=hivedb_helper.getWritableDatabase();
        ArrayList<BarEntry>dataVal=new ArrayList<>();
        Cursor cur=hivedb_helper.getHiveBar();
        for(int i=0;i<cur.getCount();i++){
            cur.moveToNext();
            dataVal.add(new BarEntry(cur.getInt(0),cur.getInt(1)));

        }
        cur.close();
        db.close();
        return  dataVal;
    }
    private ArrayList<BarEntry>getHdata(){
        hivedb_helper=new HiveDB_Helper(this);
        SQLiteDatabase db=hivedb_helper.getWritableDatabase();
        ArrayList<BarEntry>dataValu=new ArrayList<>();
        Cursor cur=hivedb_helper.getHarvestBar();
        for(int i=0;i<cur.getCount();i++){
            cur.moveToNext();
            dataValu.add(new BarEntry(cur.getInt(0),cur.getInt(1)));

        }
        cur.close();
        db.close();
        return  dataValu;
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
    private void ActivityList() {
        try {
            Intent intent = new Intent(this, HiveRecordList.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}