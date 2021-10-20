package com.example.pm_vml_pr01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String c = i.getStringExtra("check");
        Toast.makeText(this, c,Toast.LENGTH_LONG).show();
    }

    public void goInicio(View view) {
        /*Intent i1 = new Intent(this, ventanaInicio.class);
        EditText correo = findViewById(R.id.correo), contra = findViewById(R.id.contra);
        i1.putExtra("corre", correo);
        i1.putExtra("contra", contra);
        startActivity(i1);*/
    }

    public void goRegistrarse(View view) {
        Intent i1 = new Intent(this, ventanaRegistrarse.class);
        //EditText etMensaje1 = findViewById(R.id.correo);
        startActivity(i1);
    }
}