package com.example.pm_vml_04;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView numero, sinCalcular;
    Button btnNuevo, btn1, btn2, btn3, btn4;
    int posCorrecta; //numero del boton que guardará la posicion del boton correcto
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numero = findViewById(R.id.numero);
        sinCalcular = findViewById(R.id.sinCalcular);
        btnNuevo = findViewById(R.id.btnNuevo);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btnNuevo.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }
    public void onClick(@NonNull View v){
        int num; //guardará el número aleatorio para el TextView
        int num1, num2;
        switch (v.getId()){
            case R.id.btnNuevo:
                sinCalcular.setText("Sin Calcular");
                num = (int)(Math.random()*100+1); //genera numero aleatorio que será para el TextView
                numero.setText(String.valueOf(num)); //manda el numero aleatorio generado
                // selecciona uno numero del 1 asl 4, que será el boton que guardé la suma correcta
                posCorrecta = (int)(Math.random()*4+1);
                for(int i=1; i<5; i++){ //ciclo que va del 1 al 4
                    if(i==posCorrecta){ //si i es el boton correcto escogido
                        num1 = (int)(Math.random()*num+1); //un mero aleatorio entre 1 y el num
                        num2 = num-num1; //segundo numero que forma la suma correcta para el num
                        // guarda el String que se mandará al botón
                        String aux = String.valueOf(num1) + "+" + String.valueOf(num2);
                        //busca el boton que corresponde a la posion i
                        if (i == 1) btn1.setText(aux);
                        else if (i == 2) btn2.setText(aux);
                        else if (i == 3) btn3.setText(aux);
                        else if (i == 4) btn4.setText(aux);
                    }else {
                        //genera dos numeros aleatorios
                        num1 = (int)(Math.random()*100+1);
                        num2 = (int)(Math.random()*100+1);
                        String aux = String.valueOf(num1) + "+" + String.valueOf(num2);
                        //busca el boton que corresponde a la posion i
                        if (i == 1) btn1.setText(aux);
                        else if (i == 2) btn2.setText(aux);
                        else if (i == 3) btn3.setText(aux);
                        else if (i == 4) btn4.setText(aux);
                    }
                }
                break;

            case R.id.btn1:
                if(posCorrecta==1) sinCalcular.setText("Correcta");
                else sinCalcular.setText("Incorrecta");
                break;
            case R.id.btn2:
                if(posCorrecta==2) sinCalcular.setText("Correcta");
                else sinCalcular.setText("Incorrecta");
                break;
            case R.id.btn3:
                if(posCorrecta==3) sinCalcular.setText("Correcta");
                else sinCalcular.setText("Incorrecta");
                break;
            case R.id.btn4:
                if(posCorrecta==4) sinCalcular.setText("Correcta");
                else sinCalcular.setText("Incorrecta");
                break;

        }
    }
}