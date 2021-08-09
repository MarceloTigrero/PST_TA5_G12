package com.example.pst_ta5_g12.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pst_ta5_g12.IniciarSesion;
import com.example.pst_ta5_g12.MainActivity;
import com.example.pst_ta5_g12.R;
import com.example.pst_ta5_g12.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //final TextView textView = binding.textDashboard;
        final TextView Usuario = binding.idUsuario;
        final TextView nombre = binding.idNombre;
        final TextView apellido = binding.idApellido;
        final TextView correo = binding.idCorreo;
        final TextView celular = binding.idCelular;
        final TextView favorito = binding.idFavorito;

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
                SharedPreferences sp= getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
                Usuario.setText(sp.getString("Usuario",null));
                nombre.setText(sp.getString("Nombre",null));
                apellido.setText(sp.getString("Apellido",null));
                correo.setText(sp.getString("Correo",null));
                celular.setText(sp.getString("Celular",null));
                favorito.setText(sp.getString("Favorito",null));

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