package com.gpixel;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.gpixel.Retrofit.APIRestService;
import com.gpixel.Retrofit.RetrofitClient;
import com.gpixel.javabeans.AdaptadorJuegos;
import com.gpixel.javabeans.Juego;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PerfilJuego extends AppCompatActivity {

    private RecyclerView rvMain;
    private AdaptadorJuegos adaptador;
    private LinearLayoutManager llm;

    private ArrayList<Juego> datos;
    Juego juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_juego);

        rvMain = findViewById(R.id.rvJuegos);
        datos=new ArrayList<Juego>();
        adaptador=new AdaptadorJuegos(datos);
        llm = new LinearLayoutManager(this);

        rvMain.setItemAnimator(new DefaultItemAnimator());
        rvMain.setAdapter(adaptador);
        rvMain.setLayoutManager(llm);

        /*CARGAR DATOS*/
        cargarDatos();
        //consumirWS();
        //prueba();

    }
    public void consumirWS(){
        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = r.create(APIRestService.class);
        Call<Juego> call = ars.obtenerJuego();

        call.enqueue(new Callback<Juego>() {

            @Override
            public void onResponse(Call<Juego> call, Response<Juego> response) {
                if(response.isSuccessful()) {
                    Juego juego = response.body();
                    datos.add(juego);
                    adaptador.notifyDataSetChanged();

                } else {
                    System.out.print("Eres un inutil");
                }
            }

            @Override
            public void onFailure(Call<Juego> call, Throwable t) {
                System.out.print("Eres un inutil");

            }
        });
    }


   private void prueba(){
        Juego J1=new Juego("pureba 1","1");
        Juego j2=new Juego("prueba 2","2");
        Juego j3=new Juego("prueba 3","3");
        datos.add(J1);
        datos.add(j2);
        datos.add(j3);
       adaptador = new AdaptadorJuegos(datos);
       rvMain.setAdapter(adaptador);
          }

    private void cargarDatos( ) {


       if (isNetworkAvailable()) {
            Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
            APIRestService ars = r.create(APIRestService.class);
            Call<ArrayList<Juego>> call = ars.obtenerCds();

            call.enqueue(new Callback<ArrayList<Juego>>() {

                @Override
               public void onResponse(Call<ArrayList<Juego>> call, Response<ArrayList<Juego>> response) {
                    if (!response.isSuccessful()) {
                        Log.i("Resultado: ", "Error" + response.code());
                    } else {
                        datos = response.body();
                        if (datos != null) {
                            adaptador = new AdaptadorJuegos(datos);
                            rvMain.setAdapter(adaptador);
                            adaptador.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                       }

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Juego>> call, Throwable t) {
                    Log.e("error", t.toString());
                }
            });
        } else {
            Toast.makeText(this, "Error de conexión", Toast.LENGTH_LONG).show();
        }
    }


    private boolean isNetworkAvailable() {
        boolean isAvailable=false;
        //Gestor de conectividad
        ConnectivityManager manager = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        //Objeto que recupera la información de la red
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //Si la información de red no es nula y estamos conectados
        //la red está disponible
        if(networkInfo!=null && networkInfo.isConnected()){
            isAvailable=true;
        }
        return isAvailable;

    }
}
