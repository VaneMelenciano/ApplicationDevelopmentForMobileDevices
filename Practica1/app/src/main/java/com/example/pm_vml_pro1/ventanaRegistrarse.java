package com.example.pm_vml_pro1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    String url = "https://vanemelenciano11.000webhostapp.com/Practica1/cliente.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_registrsrse);
        colaSolicitudes = Volley.newRequestQueue(this);
    }

    public void goRegistrarse(View view) {
        checked = (CheckBox) findViewById(R.id.checkTerminos);
        if(checked.isChecked()) { //si aceptó terminos
            //agregar registro
            EditText correo = (EditText) findViewById(R.id.correo), contra = findViewById(R.id.contra);
            EditText nombre = (EditText) findViewById(R.id.correo);
            verificarCorreoUnico(correo, nombre, contra);
        }
        else {
            Toast.makeText(this, "Aceptar terminos", Toast.LENGTH_LONG).show(); // no está seleccionado
        }
    }
    public void verificarCorreoUnico(EditText correo, EditText nombre, EditText contra){
        //verifica si el correo ya existe, sino lo agrega
        String usuario = url + "?correo=\"" + correo.getText() + "\"";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, usuario, null, new Response.Listener<JSONObject>() { //GET para obtener recursos

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject respuesta = response;
                        try {
                            int estado = respuesta.getInt("estado");
                            if(estado==1){ //El correo ya existe
                                Toast.makeText(getApplicationContext(), "El correo ya está registrado", Toast.LENGTH_LONG).show();

                            }else{ //no existe el correo, lo agrega
                                Toast.makeText(getApplicationContext(), "No Existe", Toast.LENGTH_LONG).show();
                                JSONObject clienteJSON = new JSONObject();
                                try {
                                    clienteJSON.put("nombre", nombre.getText());
                                    clienteJSON.put("correo", correo.getText());
                                    clienteJSON.put("contrasenia", contra.getText());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
    public void mandarDatos(String nombre, String correo){
        Intent i1 = new Intent(this, ventanaInicial.class);
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