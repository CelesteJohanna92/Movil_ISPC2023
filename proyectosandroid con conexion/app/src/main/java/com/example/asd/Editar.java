package com.example.asd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends AppCompatActivity implements View.OnClickListener{

    EditText ediUser, ediPass, ediNombre, ediApellido;
    Button btnActualizar, btnCancelar;
    int id = 0;
    Usuario u;
    daoUsuario dao;
    Intent x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        try {


            ediUser = (EditText) findViewById(R.id.EdiUser);
            ediPass = (EditText) findViewById(R.id.EdiPass);
            ediNombre = (EditText) findViewById(R.id.EdiNombre);
            ediApellido = (EditText) findViewById(R.id.EdiApellido);
            btnActualizar = (Button) findViewById(R.id.btnEdiActualizar);
            btnCancelar = (Button) findViewById(R.id.btnEdiCancelar);
            btnActualizar.setOnClickListener(this);
            btnCancelar.setOnClickListener(this);

            Bundle b = getIntent().getExtras();
            id = b.getInt("id");
            dao = new daoUsuario(this);
            u = dao.getUsuarioById(id);
            ediUser.setText(u.getUsuario());
            ediPass.setText(u.getPassword());
            ediNombre.setText(u.getNombre());
            ediApellido.setText(u.getApellido());
        }catch (Exception e) {
            // Manejar cualquier excepción que ocurra
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnEdiActualizar) {
            u.setUsuario(ediUser.getText().toString());
            u.setPassword(ediPass.getText().toString());
            u.setNombre(ediNombre.getText().toString());
            u.setApellido(ediApellido.getText().toString());

            if (!u.isNull()) {
                Toast.makeText(this, "Error: campos vacíos", Toast.LENGTH_SHORT).show();
            } else if (dao.updateUsuario(u)) {
                Toast.makeText(this, "Actualización exitosa!!", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(Editar.this, MainActivity.class);
                i2.putExtra("Id", u.getId());
                startActivity(i2);
                finish();
            } else {
                Toast.makeText(this, "No se puede actualizar!!", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btnEdiCancelar) {
            Intent i2 = new Intent(Editar.this, Inicio.class);
            i2.putExtra("id", u.getId());
            startActivity(i2);
            finish();
        }


    }
}