package com.example.lb_app;

import static com.example.lb_app.Structure_BBDD.TABLE2;
import static com.example.lb_app.Structure_BBDD.TABLE4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewRenderProcessClient;
import android.widget.Button;
import android.widget.Toast;

import org.apache.poi.xslf.model.geom.Context;
import org.xml.sax.helpers.ParserAdapter;

import java.net.URL;
import java.text.ParsePosition;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity9 extends AppCompatActivity {
    HiveListHelper helper;
    private RecyclerView recyclerView;
    public ArrayList<Forecast> data;
    WebView webp;
    Button webview;

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
        setContentView(R.layout.activity_main9);
        HiveDB_Helper hiveDB_helper=new HiveDB_Helper(MainActivity9.this);
        helper=new HiveListHelper(getApplicationContext(),"LBDB.db",null,1);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view8);
        webview=(Button)findViewById(R.id.wv);
        webp=(WebView)findViewById(R.id.webv);
        data=new ArrayList<>();
        WebView wv=new WebView(MainActivity9.this);

        WebViewClient wvcl=new WebViewClient();
        //wvcl.onPageStarted();
        //wv.getUrl();


        getAdapter();
        getInfo();
        wv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void getAdapter(){
        ForecastAdapter adapter = new ForecastAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private  void  getInfo(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Forecast forecast=null;
        Cursor cur = db.rawQuery("select * from "+TABLE4 ,null);
        while (cur.moveToNext()) {
           forecast=new Forecast();
            forecast.setDate(cur.getString(0));
            forecast.setDay(cur.getString(1));
            forecast.setTemperature(cur.getString(2));
            forecast.setConditions(cur.getString(3));
            data.add(forecast);
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