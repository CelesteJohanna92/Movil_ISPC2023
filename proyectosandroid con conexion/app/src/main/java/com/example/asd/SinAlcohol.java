package com.example.asd;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class SinAlcohol extends AppCompatActivity {

    RecyclerView recyclerViewSinAlcohol;
    RecetaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_alcohol);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Categoría Sin Alcohol");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerViewSinAlcohol = findViewById(R.id.recyclerViewSinAlcohol);
        recyclerViewSinAlcohol.setLayoutManager(new LinearLayoutManager(this));

        daoUsuario dao = new daoUsuario(this);
        List<Receta> listaRecetasSinAlcohol = dao.selectRecetasSinAlcohol();
        dao.close();

        adapter = new RecetaAdapter(listaRecetasSinAlcohol, this);
        recyclerViewSinAlcohol.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
