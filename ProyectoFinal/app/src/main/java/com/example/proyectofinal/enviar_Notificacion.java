package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class enviar_Notificacion extends AppCompatActivity {

    Button btn_enviar;
    EditText titulo1, mensaje1;
    String titulo, mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_notificacion);

        //ENVIAR
        titulo1 = (EditText) findViewById(R.id.txt_titulo);
        mensaje1 = (EditText) findViewById(R.id.txt_mensaje);
        titulo = titulo1.getText().toString();
        mensaje = mensaje1.getText().toString();

        btn_enviar = (Button) findViewById(R.id.btn_enviar);

        FirebaseMessaging.getInstance().subscribeToTopic("nuevo").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //Toast.makeText(enviar_Notificacion.this, "Registrado", Toast.LENGTH_SHORT).show();
            }
        });


        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(enviar_Notificacion.this,"1",Toast.LENGTH_SHORT).show();
                //Toast.makeText(enviar_Notificacion.this,titulo1.getText().toString() + "  "+mensaje1.getText().toString(),Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), ListaUsuarios.class);  //?
                //startActivity(intent);  //?

                llamartopico();
            }
        });

        //REGRESAR
        ImageView regresar;
        regresar = (ImageView) findViewById(R.id.imageView);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaUsuarios.class);  //?
                startActivity(intent);
            }
        });
    }

    private void llamartopico() {
        RequestQueue myrequest = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();

        try {

            //String url_foto = "";

            json.put("to", "/topics/" + "enviaratodos");
            JSONObject notificacion = new JSONObject();
            notificacion.put("titulo", titulo1.getText().toString());
            //notificacion.put("foto", url_foto);
            notificacion.put("detalle", mensaje1.getText().toString());

            json.put("data", notificacion);
            String URL = "https://fcm.googleapis.com/fcm/send";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, json, null, null) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=AAAADZtfwyU:APA91bF-kB3Vp1Dr05KE52Jl7VNv3rpg8vMJ4WOjEwqfXE8A1AEL6pteaLembFjZDsPa3ecL2hkiFZDdg5k59ArnWe7F8mgBHAxyCGpvtKRMKxkzjIO3vtHm6aNBQmEK8kSXUr0ht1xe");

                    return header;

                }
            };
            myrequest.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}