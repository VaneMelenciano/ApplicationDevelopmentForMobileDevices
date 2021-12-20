package com.example.proyectofinal;

public class Mensaje {
    private String mensaje;
    private String iniciales;
    private String boleta;

    public Mensaje() {
    }

    public Mensaje(String mensaje, String iniciales, String boleta) {
        this.mensaje = mensaje;
        this.iniciales = iniciales;
        this.boleta = boleta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIniciales() {
        return iniciales;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    public String getBoleta() {
        return boleta;
    }

    public void setBoleta(String boleta) {
        this.boleta = boleta;
    }
}
