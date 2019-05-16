package com.gpixel.javabeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Juego implements Serializable {
    @SerializedName("name")
    @Expose
    private String nombre;
    @SerializedName("deck")
    @Expose
    private String descripcion;

    @SerializedName("guid")
    @Expose
    private String id;
    @SerializedName("original_release_date")
    @Expose
    private String fecha;



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

    public Juego(String nombre, String descripcion, String id, String fecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id = id;
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
