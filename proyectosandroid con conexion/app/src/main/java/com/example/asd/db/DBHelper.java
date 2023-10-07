package com.example.asd.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Coctelis.db";
    private static final String TABLE_USUARIO = "usuarios";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USUARIO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "correo TEXT NOT NULL," +
                "contrasena TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        onCreate(sqLiteDatabase);
    }
    public void agregarUsuarios(String nombre, String apellido, String correo, String contrasena){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd != null){
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("correo", correo);
            values.put("contrasena", contrasena);

            long result = bd.insert(TABLE_USUARIO, null, values);

            bd.close();
        }
    }
}
