package com.example.proyectofinal;

public class Usuario {
    private String nombre;
    private String boleta;

    private static Usuario emisor;
    private static Usuario receptor;

    public Usuario (String nombre, String boleta){
        this.nombre = nombre;
        this.boleta = boleta;
    }

    public String getNombre() {return nombre;}
    public String getBoleta() {return boleta;}

    public static Usuario getEmisor() {
        return emisor;
    }

    public static void setEmisor(Usuario emisor) {
        Usuario.emisor = emisor;
    }

    public static Usuario getReceptor() {
        return receptor;
    }

    public static void setReceptor(Usuario receptor) {
        Usuario.receptor = receptor;
    }
}
