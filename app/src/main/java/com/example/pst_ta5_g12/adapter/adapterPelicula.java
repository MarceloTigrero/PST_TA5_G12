package com.example.pst_ta5_g12.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        viewholderpelicula holder = new viewholderpelicula(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderpelicula holder, int position) {
        Pelicula pel=peliculaList.get(position);
        holder.tvpelicula.setText(pel.getAutor());
        holder.tvautor.setText(pel.getAutor());
        holder.tveditorial.setText(pel.getEditorial());
    }

    @Override
    public int getItemCount() {
        return peliculaList.size();
    }

    public class viewholderpelicula extends RecyclerView.ViewHolder {
        TextView tvpelicula,tvautor,tveditorial;

        public viewholderpelicula(@NonNull View itemView) {
            super(itemView);
            tvpelicula = itemView.findViewById(R.id.tvpelicula);
            tvautor = itemView.findViewById(R.id.tvautor);
            tveditorial = itemView.findViewById(R.id.tveditorial);
        }
    }
}
