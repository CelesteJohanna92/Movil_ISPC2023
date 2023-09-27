package com.example.asd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asd.FragmentosAdmin.Buscar;
import com.example.asd.FragmentosAdmin.Inicio;
import com.example.asd.FragmentosAdmin.Perfil;
import com.example.asd.FragmentosAdmin.Sobre_nosotros;
import com.google.android.material.navigation.NavigationView;

public class MainActivityAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        Toolbar toolbar = findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_A);
        NavigationView navigationView = findViewById(R.id.nav_viewA);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Inicio()).commit();
            navigationView.setCheckedItem(R.id.Inicio);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.Inicio) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Inicio()).commit();
        } else if (itemId == R.id.Buscar) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Buscar()).commit();
        } else if (itemId == R.id.Perfil) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Perfil()).commit();
        } else if (itemId == R.id.Sobre_nosotros) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerA, new Sobre_nosotros()).commit();
        } else if (itemId == R.id.Cerrar_sesion) {
            Toast.makeText(this, "Has cerrado sesión", Toast.LENGTH_SHORT).show();
        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}