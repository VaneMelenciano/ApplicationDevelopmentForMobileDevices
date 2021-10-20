package com.example.pm_vml_pr01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue colaSolicitudes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colaSolicitudes = Volley.newRequestQueue(this);
    }

    public void goInicio(View view) {
        /*Intent i1 = new Intent(this, ventanaInicio.class);
        EditText correo = findViewById(R.id.correo), contra = findViewById(R.id.contra);
        i1.putExtra("corre", correo);
        i1.putExtra("contra", contra);
        startActivity(i1);*/
        //Verificar que el usuario exista
        /*String urlLocal = "http://192.168.0.4/Moviles/Practica1/cliente.php?accion=read";
        String url = "http://vanemelenciano.byethost9.com/Practica1/cliente.php?accion=read";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() { //GET para obtener recursos

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject respuesta = response;
                        try {
                            int estado = respuesta.getInt("estado");
                            String mensaje = respuesta.getString("mensaje");

                            if(estado==1){ //si hay registros
                                JSONArray clientes =  respuesta.getJSONArray("clientes");

                                for(int i=0; i< clientes.length(); i++){
                                    JSONObject cliente = clientes.getJSONObject(i);
                                    Toast.makeText(getApplicationContext(), cliente.getString("nombre"), Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(getApplicationContext(), "Todo un exito la solicitud", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "Existe el siguiente error: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        colaSolicitudes.add(jsonObjectRequest);*/
        String url = "http://vanemelenciano.byethost9.com/Practica1/cliente.php";
        String urlLocal = "http://192.168.0.4/Moviles/diagnostico/php/CRUD-mascota.php?accion=read";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() { //GET para obtener recursos

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject respuesta = response;
                        try {
                            int estado = respuesta.getInt("estado");
                            String mensaje = respuesta.getString("mensaje");

                            if(estado==1){ //si hay registros
                                JSONArray mascotas =  respuesta.getJSONArray("mascotas");

                                for(int i=0; i< mascotas.length(); i++){
                                    JSONObject mascota = mascotas.getJSONObject(i);
                                    Toast.makeText(getApplicationContext(), mascota.getString("alias"), Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(getApplicationContext(), "Todo un exito la solicitud", Toast.LENGTH_LONG).show();
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

    public void goRegistrarse(View view) {
        Intent i1 = new Intent(this, ventanaRegistrarse.class);
        //EditText etMensaje1 = findViewById(R.id.correo);
        startActivity(i1);
    }
}