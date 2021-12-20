package com.example.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText et_boleta;
    EditText et_contrasena;
    Button btn_ingresar;
    RequestQueue colaSolicitudes;
    CheckBox cb_sesion;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public static final String nombre_guardado_key = "nombre_guardado";
    public static final String boleta_guardada_key = "boleta_guardada";
    public static final String contrasena_guardada_key = "contrasena_guardada";
    public static final String check_guardado_key = "check_guardado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarElementos();
        if(revisarSesion()) {
            validarUsuario();
        }
        //Toast.makeText(this, "Inicie sesión porfavor", Toast.LENGTH_LONG).show();

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarUsuario();
            }
        });
    }

    private void inicializarElementos(){
        // RequestQueue
        colaSolicitudes = Volley.newRequestQueue(this);
        // EditText
        et_boleta = (EditText) findViewById(R.id.txt_boleta);
        et_contrasena = (EditText)findViewById(R.id.txt_contrasena);
        // Button
        btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
        // Check box
        cb_sesion = findViewById(R.id.checkBox);
        //Others
        preferences = this.getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private boolean revisarSesion(){
        return this.preferences.getBoolean(check_guardado_key,false);
    }

    private void guardarSesion(boolean checked, String nombre, String boleta, String contrasena){
        editor.putBoolean(check_guardado_key,checked);

        if(checked){
            editor.putString(nombre_guardado_key, nombre);
            editor.putString(boleta_guardada_key, boleta);
            editor.putString(contrasena_guardada_key, contrasena);
        }

        editor.apply();

        Toast.makeText(getApplicationContext(), nombre + " "+ boleta,Toast.LENGTH_LONG).show();
    }

    private boolean recuperarUsuario(){
        String nombre = this.preferences.getString(nombre_guardado_key, null);
        String boleta = this.preferences.getString(boleta_guardada_key, null);
        String contrasena = this.preferences.getString(contrasena_guardada_key, null);

        if(nombre == null || boleta == null || contrasena == null) return false;
        else return true;
    }

    private void validarUsuario() {
        String boleta;
        String password;
        boolean mantenerSesion_check;

        if (recuperarUsuario()) {
            boleta = this.preferences.getString(boleta_guardada_key, null);
            password = this.preferences.getString(contrasena_guardada_key, null);
            mantenerSesion_check = this.preferences.getBoolean(check_guardado_key,false);
        }else{
            boleta = et_boleta.getText().toString();
            password = et_contrasena.getText().toString();
            mantenerSesion_check = cb_sesion.isChecked();
        }

        String url = "http://sistemas.upiiz.ipn.mx/isc/alumnos/api/v2/login.php?usuario="+boleta+"&clave="+password; //TODOS LOS REGISTROS
        //http://sistemas.upiiz.ipn.mx/isc/alumnos/api/v2/login.php?usuario=2020670245&clave=2020670245
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject respuesta) {
                        try{
                            int estado = respuesta.getInt("estado");
                            String mensaje = respuesta.getString("mensaje");

                            if(estado==1){//Como programadores significa que si se encotraron registros
                                //Toast.makeText(getApplicationContext(),"Los datos son correctos",Toast.LENGTH_LONG).show();
                                goToListaUsuarios(mantenerSesion_check, respuesta.getString("nombre"), boleta, password);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(),"Existe el siguiente error"+error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        colaSolicitudes.add(jsonObjectRequest);
    }

    public void goToListaUsuarios(boolean check,String nombre, String boleta, String contrasena) {
        Intent intent = new Intent(this, ListaUsuarios.class);

        //Se asigna como emisor al que inicia sesión.
        Usuario.setEmisor(new Usuario(nombre, boleta));

        guardarSesion(check, nombre, boleta, contrasena);

        startActivity(intent);
    }
}