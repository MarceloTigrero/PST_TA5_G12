package com.example.pst_ta5_g12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IniciarSesion extends AppCompatActivity {
    Button Ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_PST_TA5_G12);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        Ingresar = (Button) findViewById(R.id.idIngresar);

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}