package com.example.pm_vml_06;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String resOperacion = "";
    boolean vacio = true, error = false;
    Button btnSuma, btnResta, btnMultiplicacion,
            btnDivision, btnIgual, btnLimpiar, btnPunto;
    Button btnUno, btnDos, btnTres, btnCuatro, btnCinco,
            btnSeis, btnSiete, btnOcho, btnNueve, btnCero;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dis_calculadora);
        btnSuma = findViewById(R.id.btnSuma);
        btnResta = findViewById(R.id.btnResta);
        btnMultiplicacion = findViewById(R.id.btnMultiplicacion);
        btnDivision = findViewById(R.id.btnDivision);
        btnIgual = findViewById(R.id.btnIgual);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnPunto = findViewById(R.id.btnPunto);
        btnUno = findViewById(R.id.btnUno);
        btnDos = findViewById(R.id.btnDos);
        btnTres = findViewById(R.id.btnTres);
        btnCuatro = findViewById(R.id.btnCuatro);
        btnCinco = findViewById(R.id.btnCinco);
        btnSeis = findViewById(R.id.btnSeis);
        btnSiete = findViewById(R.id.btnSiete);
        btnOcho = findViewById(R.id.btnOcho);
        btnNueve = findViewById(R.id.btnNueve);
        btnCero = findViewById(R.id.btnCero);
        display = findViewById(R.id.tvDisplay);

        btnLimpiar.setOnClickListener(this);
        btnSuma.setOnClickListener(this);
        btnResta .setOnClickListener(this);
        btnMultiplicacion.setOnClickListener(this);
        btnDivision.setOnClickListener(this);
        btnIgual.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);
        btnPunto.setOnClickListener(this);
        btnUno.setOnClickListener(this);
        btnDos.setOnClickListener(this);
        btnTres.setOnClickListener(this);
        btnCuatro.setOnClickListener(this);
        btnCinco.setOnClickListener(this);
        btnSeis.setOnClickListener(this);
        btnSiete.setOnClickListener(this);
        btnOcho.setOnClickListener(this);
        btnNueve.setOnClickListener(this);
        btnCero.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(error){display.setText(""); error = false; resOperacion="";}
        switch (v.getId()) {
            case R.id.btnSuma:
                if(!vacio &&  Character.isDigit(resOperacion.charAt(resOperacion.length()-1))) {
                    resOperacion += "+";
                    display.setText(resOperacion);
                }else{display.setText("error"); error=true;}
                break;
            case R.id.btnResta:
                if(!vacio &&  Character.isDigit(resOperacion.charAt(resOperacion.length()-1))) {
                    resOperacion += "-";
                    display.setText(resOperacion);
                }else{display.setText("error");error=true;}
                break;
            case R.id.btnMultiplicacion:
               if(!vacio &&  Character.isDigit(resOperacion.charAt(resOperacion.length()-1))) {
                    resOperacion += "x";
                    display.setText(resOperacion);
                }else{display.setText("error");error=true;}
                break;
            case R.id.btnDivision:
                if(!vacio &&  Character.isDigit(resOperacion.charAt(resOperacion.length()-1))) {
                    resOperacion += "/";
                    display.setText(resOperacion);
                }else{display.setText("error");error=true;}
                break;
            case R.id.btnIgual:
                if(!vacio &&  Character.isDigit(resOperacion.charAt(resOperacion.length()-1))) {
                    display.setText(evaluarOperacion(resOperacion));
                    resOperacion = "";
                    vacio=true;
                }
                break;
            case R.id.btnPunto:
                if(!vacio &&  Character.isDigit(resOperacion.charAt(resOperacion.length()-1))) {
                    resOperacion += ".";
                    display.setText(resOperacion);
                }else{display.setText("error");error=true;}
                break;
            case R.id.btnLimpiar:
                display.setText(evaluarOperacion(resOperacion));
                resOperacion = "";
                vacio = true;
                break;
                //NUMEROS
            case R.id.btnUno:
                resOperacion += "1";
                display.setText(resOperacion);
                vacio = false;
                break;
            case R.id.btnDos:
                resOperacion += "2";
                display.setText(resOperacion);
                vacio = false;
                break;
            case R.id.btnTres:
                resOperacion += "3";
                display.setText(resOperacion);
                vacio = false;
                break;
            case R.id.btnCuatro:
                resOperacion += "4";
                display.setText(resOperacion);
                vacio = false;
                break;
            case R.id.btnCinco:
                resOperacion += "5";
                display.setText(resOperacion);
                vacio = false;
                break;
            case R.id.btnSeis:
                resOperacion += "6";
                display.setText(resOperacion);
                vacio = false;
                break;
            case R.id.btnSiete:
                resOperacion += "7";
                display.setText(resOperacion);
                vacio = false;
                break;
            case R.id.btnOcho:
                resOperacion += "8";
                display.setText(resOperacion);
                vacio = false;
                break;
            case R.id.btnNueve:
                vacio=false;
                resOperacion += "9";
                display.setText(resOperacion);
                break;
            case R.id.btnCero:
                vacio = false;
                resOperacion += "0";
                display.setText(resOperacion);
        }
    }

    public String evaluarOperacion(String operacion){
        String operaciones = operacion;
        for(int i=1; i<operaciones.length();){//si es multiplicacion o division
            if(operaciones.charAt(i) == 120  ||  operaciones.charAt(i) == 47) {
                float num1, num2;
                int j=i-1, auxi =i;
                String result = "", numAux1 = "", numAux2 = "";
                i++;
                while(i<operaciones.length() && (Character.isDigit(operaciones.charAt(i)) || operaciones.charAt(i) == 46)) { //mientras sea un numero o punto
                    numAux2+=operaciones.charAt(i);
                    i++;
                }
                num2 = Float.parseFloat(numAux2);
                while(j>=0 && (Character.isDigit(operaciones.charAt(j)) || operaciones.charAt(j) == 46)){ //mientras sea un numero o punto
                    numAux1+=operaciones.charAt(j);
                    j--;
                }
                StringBuilder strb = new StringBuilder(numAux1);
                numAux1 = strb.reverse().toString(); //se da vuelta
                num1 = Float.parseFloat(numAux1);
                if(operaciones.charAt(auxi) == 120){ //multiplicación
                    float res = num1*num2;
                    String res1 = String.valueOf(res);
                    StringBuffer auxCadena = new StringBuffer(operaciones);
                    auxCadena.replace( j+1 ,i-1 ,res1);
                    operaciones = auxCadena.toString();
                } else if(operaciones.charAt(auxi) == 47){ //división
                    float res = num1/num2;
                    String res1 = String.valueOf(res);
                    StringBuffer auxCadena = new StringBuffer(operaciones);
                    auxCadena.replace( j+1 ,i-1 ,res1);
                    operaciones = auxCadena.toString();
                }

                i=1;
            }else{i++;}
        }
        for(int i=1; i<operaciones.length();){//si es suma o resta
            if(operaciones.charAt(i) == 43  ||  operaciones.charAt(i) == 45) {
                float num1, num2;
                int j=i-1, auxi =i;
                String result = "", numAux1 = "", numAux2 = "";
                i++;
                while(i<operaciones.length() && (Character.isDigit(operaciones.charAt(i)) || operaciones.charAt(i) == 46)) { //mientras sea un numero o punto
                    numAux2+=operaciones.charAt(i);
                    i++;
                }
                num2 = Float.parseFloat(numAux2);
                while(j>=0 && (Character.isDigit(operaciones.charAt(j)) || operaciones.charAt(j) == 46)) { //mientras sea un numero o punto
                    numAux1+=operaciones.charAt(j);
                    j--;
                }
                StringBuilder strb = new StringBuilder(numAux1);
                numAux1 = strb.reverse().toString(); //se da vuelta
                num1 = Float.parseFloat(numAux1);

                if(operaciones.charAt(auxi) == 43){ //multimplicación
                    float res = num1+num2;
                    String res1 = String.valueOf(res);
                    StringBuffer auxCadena = new StringBuffer(operaciones);
                    auxCadena.replace( j+1 ,i-1 ,res1);
                    operaciones = auxCadena.toString();
                } else if(operaciones.charAt(auxi) == 45){ //división
                    float res = num1-num2;
                    String res1 = String.valueOf(res);
                    StringBuffer auxCadena = new StringBuffer(operaciones);
                    auxCadena.replace( j+1 ,i-1 ,res1);
                    operaciones = auxCadena.toString();
                }

                i=1;
            }else{i++;}
        }
        return operaciones;
    }
}