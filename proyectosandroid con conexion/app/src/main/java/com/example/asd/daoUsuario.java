package com.example.asd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class daoUsuario {

    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    static SQLiteDatabase sql;
    String bd = "Coctelis.db";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, usuario text, password text, nombre text, apellido text, estado_sesion integer)";
    String tablaReceta = "create table if not exists receta(id integer primary key autoincrement, id_categoria integer, id_usuario integer, nombre text, ingredientes text, instrucciones text, imagen blob, foreign key (id_categoria) references categoria(id), foreign key (id_usuario) references usuario(id))";
    String tablaCategoria = "create table if not exists categoria(id integer primary key autoincrement, nombre text)";
    public daoUsuario(Context c){
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        sql.execSQL(tablaReceta);
        sql.execSQL(tablaCategoria);
        u=new Usuario();
    }
    public boolean insertarUsuario(Usuario u){
        if (buscar(u.getUsuario())==0){
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getUsuario());
            cv.put("password", u.getPassword());
            cv.put("nombre", u.getNombre());
            cv.put("apellido", u.getApellido());
            return (sql.insert("usuario", null,cv)>0);

        }else {
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
        if (cr!=null && cr.moveToFirst()){
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
        if (cr!=null && cr.moveToFirst()){
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
            if (us.getId()==id){
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
        return (sql.update("usuario",cv, "id="+u.getId(),null)>0);

    }

    public boolean deleteUsuario(int id) {
        return (sql.delete("usuario", "id=?", new String[]{String.valueOf(id)}) > 0);
    }
    public int obtenerIdCategoria(String nombreCategoria) {
        Cursor cursor = sql.rawQuery("SELECT id FROM categoria WHERE nombre=?", new String[]{nombreCategoria});
        if (cursor != null && cursor.moveToFirst()) {
            int idCategoria = cursor.getInt(0);
            cursor.close();
            return idCategoria;
        }
        return -1; // Si no se encuentra la categoría, se devuelve -1 como valor predeterminado
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

    public ArrayList<Receta> selectRecetas() {
        ArrayList<Receta> listaRecetas = new ArrayList<>();
        listaRecetas.clear();
        Cursor cursor = sql.rawQuery("SELECT * FROM receta", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Receta receta = new Receta();
                receta.setId(cursor.getInt(0));
                receta.setIdCategoria(cursor.getInt(1));
                receta.setIdUsuario(cursor.getInt(2));
                receta.setNombre(cursor.getString(3));
                receta.setIngredientes(cursor.getString(4));
                receta.setInstrucciones(cursor.getString(5));
                byte[] imagenBytes = cursor.getBlob(6);
                receta.setImagen(imagenBytes);

                listaRecetas.add(receta);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaRecetas;
    }

    public void insertarCategoria(String nombreCategoria) {
        if (!existeCategoria(nombreCategoria)) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", nombreCategoria);
            sql.insert("categoria", null, cv);
        }
    }

    private boolean existeCategoria(String nombreCategoria) {
        Cursor cursor = sql.rawQuery("SELECT * FROM categoria WHERE nombre = ?", new String[] { nombreCategoria });
        return cursor.getCount() > 0;
    }

    public List<String> selectCategorias() {
        List<String> categorias = new ArrayList<>();
        Cursor cursor = sql.rawQuery("SELECT nombre FROM categoria", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String categoria = cursor.getString(0);
                categorias.add(categoria);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return categorias;
    }

    public void close() {
        if (sql != null && sql.isOpen()) {
            sql.close();
        }
    }

}

