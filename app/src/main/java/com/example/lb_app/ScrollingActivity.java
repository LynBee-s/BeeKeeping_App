package com.example.lb_app;

import android.net.Uri;
import android.os.Bundle;

import com.example.lb_app.databinding.ContentScrollingBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lb_app.databinding.ActivityScrollingBinding;

public class ScrollingActivity extends AppCompatActivity {
    Button buy1,buy2;
    String url="https://www.paypal.com/mep/dashboard";

    private ActivityScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
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
}