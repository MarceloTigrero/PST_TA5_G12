package com.example.pst_ta5_g12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    String usu, con;
    String nombre;
    String apellido;
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

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usu = Usuario.getText().toString().trim();
                con = Contrasena.getText().toString().trim();
                iniciarSesion1(usu);
            }
        });
    }


    private void iniciarSesion1(String user) {
        Map<String, String> usua = new HashMap<>();
        Map<String, String> Nombre = new HashMap<>();
        Map<String, String> Apellido = new HashMap<>();
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
                                    //Log.e("Final", String.valueOf(task.getResult().getValue()));
                                    InicioSesion inicio = ds.getValue(InicioSesion.class);
                                    //Log.e("Final2", String.valueOf(inicio));
                                    usua.put(inicio.getUsuario(), inicio.getContrasenia());
                                    Nombre.put(inicio.getUsuario(), inicio.getNombre());
                                    Apellido.put(inicio.getUsuario(), inicio.getApellido());
                                    if (usua.size() < ids.size()) {
                                        Ingresar.setSelected(false);
                                    } else {
                                        contrasena = usua.get(user);
                                        nombre = Nombre.get(user);
                                        apellido = Apellido.get(user);
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
                                                intent.putExtra("Usuario", user);
                                                intent.putExtra("Nombre", nombre);
                                                intent.putExtra("Apellido", apellido);
                                                intent.putExtra("Correo", inicio.getCorreo());
                                                intent.putExtra("Celular", inicio.getCelular());
                                                intent.putExtra("Favorito", inicio.getFavorito());
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