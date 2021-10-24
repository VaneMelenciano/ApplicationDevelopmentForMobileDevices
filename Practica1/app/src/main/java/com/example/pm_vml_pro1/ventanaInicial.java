package com.example.pm_vml_pro1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pm_vml_pro1.databinding.ActivityVentanaInicialBinding;

public class ventanaInicial extends AppCompatActivity {

    TextView nombre1;
    TextView correo1;
    String correo;
    String nombre;
    private AppBarConfiguration mAppBarConfiguration;
private ActivityVentanaInicialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        //Recibir datos
        correo = i.getStringExtra("correo");
        nombre = i.getStringExtra("nombre");
        Toast.makeText(this, nombre + "  " + correo,Toast.LENGTH_LONG).show();

        //declarar textview
        nombre1 = findViewById(R.id.mostrarNombre);
        correo1 = findViewById(R.id.mostrarCorreo);

        //mandar datos a los textview
        //nombre1.setText(nombre);
        //correo1.setText(correo);
        //t.setText("holaaaa");


        //Toast.makeText(this, "HOLA",Toast.LENGTH_LONG).show();
     binding = ActivityVentanaInicialBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

     setSupportActionBar(binding.appBarVentanaInicial.toolbar);


        //btn de mensajes que borré
        /*binding.appBarVentanaInicial.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_reportes)//, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_ventana_inicial);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ventana_inicial, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_ventana_inicial);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}