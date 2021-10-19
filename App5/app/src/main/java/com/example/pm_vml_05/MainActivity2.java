package com.example.pm_vml_05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Obtener los parametros
        Intent i = getIntent();
        String mensaje = i.getStringExtra("mensaje");
        Toast.makeText(this, "Mensaje enviado: " + mensaje,Toast.LENGTH_LONG).show();

    }
    public void backActivity(View view){
        finish();
    }
}