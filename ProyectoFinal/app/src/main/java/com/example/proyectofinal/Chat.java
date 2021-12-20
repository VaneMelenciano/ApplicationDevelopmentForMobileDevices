package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chat extends AppCompatActivity{
    TextView nombreTV;
    ImageView regresarIV;
    RecyclerView rvMensajes;
    ImageView enviarBtn;
    EditText mensajeET;

    private AdapterMensajes adapter;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private Usuario emisor;
    private Usuario receptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        emisor = Usuario.getEmisor();
        receptor = Usuario.getReceptor();

        Toast.makeText(getApplicationContext(), "Emisor: " + emisor.getNombre() + " Receptor: " + receptor.getNombre(), Toast.LENGTH_SHORT).show();

        nombreTV = (TextView) findViewById(R.id.nombreU);
        nombreTV.setText(receptor.getNombre());
        regresarIV = (ImageView) findViewById(R.id.imageView);
        regresarIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaUsuarios.class);  //?
                startActivity(intent);
            }
        });
        rvMensajes = (RecyclerView) findViewById(R.id.RVMensajes);
        enviarBtn = (ImageView) findViewById(R.id.btn_enviar);
        mensajeET = (EditText) findViewById(R.id.mensaje_ET);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(getChatKey(emisor.getBoleta(), receptor.getBoleta())); //sala de chat

        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);
        enviarBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //aquí se crea el mensaje.
                databaseReference.push().setValue(new Mensaje(mensajeET.getText().toString(), obtenerIniciales(emisor.getNombre()), emisor.getBoleta()));
                mensajeET.setText("");
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                //setScrollBar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Mensaje m = snapshot.getValue(Mensaje.class);
                adapter.addMensaje(m);
                adapter.notifyDataSetChanged();
                rvMensajes.smoothScrollToPosition(rvMensajes.getAdapter().getItemCount());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override public void onBackPressed() { }

    public String obtenerIniciales(String nombre){
        int index = nombre.indexOf(' ') +1;
        return nombre.substring(0, 1) + nombre.substring(index, index+1);
    }

    private String getChatKey(String emisor, String receptor){
        String chatkey = "";
        int b1 = Integer.parseInt (emisor), b2 = Integer.parseInt(receptor);

        if(b1<b2){ //primero b1
            chatkey = String.valueOf(b1) + String.valueOf(b2);
        }else{ //primero b2 (si b2 es menor o igual a b1)
            chatkey = String.valueOf(b2) + String.valueOf(b1);
        }
        return chatkey;
    }
}