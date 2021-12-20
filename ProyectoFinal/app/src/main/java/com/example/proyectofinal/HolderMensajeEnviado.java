package com.example.proyectofinal;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderMensajeEnviado extends RecyclerView.ViewHolder {
    private TextView mensaje;
    private TextView iniciales;

    public HolderMensajeEnviado(@NonNull View itemView) {
        super(itemView);
        mensaje = (TextView) itemView.findViewById(R.id.mensajeTxt_enviado);
        iniciales = (TextView) itemView.findViewById(R.id.iniciales_enviado);
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getIniciales() {
        return iniciales;
    }

    public void setIniciales(TextView iniciales) {
        this.iniciales = iniciales;
    }
}