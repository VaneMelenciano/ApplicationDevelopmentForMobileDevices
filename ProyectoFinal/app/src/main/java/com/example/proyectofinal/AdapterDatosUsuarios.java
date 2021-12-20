package com.example.proyectofinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterDatosUsuarios extends RecyclerView.Adapter<AdapterDatosUsuarios.ViewHolderDatos> implements View.OnClickListener{

    ArrayList<Usuario> listDatos;
    ArrayList<Usuario> listDatosOriginal;
    private View.OnClickListener listener;

    public AdapterDatosUsuarios(ArrayList<Usuario> listDatos) {
        this.listDatos = listDatos;
        listDatosOriginal = new ArrayList<>();
        listDatosOriginal.addAll(listDatos);
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario_lista, null, false);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position).getNombre());
    }

    public void filtrado(String stringBuscar){
        int longitud = stringBuscar.length();
        if(stringBuscar.length() == 0){
            listDatos.clear();
            listDatos.addAll(listDatosOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Usuario> collection = listDatos.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(stringBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listDatos.clear();
                listDatos.addAll(collection);
            }else{
                for (Usuario i : listDatosOriginal) {
                    if(i.getNombre().toLowerCase().contains(stringBuscar.toLowerCase())){
                        listDatos.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato = (TextView) itemView.findViewById(R.id.dato_lista);
        }

        public void asignarDatos(String datos) {
            dato.setText(datos);
        }
    }
}
