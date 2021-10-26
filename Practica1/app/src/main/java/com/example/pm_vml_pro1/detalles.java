package com.example.pm_vml_pro1;

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
import org.w3c.dom.Text;

public class detalles extends AppCompatActivity {
    ProgressDialog progressDialog;
    RequestQueue colaSolicitudes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        Intent i = getIntent();
        //Recibir datos


        String id = i.getStringExtra("idM");
        //Toast.makeText(this, id , Toast.LENGTH_LONG).show();
        //String alias = i.getStringExtra("alias");
        //String especie = i.getStringExtra("especie");
        colaSolicitudes = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Macota");
        progressDialog.setMessage("Buscando....");

        goGet(Integer.parseInt(id));
        //alias1.setText(alias); especie1.setText(especie);

    }

    public void goGet(int id){

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
                                //String idCliente = respuesta.getString("id_cliente");
                                String alias = respuesta.getString("alias");
                                String especie = respuesta.getString("especie");
                                String raza = respuesta.getString("raza");

                                //String color = respuesta.getString("color");
                                //String fNac = respuesta.getString("fecha_nacimiento");
                                progressDialog.dismiss();
                                mostrarDatos(alias, raza, especie);
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
    public void mostrarDatos(String alias, String raza, String especie){
        TextView alias1 = (TextView) findViewById(R.id.alias);
        TextView raza1 = (TextView) findViewById(R.id.raza);
        TextView especie1 = (TextView) findViewById(R.id.especie);

        alias1.setText(alias);
        raza1.setText(raza);
        especie1.setText(especie);
    }
    public void btnRegresar(View view) {
        Intent i1 = new Intent(this, reportes.class);
        startActivity(i1);
    }
    @Override public void onBackPressed() { }
}