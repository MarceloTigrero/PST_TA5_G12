package com.example.pst_ta5_g12.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pst_ta5_g12.R;
import com.example.pst_ta5_g12.object.Pelicula;

import java.util.List;

public class adapterPelicula extends RecyclerView.Adapter<adapterPelicula.viewholderpelicula> {

    List<Pelicula> peliculaList;
    public adapterPelicula(List<Pelicula> peliculaList){
        this.peliculaList=peliculaList;
    }
    @NonNull
    @Override
    public viewholderpelicula onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderpelicula holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewholderpelicula extends RecyclerView.ViewHolder {
        public viewholderpelicula(@NonNull View itemView) {
            super(itemView);
        }
    }
}
