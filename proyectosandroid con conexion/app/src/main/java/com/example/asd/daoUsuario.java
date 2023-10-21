package com.example.asd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuario {

    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "Coctelis.db";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, usuario text, password text, nombre text, apellido text, estado_sesion integer)";

    public daoUsuario(Context c){
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
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


}

