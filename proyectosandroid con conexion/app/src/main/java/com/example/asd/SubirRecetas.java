package com.example.asd;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SubirRecetas extends AppCompatActivity {

    private EditText edtNombre, edtIngredientes, edtInstrucciones;
    private ImageButton btnImagen;
    private String imagenPath;
    private Button SubirRecetas;
    private Spinner spinnerCategoria;
    int id = 0; // Asigna el valor que corresponda
    private int idUsuario; // Asigna el valor que corresponda
    private daoUsuario dao;

    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri uri = data.getData();
                        imagenPath = getPathFromUri(uri);
                        btnImagen.setImageURI(uri);
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
            // Manejar el caso en que no hay categorías disponibles
            return;
        }

        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriasList);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterSpinner);

        edtNombre = findViewById(R.id.NombreReceta);
        edtIngredientes = findViewById(R.id.Ingrediente);
        edtInstrucciones = findViewById(R.id.Descripcion);
        btnImagen = findViewById(R.id.imagen);
        SubirRecetas = findViewById(R.id.SubirRecetas);

        btnImagen.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            galleryLauncher.launch(intent);
        });

        SubirRecetas.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString();
            String ingredientes = edtIngredientes.getText().toString();
            String instrucciones = edtInstrucciones.getText().toString();

            if (imagenPath == null || imagenPath.isEmpty()) {
                Toast.makeText(this, "Seleccione una imagen", Toast.LENGTH_SHORT).show();
                return;
            }

            byte[] imagenBytes = convertirImagenBytes(Uri.parse(imagenPath));

            if (id == 0) {
                Toast.makeText(this, "Debe autenticarse antes de subir una receta", Toast.LENGTH_SHORT).show();
                return;
            }

            String categoria = spinnerCategoria.getSelectedItem().toString();
            agregarReceta(nombre, ingredientes, instrucciones, imagenBytes, categoria);

            // Limpiar los campos después de subir la receta con éxito
            limpiarCampos();

        });
    }
            //metodo para limpiar
             private void limpiarCampos() { edtNombre.getText().clear();
             edtIngredientes.getText().clear();
             edtInstrucciones.getText().clear();
             btnImagen.setImageURI(null);
             spinnerCategoria.setSelection(0);
    }
    private String getPathFromUri(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String filePath = cursor.getString(column_index);
            cursor.close();
            return filePath;
        } else {
            return uri.getPath();
        }
    }

    private void agregarReceta(String nombre, String ingredientes, String instrucciones, byte[] imagenBytes, String categoria) {
        if (nombre.isEmpty()) {
            Toast.makeText(this, "El nombre de la receta no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int idCategoria = dao.obtenerIdCategoria(categoria);

            if (idCategoria != -1) {
                dao.insertarReceta(idCategoria, idUsuario, nombre, ingredientes, instrucciones, imagenBytes);
                Toast.makeText(this, "La receta ha sido subida con éxito", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Categoría no encontrada en la base de datos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al subir la receta", Toast.LENGTH_SHORT).show();
        }
    }


    private byte[] convertirImagenBytes(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
