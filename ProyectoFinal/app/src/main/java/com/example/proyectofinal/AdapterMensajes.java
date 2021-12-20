package com.example.proyectofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterMensajes extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Mensaje> listMensajes = new ArrayList<>();
    private Context c;

    private static final int layout_mensaje_emisor = 1;
    private static final int layout_mensaje_receptor = 2;

    public AdapterMensajes(Context c) {
        this.c = c;
    }

    public void addMensaje(Mensaje m){
        listMensajes.add(m);
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;

        if((listMensajes.get(position).getBoleta()).equals(Usuario.getEmisor().getBoleta())){
            viewType = layout_mensaje_emisor;
        }else{
            viewType = layout_mensaje_receptor;
        }

        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //aquí es donde se elije qué cardview se usa. Se deberá modificar después.
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;

        if(viewType == layout_mensaje_emisor){
            View v = LayoutInflater.from(c).inflate(R.layout.mensaje_enviado, parent, false);
            return new HolderMensajeEnviado(v);
        }
        else{
            View v = LayoutInflater.from(c).inflate(R.layout.mensaje_recibido, parent, false);
            return new HolderMensajeRecibido(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == layout_mensaje_emisor){
            HolderMensajeEnviado holder_emisor = (HolderMensajeEnviado) holder;
            holder_emisor.getIniciales().setText(listMensajes.get(position).getIniciales());
            holder_emisor.getMensaje().setText(listMensajes.get(position).getMensaje());
        }
        else{
            HolderMensajeRecibido holder_recibido = (HolderMensajeRecibido) holder;
            holder_recibido.getIniciales().setText(listMensajes.get(position).getIniciales());
            holder_recibido.getMensaje().setText(listMensajes.get(position).getMensaje());
        }
    }

    @Override
    public int getItemCount() {
        return listMensajes.size();
    }
}
