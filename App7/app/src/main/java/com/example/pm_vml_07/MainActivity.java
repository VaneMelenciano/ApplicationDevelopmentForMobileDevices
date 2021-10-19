package com.example.pm_vml_07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue colaSolicitudes;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colaSolicitudes = Volley.newRequestQueue(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Macotas");
        progressDialog.setMessage("Conectando....");
    }

    public void goGET(View view) { //solicitar registros
        progressDialog.show();
        String url = "http://148.204.142.251/isc/api/v1/mascota.php?accion=read";
        String urlLocal = "http://192.168.0.4/Moviles/diagnostico/php/CRUD-mascota.php?accion=read";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() { //GET para obtener recursos

                @Override
                public void onResponse(JSONObject response) {
                    progressDialog.dismiss();
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
                    progressDialog.dismiss();
                    // TODO: Handle error
                    Toast.makeText(getApplicationContext(), "Existe el siguiente error: " + error.toString(), Toast.LENGTH_LONG).show();
                }
            });
        colaSolicitudes.add(jsonObjectRequest);
    }

    public void resquestString(View view){
        String url = "http://148.204.142.251/isc/api/v1/mascota.php";
        JSONObject mascotaJSON = new JSONObject();
        //PENDIENTE, SE VE LA SIG. CLASE
        //Cómo recuperar los datos que estamos mandando por POST del servidor
    }

    public void requestPOST(View view) { //agregar registros
        progressDialog.show();
        JSONObject mascotaJSON = new JSONObject();
        try {
            mascotaJSON.put("id_cliente", "004");
            mascotaJSON.put("alias", "Tilin");
            mascotaJSON.put("especie", "Perro");
            mascotaJSON.put("raza", "Pastor Aleman");
            mascotaJSON.put("color", "Gris claro");
            mascotaJSON.put("fecha_nacimiento", "2014-08-27");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //como es por POST no lleva parametros
        String url = "http://148.204.142.251/isc/api/v1/mascota.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, mascotaJSON, new Response.Listener<JSONObject>() { //GET para obtener recursos

                    @Override
                    public void onResponse(JSONObject respuesta) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Respuesta del servidor:  " + respuesta, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "Existe el siguiente error: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        colaSolicitudes.add(jsonObjectRequest);
    }
}