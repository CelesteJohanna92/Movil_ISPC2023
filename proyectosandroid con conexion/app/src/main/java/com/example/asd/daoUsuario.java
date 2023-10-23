package com.example.asd;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import android.util.Log;

public class daoUsuario {

    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "Coctelis.db";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, usuario text, password text, nombre text, apellido text, estado_sesion integer)";
    String tablaReceta = "create table if not exists receta(id integer primary key autoincrement, id_categoria integer, id_usuario integer, nombre text, ingredientes text, instrucciones text, imagen blob, foreign key (id_categoria) references categoria(id), foreign key (id_usuario) references usuario(id))";
    String tablaCategoria = "create table if not exists categoria(id integer primary key autoincrement, nombre text, id_conAlcohol integer, id_sinAlcohol integer)";

    public daoUsuario(Context c){
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        sql.execSQL(tablaReceta);
        sql.execSQL(tablaCategoria);
        u=new Usuario();

        if (getCategoriaCount("Con Alcohol") == 0) {
            insertarCategoria("Con Alcohol");
        }
        if (getCategoriaCount("Sin Alcohol") == 0) {
            insertarCategoria("Sin Alcohol");
        }
    }

    private int getCategoriaCount(String nombreCategoria) {
        Cursor cursor = sql.rawQuery("SELECT COUNT(*) FROM categoria WHERE nombre = ?", new String[]{nombreCategoria});
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    private void insertarCategoria(String nombreCategoria) {
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombreCategoria);
        long resultado = sql.insert("categoria", null, cv);
        if (resultado == -1) {
            Log.e("daoUsuario", "Error al insertar categoría: " + nombreCategoria);
        } else {
            Log.d("daoUsuario", "Categoría insertada exitosamente: " + nombreCategoria);
        }
    }



    public boolean insertarUsuario(Usuario u){
        if (buscar(u.getUsuario()) == 0){
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getUsuario());
            cv.put("password", u.getPassword());
            cv.put("nombre", u.getNombre());
            cv.put("apellido", u.getApellido());
            return (sql.insert("usuario", null, cv) > 0);
        } else {
            return false;
        }
    }

    public int buscar(String u){
        int x = 0;
        lista = selectUsuarios();
        for (Usuario us : lista) {
            if (us.getUsuario().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuarios(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()){
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setApellido(cr.getString(4));
                lista.add(u);
            } while (cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p){
        int a = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()){
            do {
                if (cr.getString(1).equals(u) && cr.getString(2).equals(p)){
                    a++;
                }
            } while (cr.moveToNext());
        }
        return a;
    }

    public Usuario getUsuario(String u, String p){
        lista = selectUsuarios();
        for (Usuario us : lista) {
            if (us.getUsuario().equals(u) && us.getPassword().equals(p)){
                return us;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int id){
        lista = selectUsuarios();
        for (Usuario us : lista) {
            if (us.getId() == id){
                return us;
            }
        }
        return null;
    }

    public boolean updateUsuario(Usuario u){
        ContentValues cv = new ContentValues();
        cv.put("usuario", u.getUsuario());
        cv.put("password", u.getPassword());
        cv.put("nombre", u.getNombre());
        cv.put("apellido", u.getApellido());
        return (sql.update("usuario", cv, "id=" + u.getId(), null) > 0);
    }

    public boolean deleteUsuario(int id) {
        return (sql.delete("usuario", "id=?", new String[]{String.valueOf(id)}) > 0);
    }

    public void insertarReceta(int idCategoria, int idUsuario, String nombre, String ingredientes, String instrucciones, byte[] imagenBytes) {
        ContentValues cv = new ContentValues();
        cv.put("id_categoria", idCategoria);
        cv.put("id_usuario", idUsuario);
        cv.put("nombre", nombre);
        cv.put("ingredientes", ingredientes);
        cv.put("instrucciones", instrucciones);
        cv.put("imagen", imagenBytes);
        sql.insert("receta", null, cv);
    }

    public void close() {
        if (sql != null) {
            sql.close();
        }
    }
}




