package com.example.pm_vml_05;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.lang.String;
public class MainActivity4 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //Pase dos números a la actividad 3 y
        // muestre su MCD en un Toast
        Intent i = getIntent();
        int num1 = i.getIntExtra("num1", 0);
        int num2 = i.getIntExtra("num2", 0);
        String resultado = String.valueOf(mcd(num1, num2));
        String text = "Num1: " + num1 + "\n" + "Num2: " + num2 + "\n\n" + "MCD: " + resultado;
        Toast.makeText(this, text,Toast.LENGTH_LONG).show();
    }
    public void backActivity(View view){
        finish();
    }

    public int mcd(int a,int b){
        return (b==0)? a : mcd(b, a % b);
    }
}


