package com.example.asd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class SubirRecetas extends AppCompatActivity {

    private EditText edtNombre, edtIngredientes, edtInstrucciones, edtImagenURL;
    private Button SubirRecetas;
    private Spinner spinnerCategoria;
    private int id = 0; // Asigna el valor que corresponda
    private int idUsuario; // Asigna el valor que corresponda
    private daoUsuario dao;

    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri uri = data.getData();
                        edtImagenURL.setText(uri.toString()); // Guarda la URL de la imagen
                    }
                }
            }
    );

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_recetas);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            id = b.getInt("id");
        }

        Toolbar toolbar = findViewById(R.id.toolbarB);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Subir Recetas");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        dao = new daoUsuario(this);

        List<String> categoriasList = dao.selectCategorias();

        if (categoriasList.isEmpty()) {
            Toast.makeText(this, "No se encontraron categorías en la base de datos.", Toast.LENGTH_SHORT).show();
            return;
        }

        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriasList);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterSpinner);

        edtNombre = findViewById(R.id.NombreReceta);
        edtIngredientes = findViewById(R.id.Ingrediente);
        edtInstrucciones = findViewById(R.id.Descripcion);
        edtImagenURL = findViewById(R.id.ImagenURL);
        SubirRecetas = findViewById(R.id.SubirRecetas);

        SubirRecetas.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString();
            String ingredientes = edtIngredientes.getText().toString();
            String instrucciones = edtInstrucciones.getText().toString();
            String imagenURL = edtImagenURL.getText().toString(); // Obtén la URL de la imagen

            if (imagenURL.isEmpty()) {
                Toast.makeText(this, "Ingrese una URL válida", Toast.LENGTH_SHORT).show();
                return;
            }

            if (id == 0) {
                Toast.makeText(this, "Debe autenticarse antes de subir una receta", Toast.LENGTH_SHORT).show();
                return;
            }

            String categoria = spinnerCategoria.getSelectedItem().toString();
            agregarReceta(nombre, ingredientes, instrucciones, imagenURL, categoria);

            // Limpiar los campos después de subir la receta con éxito
            limpiarCampos();
        });
    }

    // Método para limpiar los campos
    private void limpiarCampos() {
        edtNombre.getText().clear();
        edtIngredientes.getText().clear();
        edtInstrucciones.getText().clear();
        edtImagenURL.getText().clear();
        spinnerCategoria.setSelection(0);
    }

    private void agregarReceta(String nombre, String ingredientes, String instrucciones, String imagenURL, String categoria) {
        if (nombre.isEmpty()) {
            Toast.makeText(this, "El nombre de la receta no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int idCategoria = dao.obtenerIdCategoria(categoria);

            if (idCategoria != -1) {
                dao.insertarReceta(idCategoria, idUsuario, nombre, ingredientes, instrucciones, imagenURL);
                Toast.makeText(this, "La receta ha sido subida con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Categoría no encontrada en la base de datos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al subir la receta", Toast.LENGTH_SHORT).show();
        }
    }
}