package com.example.pm_vml_pr01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ventanaRegistrarse extends AppCompatActivity {

    private CheckBox checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registrarse);

        //Intent i = getIntent();
        //String correoo = i.getStringExtra("correo");
        //Toast.makeText(this, "HOLA",Toast.LENGTH_LONG).show();


    }
    public void goInicio(View view) {
        /*Intent i1 = new Intent(this, ventanaInicio.class);
        EditText correo = findViewById(R.id.correo), contra = findViewById(R.id.contra), nombre = findViewById(R.id.nombre);
        i1.putExtra("corre", correo);
        i1.putExtra("contra", contra);
        i1.putExtra("nombre", nombre);
        startActivity(i1);*/
        checked = (CheckBox) findViewById(R.id.checkTerminos);
        if(checked.isChecked()) Toast.makeText(this, "Bien",Toast.LENGTH_LONG).show(); //está seleccionado
        else Toast.makeText(this, "Mal",Toast.LENGTH_LONG).show(); // no está seleccionado
    }

    public void goIngresar(View view) {
        Intent i1 = new Intent(this, MainActivity.class);
        //EditText etMensaje1 = findViewById(R.id.correo);
        startActivity(i1);
    }
}