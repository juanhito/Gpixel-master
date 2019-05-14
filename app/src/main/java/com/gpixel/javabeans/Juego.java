package com.gpixel.javabeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Juego implements Serializable {
    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("id")
    @Expose
    private String id;

    public Juego(String nombre, String id) {
        this.nombre = nombre;
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
