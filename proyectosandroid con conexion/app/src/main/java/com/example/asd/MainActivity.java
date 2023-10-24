package com.example.asd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DrawerLayout drawerLayout;

    EditText user, pass;
    Button btnEntrar, btnRegistrar;
    daoUsuario dao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.User);
        pass = (EditText) findViewById(R.id.Pass);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnEntrar.setOnClickListener((View.OnClickListener) this);
        btnRegistrar.setOnClickListener((View.OnClickListener) this);
        dao = new daoUsuario(this);

        // Llama a un método para insertar las categorías en la base de datos
        insertarCategorias();
    }

    private void insertarCategorias() {

        dao.insertarCategoria("Con alcohol");
        dao.insertarCategoria("Sin alcohol");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnEntrar) {
            String u = user.getText().toString();
            String p = pass.getText().toString();

            if (u.equals("") && p.equals("")) {
                Toast.makeText(this, "Error: campos vacíos", Toast.LENGTH_SHORT).show();
            } else if (dao.login(u, p) == 1) {
                Usuario ux = dao.getUsuario(u, p);
                Toast.makeText(this, "Datos correctos", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(MainActivity.this, Carga.class);
                i2.putExtra("id", ux.getId());
                startActivity(i2);
            } else {
                Toast.makeText(this, "Usuarios y/o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.btnRegistrar) {
            Intent i = new Intent(MainActivity.this, Registrar.class);
            startActivity(i);
        }
    }
}