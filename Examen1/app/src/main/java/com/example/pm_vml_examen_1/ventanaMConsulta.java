package com.example.pm_vml_examen_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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

public class ventanaMConsulta extends AppCompatActivity {

    RequestQueue colaSolicitudes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_mconsulta);
        colaSolicitudes = Volley.newRequestQueue(this);
        //Parametros que llegan
        Intent i = getIntent();
        String idC = i.getStringExtra("idCliente");
        String alias = i.getStringExtra("alias");
        String especie = i.getStringExtra("especie");
        String raza = i.getStringExtra("raza");
        String color = i.getStringExtra("color");
        String fNac = i.getStringExtra("fNac");
        //String id1 = i.getStringExtra("id");
        //int id = Integer.parseInt(id1);
        //Toast.makeText(this, id1,Toast.LENGTH_LONG).show();
        //consultarMascota(id1);

        //decarar textview
        TextView idC1 = findViewById(R.id.idC);
        TextView alias1 = findViewById(R.id.alias);
        TextView especie1 = findViewById(R.id.especie);
        TextView raza1 = findViewById(R.id.raza);
        TextView color1 = findViewById(R.id.color);
        TextView fnac1 = findViewById(R.id.fnac);

        /*SpannableString mitextoU = new SpannableString("Mamut chiquito");
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        idC1.setText(mitextoU);*/

        idC1.setText(idC);
        alias1.setText(alias);
        especie1.setText(especie);
        raza1.setText(raza);
        color1.setText(color);
        fnac1.setText(fNac);
    }

    private void consultarMascota(String idMascota) {
        String url = "http://148.204.142.251/isc/api/v1/mascota.php?id=";
        url+=idMascota;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() { //GET para obtener recursos

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject respuesta = response;
                        try {
                            int estado = respuesta.getInt("estado");
                            String mensaje = respuesta.getString("mensaje");

                            if(estado==1){ //si hay registros, el mascota existe
                                String idCliente = respuesta.getString("id_cliente");
                                String alias = respuesta.getString("alias");
                                String especie = respuesta.getString("especie");
                                String raza = respuesta.getString("raza");
                                String color = respuesta.getString("color");
                                String fNac = respuesta.getString("fecha_nacimiento");
                                //MOSTAR LOS DATOS EN LA VENTANA MCONSULTA

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
    }

    public void goVentanaInicio(View view) {
        Intent i1 = new Intent(this, MainActivity.class);
        startActivity(i1);
    }
}