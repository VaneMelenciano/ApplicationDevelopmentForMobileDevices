package com.example.proyectofinal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ListaUsuarios extends AppCompatActivity implements SearchView.OnQueryTextListener{
    //Lista de usuarios
    private ArrayList<Usuario> listaUsuarios;

    //mostrar usurios
    ProgressDialog progressDialog;
    JSONArray usuarios;
    RequestQueue colaSolicitudes;

    //Views y adapter
    SearchView searchView;
    RecyclerView recycler;
    Button btn_sesion;
    Button btn_notificacion;
    AdapterDatosUsuarios adapter;

    ////Preferencias de usuario////
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        //////Preferencias de usuario//////
        preferences = this.getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();
        btn_sesion = (Button) findViewById(R.id.btn_cs);

        listaUsuarios = new ArrayList<Usuario>();

        btn_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean(MainActivity.check_guardado_key,false);
                editor.putString(MainActivity.boleta_guardada_key,null);
                editor.putString(MainActivity.nombre_guardado_key,null);
                editor.putString(MainActivity.contrasena_guardada_key,null);
                editor.apply();
                Toast.makeText(getApplicationContext(),"La sesión fue cerrada",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);  //?
                startActivity(intent);  //?
            }
        });

        //Buscar
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

        //////////////////////////////

        //ENVIAR NOTIFICACION A TODOS LOS MIEMBROS ***********************+
        btn_notificacion = (Button) findViewById(R.id.btn_notificacion);
        btn_notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), enviar_Notificacion.class);  //?
                startActivity(intent);  //?
            }
        });
        //////////////////////////////////////////////********************

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //Mostrar usuarios en recycler

        colaSolicitudes = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Usuarios");
        progressDialog.setMessage("Conectando....");

        recuperarUsuarios();
        //usuariosProvisional();

    }

    //lista estática
    public void usuariosProvisional(){
        listaUsuarios = new ArrayList<Usuario>();

        listaUsuarios.add(new Usuario("Adrien", "12345"));
        listaUsuarios.add(new Usuario("Amelia", "12354"));
        listaUsuarios.add(new Usuario("Angelina", "11111"));
        listaUsuarios.add(new Usuario("Ares", "22222"));
        listaUsuarios.add(new Usuario("Bonnie", "33333"));
        listaUsuarios.add(new Usuario("Bull", "44444"));
        listaUsuarios.add(new Usuario("Busy Guy", "55555"));
        listaUsuarios.add(new Usuario("Arcturus", "54321"));
        listaUsuarios.add( new Usuario("Emporio", "53421"));
        listaUsuarios.add( new Usuario("Vanessa Melenciano", "70081"));

        adapter = new AdapterDatosUsuarios(listaUsuarios);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Chat.class);

                Usuario.setReceptor(listaUsuarios.get(recycler.getChildAdapterPosition(view)));

                startActivity(intent);
            }
        });
        recycler.setAdapter(adapter);

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recycler.addItemDecoration(divider);
    }

    public void recuperarUsuarios(){
        //ArrayList<String> usuariosNombre = new ArrayList<String>();
        progressDialog.show();
        String url = "http://sistemas.upiiz.ipn.mx/isc/alumnos/api/v2/alumnos.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() { //GET para obtener recursos

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        JSONObject respuesta = response;
                        try {
                            //Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_LONG).show();
                            int estado = respuesta.getInt("estado");
                            String mensaje = respuesta.getString("mensaje");

                            if(estado==1){ //si hay registros
                                //Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_LONG).show();
                                usuarios =  respuesta.getJSONArray("alumnos");

                                for(int i=0; i< usuarios.length(); i++){
                                    JSONObject usuario = usuarios.getJSONObject(i);
                                    //Toast.makeText(getApplicationContext(),"6", Toast.LENGTH_LONG).show();

                                    listaUsuarios.add(new Usuario(usuario.getString("nombre"), usuario.getString("boleta")));

                                    //Toast.makeText(getApplicationContext(), usuariosNombre.get(i), Toast.LENGTH_LONG).show();
                                }
                                //Poner los datos en el recycler

                                adapter = new AdapterDatosUsuarios(listaUsuarios);
                                adapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Intent intent = new Intent(getApplicationContext(), Chat.class);

                                        Usuario.setReceptor(listaUsuarios.get(recycler.getChildAdapterPosition(view)));

                                        startActivity(intent);
                                    }
                                });
                                recycler.setAdapter(adapter);

                                RecyclerView.ItemDecoration divider = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                                recycler.addItemDecoration(divider);
                                /////
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
        //return usuariosNombre;
    }

    //para no dejar que de para atrás en el telefono
    @Override public void onBackPressed() { }

    //Métodos de SearchView
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }

}