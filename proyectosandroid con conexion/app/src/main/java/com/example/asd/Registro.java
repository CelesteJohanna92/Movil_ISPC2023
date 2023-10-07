package com.example.asd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asd.db.DBHelper;

public class Registro extends AppCompatActivity {

    EditText edtNombre, edtApellido, edtCorreo, edtContrasena;
    Button btnRegistrar;

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

        edtNombre=(EditText) findViewById(R.id.Nombre);
        edtApellido=(EditText) findViewById(R.id.Apellido);
        edtCorreo=(EditText) findViewById(R.id.Correo);
        edtContrasena=(EditText) findViewById(R.id.Password);

        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        btnRegistrar = findViewById(R.id.btnRegistrar); // Inicializa el botón correctamente

        btnRegistrar.setOnClickListener(view -> {
            dbHelper.agregarUsuarios(edtNombre.getText().toString(), edtApellido.getText().toString(), edtCorreo.getText().toString(), edtContrasena.getText().toString());
            Toast.makeText(getApplicationContext(), "Registrado correctamente", Toast.LENGTH_SHORT).show();
        });
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