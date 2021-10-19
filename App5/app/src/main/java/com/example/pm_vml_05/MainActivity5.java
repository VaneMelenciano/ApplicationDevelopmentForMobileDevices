package com.example.pm_vml_05;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
public class MainActivity5 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        //Pase un arreglo de números a la actividad 4 y muestre el promedio en un Toast.
        Intent i = getIntent();
        int[] numeros = i.getIntArrayExtra("arreglo");
        String text = "";
        for(int j=0; j<numeros.length-1; j++){
            text += String.valueOf(numeros[j]) + ", ";
        } text += String.valueOf(numeros[numeros.length-1]);
        Toast.makeText(this, "Numeros: " + text,Toast.LENGTH_LONG).show();

    }
    public void backActivity(View view){
        finish();
    }
}

