package com.gpixel.javabeans;

import android.view.View;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gpixel.R;

import java.util.ArrayList;
public class AdaptadorJuegos extends RecyclerView.Adapter<AdaptadorJuegos.JuegosViewHolder> implements View.OnClickListener {






    /*--------------------------------   ATRIBUTOS   ------------------------------------------*/
    private ArrayList<Juego> datos;
    private View.OnClickListener listener;

    /*--------------------------------    CONSTRUCTOR  ------------------------------------------*/

    public AdaptadorJuegos(ArrayList<Juego> datos) {
        this.datos = datos;
    }



    /*--------------------------------   METODOS ADAPTER  -----------------------------------------*/
    @NonNull
    @Override
    public JuegosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.juego_layout, viewGroup, false);
        v.setOnClickListener(this);
        JuegosViewHolder cvh = new JuegosViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull JuegosViewHolder juegoViewHolder, int i) {
        juegoViewHolder.bindJuego(datos.get(i));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    /*--------------------------------   METODOS LISTENER -----------------------------------------*/
    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    /*--------------------------------   CLASE INTERNA   ------------------------------------------*/

    /**
     *
     */
    public static class JuegosViewHolder extends RecyclerView.ViewHolder {
        /*-------------------------------   ATRIBUTOS   ------------------------------------------*/
        private TextView tvTitulo;
        private TextView tvArtista;

        /*-------------------------------    CONSTRUCTOR  ----------------------------------------*/
        public JuegosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvNombre);
        }
        /*----------------------------------    METODOS   ----------------------------------------*/
        public void bindJuego(Juego jg) {
            tvTitulo.setText(jg.getNombre());
        }
    }

}
