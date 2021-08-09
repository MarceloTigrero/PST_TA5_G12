package com.example.pst_ta5_g12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaGenero extends AppCompatActivity {

    public DatabaseReference mDatabase;
    public ArrayList generolist = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_genero);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void datosFire(){
        mDatabase.child("Libros");
        mDatabase.addValueEventListener(new ValueEventListener() {
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
}