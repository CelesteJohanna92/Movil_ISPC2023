package com.example.asd;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.asd.Fragmentos.Categorias;
import com.example.asd.Fragmentos.Contacto;
import com.example.asd.Fragmentos.Home;
import com.example.asd.Fragmentos.Perfil;
import com.example.asd.Fragmentos.Sobre_nosotros;
import com.example.asd.Fragmentos.Subir_Receta;
import com.google.android.material.navigation.NavigationView;

public class menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_A);
        NavigationView navigationView = findViewById(R.id.nav_viewA);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Home()).commit();
            navigationView.setCheckedItem(R.id.Home);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.Home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Home()).commit();}
        else if (itemId == R.id.Categorias) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Categorias()).commit();}
        else if (itemId == R.id.Subir_Receta) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Subir_Receta()).commit();}
        else if (itemId == R.id.Perfil) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Perfil()).commit();}
        else if (itemId == R.id.Sobre_nosotros) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Sobre_nosotros()).commit();}
        else if (itemId == R.id.Contacto) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Contacto()).commit();}
        else if (itemId == R.id.Cerrar_sesion) {
            Toast.makeText(this, "Has cerrado sesión", Toast.LENGTH_SHORT).show();
            // Redirigir al usuario a la actividad de inicio de sesión.
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            // Finalizar la actividad actual para cerrar la sesión.
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}