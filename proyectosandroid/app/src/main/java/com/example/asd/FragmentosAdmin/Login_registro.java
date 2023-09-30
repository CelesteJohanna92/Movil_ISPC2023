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

public class Login_registro extends Fragment {

    Button Acceder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_registro, container, false);

        Acceder = view.findViewById(R.id.Acceder);

        Acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Inicio_sesion.class));
            }
        });
        return view;
    }
}