package com.ut3.ehg.turismotepic.rc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.ut3.ehg.turismotepic.db.db_usuarios;

import static com.ut3.ehg.turismotepic.db.db_usuarios.*;



/**
 * Created by LAB-DES-05 on 07/10/2016.
 */

public class rc_usuarios {
    private final db_usuarios dbUsuarios;
    private SQLiteDatabase database;
    private final String TAG = this.getClass().getName();

    public rc_usuarios(Context context) { dbUsuarios = new db_usuarios(context); }
    public void open() throws SQLiteException { database = dbUsuarios.getWritableDatabase(); }
    public void close() { database.close(); }
    public void read(){ database = dbUsuarios.getReadableDatabase(); }
    /*------------------------------- Public Methods for RC_USER----------------------------------*/

    public void insertarUsuarios (String Usuario, String Pass, String Sexo, String Motivo, String Origen, String Acompañantes, String Edad){
        SQLiteDatabase db;

        database.insert(TABLE_NAME, null,generarContentValues(Usuario,Pass, Sexo, Motivo, Origen, Acompañantes,Edad));
    }

    public String checarUsuario(String usuario, String pass){
        String resultado = null;
        String[] args = new String[] {usuario, pass};
        Cursor c = database.rawQuery(" SELECT id FROM usuarios WHERE usuario=? and pass=?", args);
        if (c.moveToFirst()) {
            do {
                resultado= c.getString(0);
            } while(c.moveToNext());
        }
        return resultado;
    }

    public ContentValues generarContentValues(String Usuario, String Pass, String Sexo, String Motivo, String Origen, String Acompañantes, String Edad){
        ContentValues valores = new ContentValues();
        valores.put(COLUMN_NAME_USUARIO,Usuario);
        valores.put(COLUMN_NAME_PASS,Pass);
        valores.put(COLUMN_NAME_EDAD,Edad);
        valores.put(COLUMN_NAME_SEXO,Sexo);
        valores.put(COLUMN_NAME_ORIGEN,Origen);
        valores.put(COLUMN_NAME_MOTIVO,Motivo);
        valores.put(COLUMN_NAME_ACCOMPANYING,Acompañantes);
        return valores;
    }

}
