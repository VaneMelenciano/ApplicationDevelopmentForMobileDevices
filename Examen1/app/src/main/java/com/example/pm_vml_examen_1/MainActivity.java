package com.example.pm_vml_examen_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goVentanaConsulta(View view) {
        Intent i1 = new Intent(this, ventanaConsulta.class);
        startActivity(i1);
    }

    public void goVentanaAlta(View view) {
        Intent i1 = new Intent(this, ventanaAlta.class);
        startActivity(i1);
    }
}