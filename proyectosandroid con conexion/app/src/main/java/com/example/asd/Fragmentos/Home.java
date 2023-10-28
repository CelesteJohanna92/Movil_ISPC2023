package com.example.asd.Fragmentos;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.asd.R;
import com.example.asd.Receta;
import com.example.asd.RecetaAdapter;
import com.example.asd.daoUsuario;



import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    private ArrayList<Receta> listaRecetas;
    private daoUsuario dao;
    private RecetaAdapter recetaAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public Home() {
        // Required empty public constructor
    }

    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recetaAdapter = new RecetaAdapter(listaRecetas, requireContext());
        recyclerView.setAdapter(recetaAdapter);

        dao = new daoUsuario(requireContext());
        listaRecetas = dao.selectRecetas();
        dao.close();

        recetaAdapter.setRecetas(listaRecetas);
        recetaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dao = new daoUsuario(getContext());
        listaRecetas = dao.selectRecetas();
        dao.close();

        // Actualiza la lista de recetas en el adaptador y notifica el cambio
        recetaAdapter.setRecetas(listaRecetas);
        recetaAdapter.notifyDataSetChanged();
    }

}


