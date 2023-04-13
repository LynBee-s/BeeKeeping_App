package com.example.lb_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.service.controls.templates.TemperatureControlTemplate;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.sql.Time;
import java.util.Map;

public class MainActivity6 extends AppCompatActivity {
    CalendarView calendarView;
    Button newevent;
    WebView webp;
    Button webview;
    FrameLayout frameLayout;
    String url = "https://www.weather.gd/";


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
            case R.id.products:
               Products();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout1);
        newevent = (Button) findViewById(R.id.setrecuerdo);
        calendarView = (CalendarView) findViewById(R.id.calendarView7);
        View
                webview = (Button) findViewById(R.id.wv);
        webp = (WebView) findViewById(R.id.webv);
        WebView wv = new WebView(MainActivity6.this);


        newevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event();
            }
        });
        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(MainActivity6.this, Uri.parse(url));
            }
        });
    }

    private void event() {
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
            Intent intent = new Intent(this, MainActivity9.class);
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
            Intent intent = new Intent(this, MainActivity6.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }

    private void HiveMap() {
        try {
            Intent intent = new Intent(this, MainActivity7.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private void Products() {
        try {
            Intent intent=new Intent(this,CheckoutActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}