package com.example.asd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class ConAlcohol extends AppCompatActivity {

    RecyclerView recyclerViewConAlcohol;
    RecetaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_alcohol);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Categoría Con Alcohol");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerViewConAlcohol = findViewById(R.id.recyclerViewConAlcohol);
        recyclerViewConAlcohol.setLayoutManager(new LinearLayoutManager(this));

        daoUsuario dao = new daoUsuario(this);
        List<Receta> listaRecetasConAlcohol = dao.selectRecetasConAlcohol();
        dao.close();

        adapter = new RecetaAdapter(listaRecetasConAlcohol, this);
        recyclerViewConAlcohol.setAdapter(adapter);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}