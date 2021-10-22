package com.example.pm_vml_examen_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import org.json.JSONException;
import org.json.JSONObject;

public class ventanaAlta extends AppCompatActivity {
    RequestQueue colaSolicitudes;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_alta);
        colaSolicitudes = Volley.newRequestQueue(this);

        colaSolicitudes = Volley.newRequestQueue(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Macotas");
        progressDialog.setMessage("Conectando....");
    }

    public void goAgregar(View view) {
        progressDialog.show();
        //guardar datos agregardor
        EditText idC = (EditText) findViewById(R.id.idC), alias = (EditText) findViewById(R.id.alias);
        EditText especie = (EditText) findViewById(R.id.especie), raza = (EditText) findViewById(R.id.raza);
        EditText color = (EditText) findViewById(R.id.color), fNac = (EditText) findViewById(R.id.fnac);
        //agregar nuevo registro
        String url = "http://148.204.142.251/isc/api/v1/mascota.php";
        JSONObject mascotaJSON = new JSONObject();
        try {
            mascotaJSON.put("id_cliente", idC.getText());
            mascotaJSON.put("alias", alias.getText());
            mascotaJSON.put("especie", especie.getText());
            mascotaJSON.put("raza", raza.getText());
            mascotaJSON.put("color", color.getText());
            mascotaJSON.put("fecha_nacimiento", fNac.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, mascotaJSON, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject respuesta1) {
                        JSONObject respuesta = respuesta1;
                        try {
                            int estado = respuesta.getInt("estado");
                            String mensaje = respuesta.getString("mensaje");

                            if(estado==1){ //el registro se creó
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();

                            }else{ //hubo un error //"Ocurrio un error desconocido"
                                if(mensaje.equals("Ocurrio un error desconocido")){
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Por favor ponga la fecha con el formato indicado: aaa-mm-dd", Toast.LENGTH_LONG).show();
                                }else{ //Faltan parametros
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Por favor llene todos los campos", Toast.LENGTH_LONG).show();
                                }
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

    public void goVentanaInicio(View view) {
        Intent i1 = new Intent(this, MainActivity.class);
        startActivity(i1);
    }
}