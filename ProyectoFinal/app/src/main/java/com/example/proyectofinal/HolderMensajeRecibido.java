package com.example.proyectofinal;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderMensajeRecibido extends RecyclerView.ViewHolder {
    private TextView mensaje;
    private TextView iniciales;

    public HolderMensajeRecibido(@NonNull View itemView) {
        super(itemView);
        mensaje = (TextView) itemView.findViewById(R.id.mensajeTxt_recibido);
        iniciales = (TextView) itemView.findViewById(R.id.iniciales_recibido);
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
