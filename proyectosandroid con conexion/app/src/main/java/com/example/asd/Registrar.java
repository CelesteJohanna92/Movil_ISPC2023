package com.example.asd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity implements View.OnClickListener {

    EditText us, pas, nom,ap;
    Button reg,can;
    daoUsuario dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);
        us = (EditText) findViewById(R.id.RegUser);
        pas = (EditText) findViewById(R.id.RegPass);
        nom = (EditText) findViewById(R.id.RegNombre);
        ap = (EditText) findViewById(R.id.RegApellido);
        reg = (Button) findViewById(R.id.btnRegRegistrar);
        can = (Button) findViewById(R.id.btnRegCancelar);
        reg.setOnClickListener((View.OnClickListener) this);
        can.setOnClickListener((View.OnClickListener) this);
        dao = new daoUsuario(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegRegistrar) {
            Usuario u = new Usuario();
            u.setUsuario(us.getText().toString());
            u.setPassword(pas.getText().toString());
            u.setNombre(nom.getText().toString());
            u.setApellido(ap.getText().toString());

            if (!u.isNull()) {
                Toast.makeText(this, "Error: campos vacíos", Toast.LENGTH_SHORT).show();
            } else if (dao.insertarUsuario(u)) {
                Toast.makeText(this, "Registro exitoso!!", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(Registrar.this, MainActivity.class);
                startActivity(i2);
                finish();
            } else {
                Toast.makeText(this, "Usuario ya registrado!!", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.btnRegCancelar) {
            Intent i = new Intent(Registrar.this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }
}