package com.gpixel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth fba;
    private FirebaseUser user;

    EditText etCorreo;
    EditText etContrasenna;
    String correo;
    String contrasenna;
    String correosGuardados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fba=FirebaseAuth.getInstance();
        etContrasenna =findViewById(R.id.etCont);
        etCorreo=findViewById(R.id.etCorreo);



    }
    public void iniciarSesion(View v){
        correo= String.valueOf(etCorreo.getText());
        contrasenna =String.valueOf(etContrasenna.getText());
        fba.signInWithEmailAndPassword(correo,contrasenna).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user=fba.getCurrentUser();
                    Intent i =new Intent(MainActivity.this,ActivityMenu.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"No se puede acceder",Toast.LENGTH_LONG).show();
                }

            }
        });




    }
    public void Registrar(View v){
        Intent i = new Intent(this,ActivityRegistrar.class);
        startActivity(i);


    }
}
