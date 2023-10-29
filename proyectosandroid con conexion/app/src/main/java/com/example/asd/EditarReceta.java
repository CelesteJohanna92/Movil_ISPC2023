package com.example.asd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.example.asd.Receta;

public class EditarReceta extends AppCompatActivity {

    EditText txtNombre, txtIngredientes, txtinstruccione;

    Receta receta;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_receta);

        txtNombre = findViewById(R.id.EditarNombreImagen);
        txtIngredientes = findViewById(R.id.EditarIngredientesReceta);
        txtIngredientes = findViewById(R.id.EditarInstruccionesReceta);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("ID");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        daoUsuario daoUsuario = new daoUsuario(EditarReceta.this);
        receta = daoUsuario.getReceta(id);

        if (receta != null){
            txtNombre.setText(receta.getNombre());
            txtIngredientes.setText(receta.getIngredientes());
            txtinstruccione.setText(receta.getInstrucciones());
        }
    }
}