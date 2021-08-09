package com.example.pst_ta5_g12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pst_ta5_g12.object.Libro;
import com.example.pst_ta5_g12.object.Pelicula;
import com.example.pst_ta5_g12.usuarios.InicioSesion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaGenero extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public ArrayList generolist = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_genero);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void datosFire(){
        databaseReference.child("Libros");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        //String nombres=ds.child();
                        String gen = ds.child("Genero").getValue().toString();
                        for (int i = 0; i < generolist.size(); i++) {
                            for (int j = 0; i < generolist.size(); j++) {
                                if (i != j) {
                                    if (generolist.get(i).equals(generolist.get(j))) {
                                    } else {
                                        generolist.add(gen);
                                    }
                                }
                            }
                        }

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void iniciarSesion1(String user) {
        ArrayList<String> nomlibros = new ArrayList<>();
        Map<String, String> caracLibros = new HashMap<>();
        Map<String, Map<String, String>> Libros = new HashMap<>();
        Map<String, String> ids = new HashMap<>();
        databaseReference.child("Libros").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    //Log.e("firebase", "Error getting data", task.getException());
                    Toast.makeText(ListaGenero.this, "Error Data", Toast.LENGTH_LONG).show();
                } else {
                    Log.e("firebase", String.valueOf(task.getResult().getChildren()));
                    for (final DataSnapshot ds : task.getResult().getChildren()) {
                        Log.e("Re", "" + ds);
                        ids.put(ds.getKey(), ds.getKey());
                        databaseReference.child("Libros").child(ds.getKey()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (!task.isSuccessful()) {
                                    Log.e("firebase", "Error getting data", task.getException());
                                } else {
                                    nomlibros.add(task.getResult().getKey());

                                    Log.e("Final", String.valueOf(task.getResult().getValue()));
                                    Libro inicio = ds.getValue(Libro.class);
                                    Log.e("Final2", String.valueOf(inicio));
                                    caracLibros.put("autor", inicio.getAutor());
                                    caracLibros.put("descripcion", inicio.getDescripcion());
                                    caracLibros.put("editorial", inicio.getEditorial());
                                    caracLibros.put("genero", inicio.getGenero());
                                    caracLibros.put("imagen", inicio.getImagen());
                                    Libros.put(task.getResult().getKey(),caracLibros);
                                    if (nomlibros.size() < ids.size()) {
                                        Log.e("Menor", "menor");
                                    } else {
                                        Log.e("salida",nomlibros+"" );
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