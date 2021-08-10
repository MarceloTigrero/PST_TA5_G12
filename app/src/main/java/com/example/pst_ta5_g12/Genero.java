package com.example.pst_ta5_g12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pst_ta5_g12.adapter.AdapterLibro;
import com.example.pst_ta5_g12.object.Libro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Genero extends AppCompatActivity {

    ArrayList<Libro> list;
    TextView genero;
    RecyclerView listaGenero;
    SearchView buscar;
    String imagene;
    String nombree;
    String descripcione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero);

        list = new ArrayList<>();
        genero = (TextView) findViewById(R.id.idGeneros);
        listaGenero = (RecyclerView) findViewById(R.id.idListGenero);
        buscar = findViewById(R.id.idBuscarGenero);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Libros");

        //searchView = root.findViewById(R.id.search);
        LinearLayoutManager lm= new LinearLayoutManager(this);
        listaGenero.setLayoutManager(lm);
        list = new ArrayList<>();
        AdapterLibro adapter = new AdapterLibro(list);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View dialView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.dialogo,null);
                ImageView imagen;
                TextView nombre;
                TextView descripcion;


                imagen = (ImageView) dialView.findViewById(R.id.idImagen);
                nombre = (TextView) dialView.findViewById(R.id.idNombreE);
                descripcion = (TextView) dialView.findViewById(R.id.idDescripcionE);

                imagene = list.get(listaGenero.getChildAdapterPosition(v)).getImagen();
                nombree = list.get(listaGenero.getChildAdapterPosition(v)).getNombre();
                descripcione = list.get(listaGenero.getChildAdapterPosition(v)).getDescripcion();
                Picasso.get().load(imagene).into(imagen);
                nombre.setText(nombree);
                descripcion.setText(descripcione);
                builder.setView(dialView);
                builder.setCancelable(true);
                builder.show();

            }
        });
        listaGenero.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        Libro ms = snapshot1.getValue(Libro.class);

                        imagene = ms.getImagen();
                        nombree = ms.getNombre();
                        descripcione = ms.getDescripcion();
                        Bundle extra = getIntent().getExtras();
                        String mens = extra.getString("Genero");

                        if (ms.getGenero().equals(mens)){
                            list.add(ms);
                            genero.setText(ms.getGenero());
                            Log.e("myTag", ""+ms+"");
                        }
                    }
                    adapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                buscar(s);
                return true;
            }
        });
    }
    private void buscar(String s) {
        ArrayList<Libro> mylist= new ArrayList<>();
        for(Libro obj:list){
            if(obj.getNombre().toLowerCase().contains(s.toLowerCase())){
                mylist.add(obj);
            }
        }
        AdapterLibro adapterLibro = new AdapterLibro(mylist);
        listaGenero.setAdapter(adapterLibro);
    }
}