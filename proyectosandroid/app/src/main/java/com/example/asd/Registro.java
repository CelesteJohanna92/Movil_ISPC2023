package com.example.asd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Toolbar toolbar = findViewById(R.id.toolbarB);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Botón de navegacion
        }
    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            // Maneja evento botón de navegación
            if (id == android.R.id.home) {
                onBackPressed();            // Vuelve una pág atrás
                return true;
            }
            return super.onOptionsItemSelected(item);
    }
}