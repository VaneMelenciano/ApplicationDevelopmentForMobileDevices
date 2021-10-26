package com.example.pm_vml_pro1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class detalles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        Intent i = getIntent();
        //Recibir datos
        String alias = i.getStringExtra("alias");
        String especie = i.getStringExtra("especie");

        TextView alias1 = (TextView) findViewById(R.id.alias);
        TextView raza1 = (TextView) findViewById(R.id.raza);
        TextView especie1 = (TextView) findViewById(R.id.especie);

        alias1.setText(alias); especie1.setText(especie);

    }

    public void btnRegresar(View view) {
        Intent i1 = new Intent(this, reportes.class);
        startActivity(i1);
    }
}