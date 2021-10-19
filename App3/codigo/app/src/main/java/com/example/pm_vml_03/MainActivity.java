package com.example.pm_vml_03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast t = Toast.makeText(this, "Estoy en onCreate", Toast.LENGTH_LONG);
        t.show();
    }
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Toast t = Toast.makeText(this, "Estoy en onRestoreInstanceState", Toast.LENGTH_LONG);
        t.show();
    }
    @Override
    public void onStart(){
        super.onStart();
        Toast t = Toast.makeText(this, "Estoy en onStart", Toast.LENGTH_LONG);
        t.show();
    }
    @Override
    public void onRestart(){
        super.onRestart();
        Toast t = Toast.makeText(this, "Estoy en onRestart", Toast.LENGTH_LONG);
        t.show();
    }
    @Override
    public void onResume(){
        super.onResume();
        Toast t = Toast.makeText(this, "Estoy en onResume", Toast.LENGTH_LONG);
        t.show();
    }
    @Override
    public void onPause(){
        super.onPause();
        Toast t = Toast.makeText(this, "Estoy en onPause", Toast.LENGTH_LONG);
        t.show();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Toast t = Toast.makeText(this, "Estoy en onSaveInstanceState", Toast.LENGTH_LONG);
        t.show();
    }
    @Override
    public void onStop(){
        super.onStop();
        Toast t = Toast.makeText(this, "Estoy en onStop", Toast.LENGTH_LONG);
        t.show();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast t = Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG);
        t.show();
    }
}