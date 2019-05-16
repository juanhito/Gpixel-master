package com.gpixel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityMenu extends AppCompatActivity {
    TextView tvNombre;
    TextView tvDescripcion;
    TextView tvFecha;
    String nombre;
    String fecha;
    String descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tvNombre=findViewById(R.id.tvgenero);
        tvDescripcion=findViewById(R.id.sinopsis);
        tvFecha=findViewById(R.id.genero);
        nombre= getIntent().getStringExtra("nombre");
        fecha= getIntent().getStringExtra("fecha");
        descripcion=getIntent().getStringExtra("descripcion");


        tvNombre.setText(nombre);
        tvDescripcion.setText(descripcion);
        tvFecha.setText(fecha);
    }
}
