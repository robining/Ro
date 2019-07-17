package com.robining.ro.plugindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Hello Plugin" + getResources().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
    }

    public void Go(View view) {
        startActivity(new Intent(MainActivity.this, HelloActivity.class));
    }
}
