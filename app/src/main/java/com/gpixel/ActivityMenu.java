package com.gpixel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.gpixel.javabeans.plataformas;

import java.util.ArrayList;

public class ActivityMenu extends AppCompatActivity {
    TextView tvNombre;
    TextView tvDescripcion;
    TextView tvFecha;
    String nombre;
    String fecha;
    String descripcion;
    String id;
    TextView tvPlatafor;
    String total="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tvNombre=findViewById(R.id.tvNombre);
        tvDescripcion=findViewById(R.id.tvDesc);
        tvFecha=findViewById(R.id.tvFecha);
        tvPlatafor=findViewById(R.id.tvPlataformas);
        ArrayList<String> datos2;

        nombre= getIntent().getStringExtra("nombre");
        fecha= getIntent().getStringExtra("fecha");
        descripcion=getIntent().getStringExtra("descripcion");
        datos2=getIntent().getStringArrayListExtra("plataformas");
        id=getIntent().getStringExtra("id");


        tvNombre.setText(nombre);
        tvDescripcion.setText(descripcion);
        tvFecha.setText(fecha);
        for(int i=0;i<datos2.size()-1;i++){

            String prueba=datos2.get(i);
            total=prueba+" "+total;

        }

        tvPlatafor.setText(total);

        System.out.println("todo bien todo correcto");
    }
}
