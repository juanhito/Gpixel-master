package com.gpixel.Login;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.gpixel.R;
import com.gpixel.Retrofit.APIRestService;
import com.gpixel.Retrofit.RetrofitClient;
import com.gpixel.Usuario.Aplicacion;
import com.gpixel.javabeans.AdaptadorJuegos;
import com.gpixel.javabeans.Imagen;
import com.gpixel.javabeans.Juego;
import com.gpixel.javabeans.Prueba;
import com.gpixel.javabeans.plataformas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InicioActivity extends AppCompatActivity {

    private RecyclerView rvMain;
        private AdaptadorJuegos adaptador;
        private LinearLayoutManager llm;
        String id;
        private EditText etBusqueda;


        private ArrayList<Juego> datos;
        private ArrayList<String>datos2;
        private ArrayList<plataformas>datos3;
        Aplicacion app;
        Juego juego;
        Imagen imag;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inicio);
            rvMain = findViewById(R.id.rv);
            datos=new ArrayList<Juego>();
            adaptador=new AdaptadorJuegos(datos);
            llm = new LinearLayoutManager(this);
            datos2=new ArrayList<String>();
            rvMain.setItemAnimator(new DefaultItemAnimator());
            rvMain.setAdapter(adaptador);
            rvMain.setLayoutManager(llm);
            datos3=new ArrayList<plataformas>();
            app=(Aplicacion) getApplication();
        /*CARGAR DATOS*/
       //cargarDatos();
            consumirWS();
            etBusqueda=findViewById(R.id.etBusq);



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));


        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.android) {
            Toast toast = Toast.makeText(this, "Android NO funsiona", Toast.LENGTH_LONG);
            toast.show();
        } else if (id == R.id.search) {



            Toast toast = Toast.makeText(this, "NO busques porque no tenemos nada", Toast.LENGTH_LONG);
            toast.show();
        }

        return super.onOptionsItemSelected(item);
    }
    public void consumirWS(){
        if(isNetworkAvailable()){
        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = r.create(APIRestService.class);
        String filtroPlat="145|146|157|117";
        Call<Prueba> call = ars.obtenerPrueba(ars.Key,ars.format,ars.field_list,ars.filter+filtroPlat);

        call.enqueue(new Callback<Prueba>() {


            @Override
            public void onResponse(Call<Prueba> call, Response<Prueba> response) {
                if(response.isSuccessful()) {
                    Prueba juego = response.body();
                    datos=juego.getResults();


                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent(InicioActivity.this,ActivityRegistrar.class);
                        }
                    });



                    adaptador = new AdaptadorJuegos(datos);
                    rvMain.setAdapter(adaptador);

                    //adaptador.notifyDataSetChanged();

                } else {
                    System.out.print("Eres un inutil");
                }
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Juego juego=datos.get(rvMain.getChildAdapterPosition(v));
                        datos3=juego.getPlataformasAL();
                        imag=juego.getimagen();
                        app.setJuego(juego);

                        /*for(int j=0;j<=datos3.size()-1;j++){
                            plataformas plat= datos3.get(j);
                            String pene=plat.getNombre();
                            datos2.add(pene);



                        }*/
                        Intent i=new Intent(InicioActivity.this,JuegoActivity.class);
                        /*i.putExtra("imagen",imag.getImagen());
                        i.putExtra("nombre",juego.getNombre());
                        i.putExtra("descripcion",juego.getDescripcion());
                        i.putExtra("fecha",juego.getFecha());
                        i.putExtra("plataformas",datos2);*/

                        startActivity(i);
                    }
                });
            }

            @Override
            public void onFailure(Call<Prueba> call, Throwable t) {
                System.out.print("Eres un inutil");

            }
        });
    }}




    public void cargarDatos(View v ) {
       String filtro=etBusqueda.getText().toString();
       if (isNetworkAvailable()) {
            Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
            APIRestService ars = r.create(APIRestService.class);
            Call<Prueba> call = ars.filtrarNombre("bd2514fa50bc7f31b80992f4dd257af11aa48f96","json","name",ars.filtro+filtro);

            call.enqueue(new Callback<Prueba>() {

                @Override
               public void onResponse(Call<Prueba> call, Response<Prueba> response) {
                    if (!response.isSuccessful()) {
                        Log.i("Resultado: ", "Error" + response.code());
                    } else {
                        Prueba pr;
                        pr = response.body();
                        datos=pr.getResults();
                        if (datos != null) {
                           adaptador.notifyDataSetChanged();
                       }

                    }
                }

                @Override
                public void onFailure(Call<Prueba> call, Throwable t) {
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
        ConnectivityManager manager = (ConnectivityManager) getSystemService(LoginActivity.CONNECTIVITY_SERVICE);
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
