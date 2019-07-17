package com.robining.ro.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.robining.ro.Ro;
import com.robining.ro.stub.StubActivity01;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startSecond(null);
//        Toast.makeText(this, "fixed", Toast.LENGTH_SHORT).show();
    }

    public void startSecond(View view) {
        Intent intent = new Intent();
        intent.setClassName(this,"com.robining.ro.plugindemo.MainActivity");
//        intent.setClass(this,StubActivity01.class);
        startActivity(intent);
    }
}
