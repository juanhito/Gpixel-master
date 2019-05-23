package com.gpixel.Usuario;

import android.app.Application;

import com.gpixel.javabeans.Juego;
import com.gpixel.javabeans.Prueba;

public class Aplicacion extends Application {
    private String usuario;
    private String idusuario;

    public String getUsuario() {
        return usuario;
    }

    public String getIdusuario() {
        return idusuario;
    }


    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    private Juego juego;

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }
}
