package com.example.pm_vml_examen_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ventanaConsulta extends AppCompatActivity {

    RequestQueue colaSolicitudes;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_consulta);
        colaSolicitudes = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Macota");
        progressDialog.setMessage("Buscando....");
    }

    public void goConsultar(View view) {
        progressDialog.show();
        EditText id1 = (EditText)findViewById(R.id.id);

        if(id1.getText().toString().isEmpty()){
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Por favor escriba un Id de una Mascota", Toast.LENGTH_LONG).show();
        }
        else {
            String id =  String.valueOf(id1.getText());
            String url = "http://148.204.142.251/isc/api/v1/mascota.php?id=";
            url += id;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() { //GET para obtener recursos

                        @Override
                        public void onResponse(JSONObject response) {
                            JSONObject respuesta = response;
                            try {
                                int estado = respuesta.getInt("estado");
                                String mensaje = respuesta.getString("mensaje");

                                if (estado == 1) { //si hay registros, el mascota existe
                                    String idCliente = respuesta.getString("id_cliente");
                                    String alias = respuesta.getString("alias");
                                    String especie = respuesta.getString("especie");
                                    String raza = respuesta.getString("raza");
                                    String color = respuesta.getString("color");
                                    String fNac = respuesta.getString("fecha_nacimiento");
                                    //MANDAR LOS DATOS EN LA VENTANA MCONSULTA
                                    Intent i1 = new Intent(getApplicationContext(), ventanaMConsulta.class);
                                    i1.putExtra("idCliente", idCliente);
                                    i1.putExtra("alias", alias);
                                    i1.putExtra("especie", especie);
                                    i1.putExtra("raza", raza);
                                    i1.putExtra("color", color);
                                    i1.putExtra("fNac", fNac);
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Registro encontrado", Toast.LENGTH_LONG).show();
                                    startActivity(i1);
                                } else { //no existe el registro
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "No existe mascota con ese id", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Existe el siguiente error: " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
            colaSolicitudes.add(jsonObjectRequest);
        }
    }
}