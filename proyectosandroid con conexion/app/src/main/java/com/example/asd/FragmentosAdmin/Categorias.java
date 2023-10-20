package com.example.asd.FragmentosAdmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.asd.R;

public class Categorias extends Fragment {

    Button ColctelSA;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);


        ColctelSA = view.findViewById(R.id.ColctelSA);

        ColctelSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SinAlcohol.class));
            }
        });

        return view;
    }
}