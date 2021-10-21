package com.example.pm_vml_pr01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ventanaRegistrarse extends AppCompatActivity {

    private CheckBox checked;
    RequestQueue colaSolicitudes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registrarse);
        colaSolicitudes = Volley.newRequestQueue(this);


        //Intent i = getIntent();
        //String correoo = i.getStringExtra("correo");
        //Toast.makeText(this, "HOLA",Toast.LENGTH_LONG).show();


    }
    public void goRegistrarse(View view) {
        /*Intent i1 = new Intent(this, ventanaInicio.class);
        EditText correo = findViewById(R.id.correo), contra = findViewById(R.id.contra), nombre = findViewById(R.id.nombre);
        i1.putExtra("corre", correo);
        i1.putExtra("contra", contra);
        i1.putExtra("nombre", nombre);
        startActivity(i1);*/
        checked = (CheckBox) findViewById(R.id.checkTerminos);
        if(checked.isChecked()){ //si aceptó terminos
            //Intent i1 = new Intent(this, ventanaInicio.class);
            //Toast.makeText(this, "HOLA",Toast.LENGTH_LONG).show();
            //startActivity(i1);
            //Toast.makeText(this, "Bien",Toast.LENGTH_LONG).show(); //está seleccionado
            //agregar registro
            EditText correo = (EditText)findViewById(R.id.correo), contra = findViewById(R.id.contra);
            EditText nombre = (EditText)findViewById(R.id.correo);
            //String nombre1 = nombre.toString();
            //Editable correo11 = correo.getText(), nombre1 = nombre.
            //String contra1=contra.toString();
            JSONObject clienteJSON = new JSONObject();
            try {
                clienteJSON.put("nombre", nombre.getText());
                clienteJSON.put("correo", correo.getText());
                clienteJSON.put("contrasenia", contra.getText());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = "https://vanemelenciano11.000webhostapp.com/Practica1/cliente.php";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, clienteJSON, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject respuesta) {
                            Toast.makeText(getApplicationContext(), "Respuesta del servidor:  " + respuesta, Toast.LENGTH_LONG).show();
                            mandarDatos(String.valueOf(nombre.getText()), String.valueOf(correo.getText()));
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            Toast.makeText(getApplicationContext(), "Existe el siguiente error: " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
            colaSolicitudes.add(jsonObjectRequest);

        }
        else {
            Toast.makeText(this, "Mal", Toast.LENGTH_LONG).show(); // no está seleccionado
        }
    }
    public void mandarDatos(String nombre, String correo){
        Intent i1 = new Intent(this, ventanaInicio.class);
        i1.putExtra("nombre", nombre);
        i1.putExtra("correo", correo);
        startActivity(i1);
    }

    public void goVentanaIngresar(View view) {
        Intent i1 = new Intent(this, MainActivity.class);
        //EditText etMensaje1 = findViewById(R.id.correo);
        startActivity(i1);
    }
}