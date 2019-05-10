package com.gpixel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.gpixel.javabeans.Usuario;


public class ActivityRegistrar extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etContrasenna;
    private EditText etRepetirCont;
    private EditText etCorreo;
    private DatabaseReference dbR;
    private String NombreUsuario;
    private String Contrasenna;
    private String Correo;
    private String repetir;
    private FirebaseAuth fba;
    private FirebaseUser user;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        etUsuario=findViewById(R.id.etNombre);
        etContrasenna=findViewById(R.id.etCont);
        etRepetirCont=findViewById(R.id.etConfirma);
        etCorreo=findViewById(R.id.etCorreo);
        fba=FirebaseAuth.getInstance();

    }
    public void enviar(View v){
        NombreUsuario =etUsuario.getText().toString();
        Contrasenna=etContrasenna.getText().toString();
        Correo=etCorreo.getText().toString();
        repetir=etRepetirCont.getText().toString();

        usuario=new Usuario(NombreUsuario,Correo,Contrasenna);
        if(Contrasenna.equals(repetir)){
            Toast.makeText(getBaseContext(),"las contraseñas no coiniciden",Toast.LENGTH_LONG).show();
        }
        else{
            dbR.push().setValue(usuario);
        }

    }
}
