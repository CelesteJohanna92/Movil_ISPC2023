package com.example.asd.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.asd.R;
import com.example.asd.SinAlcohol;
import com.example.asd.ConAlcohol;
public class Categorias extends Fragment {

    Button ColctelSA;

    Button CoctelCA;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);

        ColctelSA = view.findViewById(R.id.ColctelSA);
        CoctelCA = view.findViewById(R.id.CoctelCA);

        ColctelSA.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), SinAlcohol.class);
            startActivity(intent);
        });

        CoctelCA.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), ConAlcohol.class);
            startActivity(intent);
        });

        return view;
    }
}
