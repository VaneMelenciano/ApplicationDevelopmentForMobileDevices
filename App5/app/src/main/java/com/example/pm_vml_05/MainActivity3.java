package com.example.pm_vml_05;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
public class MainActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Pase como parámetro sus datos personales:
        // nombre, correo y teléfono a la actividad 2
        // y muéstrelo con un Toast
        Intent i = getIntent();
        String nombre = i.getStringExtra("nombre");
        String correo = i.getStringExtra("correo");
        String tel = i.getStringExtra("tel");
        String texto = nombre + "\n" + correo + "\n" +tel;
        Toast.makeText(this, texto,Toast.LENGTH_LONG).show();
    }
    public void backActivity(View view){
        finish();
    }
}