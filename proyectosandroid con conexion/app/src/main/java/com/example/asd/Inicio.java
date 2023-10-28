package com.example.asd;

import static com.example.asd.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.Edits;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asd.Fragmentos.Home;

import java.text.BreakIterator;

public class Inicio extends AppCompatActivity implements View.OnClickListener {

    TextView nombre;
    Button btnEditar,btnEliminar,btnSalir;
    int id =0;
    Usuario u;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.inicio);

        nombre = (TextView) findViewById(R.id.nombreUsuario);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            id = b.getInt("id");
            dao = new daoUsuario(this);
            u = dao.getUsuarioById(id);
            nombre.setText(u.getNombre() + " " + u.getApellido());
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.btnEditar) {
            Intent a = new Intent(Inicio.this, Editar.class);
            a.putExtra("id", id);
            startActivity(a);
        } else if (v.getId() == R.id.btnEliminar) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setMessage("¿Estás seguro de eliminar tu cuenta?");
            b.setCancelable(false);
            b.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (dao.deleteUsuario(id)) {
                        Toast.makeText(Inicio.this, "Se eliminó correctamente", Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(Inicio.this, MainActivity.class);
                        startActivity(a);
                        finish();
                    } else {
                        Toast.makeText(Inicio.this, "Error, no se eliminó la cuenta", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            b.show();
        } else if (v.getId() == R.id.btnSalir) {
            Intent i2 = new Intent(Inicio.this, Home.class);
            startActivity(i2);
        }
        if (v.getId() == R.id.btnEdiActualizar) {

            BreakIterator ediUser = null;
            BreakIterator ediPass = null;
            BreakIterator ediNombre = null;
            BreakIterator ediApellido = null;

            u.setUsuario(ediUser.getText().toString());
            u.setPassword(ediPass.getText().toString());
            u.setNombre(ediNombre.getText().toString());
            u.setApellido(ediApellido.getText().toString());


            if (!u.isNull()) {
                Toast.makeText(this, "Error: campos vacios", Toast.LENGTH_SHORT).show();
            } else if (dao.updateUsuario(u)) {
                Toast.makeText(this, "Actualización exitoso!!", Toast.LENGTH_SHORT).show();

                Intent i2 = new Intent(this, MainActivity.class); // Usa "Editar.class" en lugar de "Editar.this"
                i2.putExtra("id", u.getId());
                startActivity(i2);

                finish();
            } else {
                Toast.makeText(this, "No se puede actualizar!!", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btnEdiCancelar) {
            Intent i2 = new Intent(this, Inicio.class);
            i2.putExtra("id", u.getId());
            startActivity(i2);
            finish();
        }

    }
}