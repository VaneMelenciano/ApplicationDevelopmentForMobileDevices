package com.example.pm_vml_pro1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.ArrayList;

public class reportes extends AppCompatActivity {
    private ListView list;
    private ArrayList<String> mascotasServidor;
    private ArrayList<Integer> mascotasID;
    RequestQueue colaSolicitudes;
    ProgressDialog progressDialog;
    JSONArray mascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);
        colaSolicitudes = Volley.newRequestQueue(this);
        list = (ListView) findViewById(R.id.listaMascotas);
        //Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_LONG).show();
        mascotasServidor = new ArrayList();
        mascotasID = new ArrayList();
        /*progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Macotas");
        progressDialog.setMessage("Conectando....");*/
        getGet();

    }

    private void getGet(){
        //progressDialog.show();
        String url = "http://148.204.142.251/isc/api/v1/mascota.php?accion=read";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() { //GET para obtener recursos

                    @Override
                    public void onResponse(JSONObject response) {
                        //progressDialog.dismiss();
                        JSONObject respuesta = response;
                        try {
                            //Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_LONG).show();
                            int estado = respuesta.getInt("estado");
                            String mensaje = respuesta.getString("mensaje");

                            if(estado==1){ //si hay registros
                                //Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_LONG).show();
                                mascotas =  respuesta.getJSONArray("mascotas");

                                for(int i=0; i< mascotas.length(); i++){
                                    JSONObject mascota = mascotas.getJSONObject(i);
                                    //Toast.makeText(getApplicationContext(),"rrr", Toast.LENGTH_LONG).show();

                                    mascotasServidor.add(mascota.getString("alias"));
                                    mascotasID.add(Integer.valueOf(mascota.getString("id")));
                                    //Toast.makeText(getApplicationContext(), mascota.getString("alias"), Toast.LENGTH_LONG).show();
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mascotasServidor);
                                list.setAdapter(adapter);
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
                        //progressDialog.dismiss();
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "Existe el siguiente error: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        colaSolicitudes.add(jsonObjectRequest);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int idM = mascotasID.get(position);
                Toast.makeText(reportes.this, "Has pulsado: "+ mascotasServidor.get(position) + "   " + position+"   " +idM, Toast.LENGTH_LONG).show();

                try {
                    JSONObject mascota = mascotas.getJSONObject(position);
                    //Toast.makeText(reportes.this, mascota.getString("alias") + mascota.getString("especie"), Toast.LENGTH_LONG).show();
                    goDetalle(mascota.getString("alias"), mascota.getString("especie"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //Toast.makeText(reportes.this, idM, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goDetalle(String alias, String especie){
        Toast.makeText(reportes.this, alias + "  " + especie , Toast.LENGTH_LONG).show();
        Intent i1 = new Intent(this, detalles.class);
        i1.putExtra("alias", alias);
        i1.putExtra("especie", especie);
        //i1.putExtra("raza", raza);
        startActivity(i1);
    }


    public void btnRegresar(View view) {
        Intent i1 = new Intent(this, ventanaInicial.class);
        startActivity(i1);
    }
}