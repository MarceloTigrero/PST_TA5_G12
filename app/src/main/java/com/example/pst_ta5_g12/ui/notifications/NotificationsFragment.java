package com.example.pst_ta5_g12.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pst_ta5_g12.Genero;
import com.example.pst_ta5_g12.R;
import com.example.pst_ta5_g12.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final LinearLayout romantico = binding.idRomantico;
        final LinearLayout terror = binding.idTerror;
        final LinearLayout aventura = binding.idAventura;
        final LinearLayout ficcion = binding.idFiccion;

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                romantico.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), Genero.class);
                        intent.putExtra("Genero","Romantico");
                        startActivity(intent);
                    }
                });
                terror.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), Genero.class);
                        intent.putExtra("Genero","Terror");
                        startActivity(intent);
                    }
                });
                aventura.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), Genero.class);
                        intent.putExtra("Genero","Aventura");
                        startActivity(intent);
                    }
                });
                ficcion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), Genero.class);
                        intent.putExtra("Genero","Ficcion");
                        startActivity(intent);
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