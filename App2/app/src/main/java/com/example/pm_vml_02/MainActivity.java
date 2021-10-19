package com.example.pm_vml_02;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button btnFactorial, btnTerminar;
    EditText numText;
    TextView resultadoText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFactorial = findViewById(R.id.btnFactorial);
        btnTerminar = findViewById(R.id.btnTerminar);
        numText = findViewById(R.id.editTextDigite);
        resultadoText = findViewById(R.id.textViewRes);
        btnFactorial.setOnClickListener(this);
        btnTerminar.setOnClickListener(this);
    }
    public void onClick(@NonNull View v){
        double num;
        num = Double.parseDouble(numText.getText().toString());
        switch (v.getId()){
            case R.id.btnFactorial://Calcular el factorial
                int res = 1;
                for(int i=1;i<=num;i++) {
                    res *= i;
                }
                resultadoText.setText("Resultado: "+res);
                Toast.makeText(this ,"Diste click sobre el boton Factorial: ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnTerminar:
                finish();
                Toast.makeText(this ,"Diste click sobre el boton salir:",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

