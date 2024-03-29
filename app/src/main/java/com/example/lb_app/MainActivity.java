package com.example.lb_app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnHR,btnS,btnE,btnMp,btnActP,btnHarV;
    ActivityResultLauncher<String[]> mPermissionResultlauncher;
    private boolean isReadPermissionGranted = false;
    private boolean isLocationPermissionGranted = false;
    private boolean isWritePermissionGranted = false;
    private boolean isReadCalendarPermissionGranted = false;
    private boolean isWriteCalendarPermissionGranted = false;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mmenu:
                MainMenu();
                return true;
                case R.id.hivrec:
                HiveRecords();
                return true;
            case R.id.planner:
                ActivityPlanner();
                return true;
            case R.id.ventas:
                Sales();
                return true;
            case R.id.gastos:
                Expenditure();
                return true;
            case R.id.gastoshstry:
                ExpenditureHistory();
                return true;
            case R.id.salesresum:
                SalesResume();
                return true;
            case R.id.event:
                PlanEvent();
                return true;
            case R.id.geoloc:
                HiveMap();
                return true;
            case R.id.harvestrec:
                HarvestRecords();
                return true;
            case R.id.products:
                Products();
                return true;
            case R.id.resumen:
                ProductionHistory();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHR= findViewById(R.id.hr);
        btnS= findViewById(R.id.sls);
        btnE= findViewById(R.id.exp);
        btnMp= findViewById(R.id.gl);
        btnActP= findViewById(R.id.button);
        btnHarV= findViewById(R.id.btnharvestrec);


        btnMp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity7.class);
            startActivity(intent);

        });
        btnE.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity4.class);
            startActivity(intent);
        });
        btnHR.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });
        btnS.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });
        btnActP.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, MainActivity6.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });
        btnHarV.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, MainActivity9.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });
        mPermissionResultlauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

            if (result.get(Manifest.permission.READ_EXTERNAL_STORAGE) != null) {
                isReadPermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.READ_EXTERNAL_STORAGE));
            }
            if (result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != null) {
                isWritePermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE));
            }
            if (result.get(Manifest.permission.ACCESS_FINE_LOCATION) != null) {
                isLocationPermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.ACCESS_FINE_LOCATION));
            }
            if (result.get(Manifest.permission.ACCESS_COARSE_LOCATION) != null) {
                isLocationPermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.ACCESS_COARSE_LOCATION));
            }
            if (result.get(Manifest.permission.READ_CALENDAR) != null) {
                isReadCalendarPermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.READ_CALENDAR));
            }
            if (result.get(Manifest.permission.WRITE_CALENDAR) != null) {
                isWriteCalendarPermissionGranted = Boolean.TRUE.equals(result.get(Manifest.permission.WRITE_CALENDAR));
            }
        });
        requestPermission();
    }
    private void requestPermission() {
        isReadPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        isWritePermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        isLocationPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        isReadCalendarPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED;
        isWriteCalendarPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED;

        List<String> permissionRequest = new ArrayList<>();
        if (!isReadPermissionGranted) {
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!isLocationPermissionGranted) {
            permissionRequest.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!isWritePermissionGranted) {
            permissionRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!isReadCalendarPermissionGranted) {
            permissionRequest.add(Manifest.permission.READ_CALENDAR);
        }
        if (!isWriteCalendarPermissionGranted) {
            permissionRequest.add(Manifest.permission.WRITE_CALENDAR);
        }
        if(permissionRequest.isEmpty()){
            requestPermission();
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
    private void ActivityPlanner() {
        try {
            Intent intent=new Intent(this,MainActivity6.class);
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
              Intent intent = new Intent(Intent.ACTION_INSERT) 
                     .setData(CalendarContract.Events.CONTENT_URI) 
                     .putExtra(CalendarContract.Events.TITLE, "Reminder: ") 
                     .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true); 
             if (intent.resolveActivity(getPackageManager()) != null) { 
                 startActivity(intent); 
             }
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
    private void HarvestRecords() {
        try {
            Intent intent=new Intent(this,MainActivity9.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void Products() {
        try {
            Intent intent=new Intent(this,ScrollingActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void ProductionHistory() {
        try {
            Intent intent=new Intent(this,MainActivity10.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}