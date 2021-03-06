package com.example.pst_ta5_g12.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pst_ta5_g12.R;
import com.example.pst_ta5_g12.adapter.AdapterLibro;
import com.example.pst_ta5_g12.databinding.FragmentHomeBinding;
import com.example.pst_ta5_g12.object.Libro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    ArrayList<Libro> list;
    RecyclerView rv;
    String imagene;
    String nombree;
    String descripcione;


    private void buscar(String s) {
        ArrayList<Libro> mylist= new ArrayList<>();
        for(Libro obj:list){
            if(obj.getNombre().toLowerCase().contains(s.toLowerCase())){
                mylist.add(obj);
            }
        }
        AdapterLibro adapterLibro = new AdapterLibro(mylist);
        rv.setAdapter(adapterLibro);
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.texthome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                //textView.setText(s);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Libros");
                rv = binding.rv;

                SearchView searchView = binding.search;
                //searchView = root.findViewById(R.id.search);
                LinearLayoutManager lm= new LinearLayoutManager(getActivity());
                rv.setLayoutManager(lm);
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

                        imagene = list.get(rv.getChildAdapterPosition(v)).getImagen();
                        nombree = list.get(rv.getChildAdapterPosition(v)).getNombre();
                        descripcione = list.get(rv.getChildAdapterPosition(v)).getDescripcion();
                        Picasso.get().load(imagene).into(imagen);
                        nombre.setText(nombree);
                        descripcion.setText(descripcione);
                        builder.setView(dialView);
                        builder.setCancelable(true);
                        builder.show();

                    }
                });
                rv.setAdapter(adapter);

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                Libro ms = snapshot1.getValue(Libro.class);
                                imagene = ms.getImagen();
                                nombree = ms.getNombre();
                                descripcione = ms.getDescripcion();
                                Log.e("myTag", ""+ms+"");
                                list.add(ms);
                            }
                            adapter.notifyDataSetChanged();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}