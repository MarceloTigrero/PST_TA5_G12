package com.example.pst_ta5_g12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pst_ta5_g12.usuarios.InicioSesion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class IniciarSesion extends AppCompatActivity {
    EditText Usuario, Contrasena;
    Button Ingresar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SharedPreferences sp;
    String usu, con;
    String nombre;
    String apellido;
    String correo;
    String celular;
    String favorito;
    String contrasena = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_PST_TA5_G12);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        Usuario = (EditText) findViewById(R.id.idInputUsuario);
        Contrasena = (EditText) findViewById(R.id.idInputContrasenia);
        Ingresar = (Button) findViewById(R.id.idIngresar);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        sp = getSharedPreferences("sesion", Context.MODE_PRIVATE);

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usu = Usuario.getText().toString().trim();
                con = Contrasena.getText().toString().trim();
                iniciarSesion1(usu);
            }
        });
    }
    private void recordarUsuario(String usuario,String nombre,String apellido,String correo,String celular,String favorito) {
        SharedPreferences.Editor editor= sp.edit();
        editor.putString("Usuario",usuario);
        editor.putString("Nombre",nombre);
        editor.putString("Apellido",apellido);
        editor.putString("Correo",correo);
        editor.putString("Celular",celular);
        editor.putString("Favorito",favorito);
        editor.apply();
    }

    private void iniciarSesion1(String user) {
        Map<String, String> usua = new HashMap<>();
        Map<String, String> Nombre = new HashMap<>();
        Map<String, String> Apellido = new HashMap<>();
        Map<String, String> Correo = new HashMap<>();
        Map<String, String> Celular = new HashMap<>();
        Map<String, String> Favorito = new HashMap<>();
        Map<String, String> ids = new HashMap<>();
        databaseReference.child("Usuarios").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    //Log.e("firebase", "Error getting data", task.getException());
                    Toast.makeText(IniciarSesion.this, "Error Data", Toast.LENGTH_LONG).show();
                    Toast.makeText(IniciarSesion.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                } else {
                    Log.e("firebase", String.valueOf(task.getResult().getChildren()));
                    for (final DataSnapshot ds : task.getResult().getChildren()) {
                        Log.e("Re", "" + ds);
                        ids.put(ds.getKey(), ds.getKey());
                        databaseReference.child("Usuarios").child(ds.getKey()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (!task.isSuccessful()) {
                                    Log.e("firebase", "Error getting data", task.getException());
                                } else {
                                    Log.e("Final", String.valueOf(task.getResult().getValue()));
                                    InicioSesion inicio = ds.getValue(InicioSesion.class);
                                    Log.e("Final2", String.valueOf(inicio));
                                    usua.put(inicio.getUsuario(), inicio.getContrasena());
                                    Nombre.put(inicio.getUsuario(), inicio.getNombre());
                                    Apellido.put(inicio.getUsuario(), inicio.getApellido());
                                    Correo.put(inicio.getUsuario(), inicio.getCorreo());
                                    Celular.put(inicio.getUsuario(), inicio.getCelular());
                                    Favorito.put(inicio.getUsuario(), inicio.getFavorito());
                                    if (usua.size() < ids.size()) {
                                        Ingresar.setSelected(false);
                                    } else {
                                        contrasena = usua.get(user);
                                        nombre = Nombre.get(user);
                                        apellido = Apellido.get(user);
                                        correo = Correo.get(user);
                                        celular = Celular.get(user);
                                        favorito = Favorito.get(user);
                                        if (contrasena == null) {
                                            Toast.makeText(IniciarSesion.this, "Usuario No Existe", Toast.LENGTH_SHORT).show();
                                            Usuario.setError("Usuario Invalido");
                                        } else {
                                            Usuario.setError(null);
                                            if (!contrasena.equals(con)) {
                                                Contrasena.setError("Contraseña invalida");
                                            } else {
                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                Toast.makeText(IniciarSesion.this, "Inicio Exitoso", Toast.LENGTH_SHORT).show();
                                                recordarUsuario(user,nombre,apellido,correo,celular,favorito);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                }

            }
        });
    }
}