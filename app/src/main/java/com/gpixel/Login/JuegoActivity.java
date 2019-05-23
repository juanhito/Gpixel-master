package com.gpixel.Login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gpixel.R;
import com.gpixel.Usuario.Aplicacion;
import com.gpixel.javabeans.Juego;
import com.gpixel.javabeans.plataformas;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class JuegoActivity extends AppCompatActivity {
    TextView tvNombre;
    TextView tvDescripcion;
    TextView tvFecha;
    /*String nombre;
    String fecha;
    String descripcion;
    String id;*/
    TextView tvPlatafor;
    String total="";
    ImageView iv;
    private Bitmap loadedImage;
    Aplicacion app;
    String prueba;


    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        tvNombre=findViewById(R.id.tvNombre);
        tvDescripcion=findViewById(R.id.tvDesc);
        tvFecha=findViewById(R.id.tvFecha);
        tvPlatafor=findViewById(R.id.tvPlataformas);
       // ArrayList<String> datos2;
        ArrayList<plataformas>datos;

        app=(Aplicacion) getApplication();
      /*  nombre= getIntent().getStringExtra("nombre");
        fecha= getIntent().getStringExtra("fecha");
        descripcion=getIntent().getStringExtra("descripcion");
        datos2=getIntent().getStringArrayListExtra("plataformas");
        url=getIntent().getStringExtra("imagen");
        id=getIntent().getStringExtra("id");
        */
        Juego juego=app.getJuego();
        new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                .execute(juego.getimagen().getImagen());       // downloadFile(url);



        tvNombre.setText(juego.getNombre());
        tvDescripcion.setText(juego.getDescripcion());
        tvFecha.setText(juego.getFecha());
        datos=juego.getPlataformasAL();

        for(int i=0;i<datos.size()-1;i++){

            prueba=datos.get(i).getNombre();
            total=prueba+", "+total;

        }

        tvPlatafor.setText(total.substring(0,total.length()-2));


    }


    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

