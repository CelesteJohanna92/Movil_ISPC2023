package com.example.asd.FragmentosAdmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.asd.Inicio_sesion;
import com.example.asd.R;
import com.example.asd.Registro;

public class Login_registro extends Fragment {

    Button Acceder;
    Button Registrarse; // Agregamos un botón para redirigir a la pantalla de registro

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_registro, container, false);

        Acceder = view.findViewById(R.id.Acceder);
        Registrarse = view.findViewById(R.id.Registrarse); // Asignamos el botón de registro

        Acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Inicio_sesion.class));
            }
        });

        // Agregamos un OnClickListener para el botón de registro
        Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Registro.class)); // Reemplaza 'Registro' con la actividad adecuada
            }
        });

        return view;
    }
}
