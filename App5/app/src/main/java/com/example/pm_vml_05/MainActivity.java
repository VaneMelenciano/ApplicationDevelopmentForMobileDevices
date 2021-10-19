package com.example.pm_vml_05;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goActivity(View view) {
        //view = Para saber sobre cual boton se da Touch
        switch (view.getId()){
            case R.id.btnActivity1:
                Intent i1 = new Intent(this, MainActivity2.class);
                EditText etMensaje1 = findViewById(R.id.etMensaje);
                i1.putExtra("mensaje", etMensaje1.getText().toString());
                startActivity(i1);
                break;
            case R.id.btnActivity2:
                Intent i2 = new Intent(this, MainActivity3.class);
                String nombre = "Vanessa Melenciano";
                String correo = "vanemelenciano@gmail.com";
                String tel = "492-111-11-11";
                i2.putExtra("nombre", nombre);
                i2.putExtra("correo", correo);
                i2.putExtra("tel", tel);
                startActivity(i2);
                break;
            case R.id.btnActivity3:
                Intent i3 = new Intent(this, MainActivity4.class);
                EditText etMensaje3 = findViewById(R.id.etMensaje);
                int num1 = (int)(Math. random()*50+1);
                int num2 = (int)(Math. random()*50+1);
                i3.putExtra("num1", num1);
                i3.putExtra("num2", num2);
                startActivity(i3);
                break;
            case R.id.btnActivity4:
                Intent i4 = new Intent(this, MainActivity5.class);
                int[] numeros = new int[(int)(Math. random()*10+2)];
                for(int i=0; i<numeros.length;i++){
                    numeros[i] = (int)(Math. random()*50+1);
                }
                i4.putExtra("arreglo", numeros);
                startActivity(i4);
                break;
        }
    }
}