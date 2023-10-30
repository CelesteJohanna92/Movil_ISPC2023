package com.example.asd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.asd.Fragmentos.Home;

public class EditarReceta extends AppCompatActivity {

    EditText txtNombre, txtIngredientes, txtInstrucciones;
    Button btnModificar, btnEliminar, btnCancelar;

    Receta receta;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_receta);

        txtNombre = findViewById(R.id.EditarNombreImagen);
        txtIngredientes = findViewById(R.id.EditarIngredientesReceta);
        txtInstrucciones = findViewById(R.id.EditarInstruccionesReceta);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Editar Recetas");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        daoUsuario daoUsuario = new daoUsuario(EditarReceta.this);
        receta = daoUsuario.getReceta(id);

        if (receta != null) {
            txtNombre.setText(receta.getNombre());
            txtIngredientes.setText(receta.getIngredientes());
            txtInstrucciones.setText(receta.getInstrucciones());
        }

        btnModificar = findViewById(R.id.btnActualizarReceta);
        btnEliminar = findViewById(R.id.btnEliminarReceta);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                String ingredientes = txtIngredientes.getText().toString();
                String instrucciones = txtInstrucciones.getText().toString();

                if (!nombre.isEmpty() && !ingredientes.isEmpty()) {
                    receta.setNombre(nombre);
                    receta.setIngredientes(ingredientes);
                    receta.setInstrucciones(instrucciones);

                    boolean updated = daoUsuario.updateReceta(receta);

                    if (updated) {
                        Toast.makeText(EditarReceta.this, "Receta modificada con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditarReceta.this, "Error al modificar la receta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditarReceta.this, "Nombre e ingredientes son obligatorios", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditarReceta.this);
                builder.setMessage("¿Desea eliminar esta receta?")
                        .setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean deleted = daoUsuario.deleteReceta(id);

                                if (deleted) {
                                    Toast.makeText(EditarReceta.this, "Receta eliminada con éxito", Toast.LENGTH_SHORT).show();
                                    lista();
                                } else {
                                    Toast.makeText(EditarReceta.this, "Error al eliminar la receta", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Al hacer clic en "Cancelar", activamos la acción de retroceso
            }
        });


    }
    @Override
    public void onBackPressed() {
        // Esta es la acción de retroceso personalizada
        super.onBackPressed(); // Esto volverá a la página anterior
    }
    private void lista(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}