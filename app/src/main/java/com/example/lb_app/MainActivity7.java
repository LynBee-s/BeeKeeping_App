package com.example.lb_app;

import static com.example.lb_app.HiveDB_Helper.TABLE1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity7 extends AppCompatActivity {
    public  ArrayList<Hives>data;
    private RecyclerView recyclerView;
    HiveListHelper helper;
    Button tomap;
    MapView map;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tracker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tomainmenu:
                MainMenu();
                return true;
            case R.id.go2hiverec:
                HiveRecords();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Hive Tracker
    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        helper= new HiveListHelper(getApplicationContext(),"LBDB.db", null, 1);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view7);
        data=new ArrayList<>();
        tomap=(Button) findViewById(R.id.tomap);
        map=(MapView)findViewById(R.id.mapView);

        MapView mapView = new MapView(this);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMap(Uri.parse("geo:12,0428,-61.6966?z=11"));
            }
        });
        tomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMap(Uri.parse("geo:12,0428,-61.6966?z=11"));
            }
        });
        getAdapter();
        getInfo();
    }
    private void getAdapter(){
        HiveLocationAdapter adapter = new HiveLocationAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private  void  getInfo(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Hives hives = null;
        Cursor cur = db.rawQuery("select ID,Hive_ID, Location from "+TABLE1 ,null);
        while (cur.moveToNext()) {
            hives= new Hives();
            hives.setID(cur.getString(0));
            hives.setHiveID(cur.getString(1));
            hives.setLocation(cur.getString(2));
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

}