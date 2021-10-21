package com.example.pm_vml_pr01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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

    public void goIngrear(View view) {
        //Intent i1 = new Intent(this, ventanaInicio.class);
        //Intent i1 = new Intent(this, ventanaInicio.class);
        //Toast.makeText(this, "HOLA",Toast.LENGTH_LONG).show();
        //startActivity(i1);
        /*Intent i1 = new Intent(this, ventanaInicio.class);
        EditText correo = findViewById(R.id.correo), contra = findViewById(R.id.contra);
        i1.putExtra("corre", correo);
        i1.putExtra("contra", contra);
        startActivity(i1);*/

        //Verificar que el usuario exista

        EditText correo = (EditText)findViewById(R.id.correo), contra = findViewById(R.id.contra);
        String url = "https://vanemelenciano11.000webhostapp.com/Practica1/cliente.php";
        String usuario = "?correo=\"" + correo.getText() + "\"";
        url+=usuario;
        //Toast.makeText(this, url,Toast.LENGTH_LONG).show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() { //GET para obtener recursos

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject respuesta = response;
                        try {
                            int estado = respuesta.getInt("estado");
                            String mensaje = respuesta.getString("mensaje");

                            if(estado==1){ //si hay registros, el correo existe
                                String contrasenia = respuesta.getString("contrasenia");
                                String contra1 = String.valueOf(contra.getText());

                                if(contra1.equals(contrasenia)){ //contraseña correcta
                                    Toast.makeText(getApplicationContext(), "Contraseña correcta", Toast.LENGTH_LONG).show();
                                    //si es correcta
                                    String correo1= respuesta.getString("correo"), nombre=respuesta.getString("nombre");
                                    mandarDatos(nombre, correo1);
                                }else{ //contraseña incorrecta
                                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta: " + contrasenia + " " + contra1, Toast.LENGTH_LONG).show();
                                }
                            }else{ //no existe el registro
                                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
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
        /*String url = "http://vanemelenciano.byethost9.com/Practica1/cliente.php";
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
        colaSolicitudes.add(jsonObjectRequest)*/

    }

    public void mandarDatos(String nombre, String correo){
        Intent i1 = new Intent(this, ventanaInicio.class);
        i1.putExtra("nombre", nombre);
        i1.putExtra("correo", correo);
        startActivity(i1);
    }

    public void goVentanaRegistrarse(View view) {
        Intent i1 = new Intent(this, ventanaRegistrarse.class);
        //EditText etMensaje1 = findViewById(R.id.correo);
        startActivity(i1);
    }
}