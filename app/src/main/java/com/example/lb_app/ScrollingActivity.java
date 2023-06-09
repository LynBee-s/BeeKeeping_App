package com.example.lb_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;

import com.example.lb_app.databinding.ActivityScrollingBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ScrollingActivity extends AppCompatActivity {
    Button buy1,buy2;
    String url="https://www.paypal.com/mep/dashboard";
    String urlinsta="https://www.instagram.com/lynbeehoney/?hl=en";
    String urlfb="https://www.facebook.com/profile.php?id=100067959146512&notif_id=1680897311748116&notif_t=page_user_activity&ref=notif";

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scrolling, menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tomainmenu2:
                MainMenu();
                return true;
            case R.id.action_settings:
                Insta();
                return true;

            case R.id.action_settings2:
                Facebk();
                return true;
            case R.id.action_settings3:
                TransCash();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.lb_app.databinding.ActivityScrollingBinding binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        buy1=(Button) findViewById(R.id.buy);
        buy2=(Button) findViewById(R.id.buy2);
        buy1.setOnClickListener(v -> {
            try{
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(ScrollingActivity.this, Uri.parse(url));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        buy2.setOnClickListener(v -> {
            try{
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(ScrollingActivity.this, Uri.parse(url));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    private void MainMenu() {
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
        }
    }
    private  void Insta(){
        try{
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(ScrollingActivity.this, Uri.parse(urlinsta));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private  void Facebk() {
        try {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(ScrollingActivity.this, Uri.parse(urlfb));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void  TransCash(){
        Intent intent=new Intent(this,MainActivity3.class);
        startActivity(intent);
    }
}