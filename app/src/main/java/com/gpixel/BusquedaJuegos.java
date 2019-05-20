package com.gpixel;

import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gpixel.Retrofit.APIRestService;
import com.gpixel.Retrofit.RetrofitClient;
import com.gpixel.javabeans.AdaptadorJuegos;
import com.gpixel.javabeans.Juego;
import com.gpixel.javabeans.Prueba;
import com.gpixel.javabeans.plataformas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BusquedaJuegos extends AppCompatActivity {
    EditText etNombre;
    TextView tvNombre;
    TextView tvFecha;
    TextView tvplataformas;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_juegos);
        etNombre=findViewById(R.id.etNombre);
        tvNombre=findViewById(R.id.textView);
        tvFecha=findViewById(R.id.textView2);
        tvplataformas=findViewById(R.id.textView3);
        iv=findViewById(R.id.imageView2);
    }
    public void consumirWS(View v){
        if(isNetworkAvailable()){
            Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
            APIRestService ars = r.create(APIRestService.class);
            Call<Prueba> call = ars.filtrarNombre(ars.Key,ars.format,ars.field_list,ars.filtro+etNombre.getText());

            call.enqueue(new Callback<Prueba>() {


                @Override
                public void onResponse(Call<Prueba> call, Response<Prueba> response) {
                    if(response.isSuccessful()) {
                        Prueba juego = response.body();
                        cargarDatos(juego);





                        //adaptador.notifyDataSetChanged();

                    } else {
                        System.out.print("Eres un inutil");
                    }

                }

                @Override
                public void onFailure(Call<Prueba> call, Throwable t) {
                    System.out.print("Eres un inutil");

                }
            });
        }}
        public void cargarDatos(Prueba jg){
            ArrayList<Juego>aLjuego=jg.getResults();
            Juego j=aLjuego.get(0);
            tvNombre.setText(j.getNombre());
            tvFecha.setText(j.getFecha());
            tvplataformas.setText(j.getPlataformasAL().get(0).getNombre());
            new ActivityMenu.DownloadImageTask(iv)
                    .execute(j.getimagen().getImagen());

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
