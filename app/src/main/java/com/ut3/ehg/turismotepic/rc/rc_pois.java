package com.ut3.ehg.turismotepic.rc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.ut3.ehg.turismotepic.db.db_pois;

import static com.ut3.ehg.turismotepic.db.db_usuarios.COLUMN_NAME_ACCOMPANYING;
import static com.ut3.ehg.turismotepic.db.db_usuarios.COLUMN_NAME_EDAD;
import static com.ut3.ehg.turismotepic.db.db_usuarios.COLUMN_NAME_MOTIVO;
import static com.ut3.ehg.turismotepic.db.db_usuarios.COLUMN_NAME_ORIGEN;
import static com.ut3.ehg.turismotepic.db.db_usuarios.COLUMN_NAME_PASS;
import static com.ut3.ehg.turismotepic.db.db_usuarios.COLUMN_NAME_SEXO;
import static com.ut3.ehg.turismotepic.db.db_usuarios.COLUMN_NAME_USUARIO;

/**
 * Created by LAB-DES-05 on 31/10/2016.
 */

public class rc_pois {
    private final db_pois dbPois;
    private SQLiteDatabase database;
    private final String TAG = this.getClass().getName();

    public rc_pois(Context context) { dbPois = new db_pois(context); }
    public void open() throws SQLiteException { database = dbPois.getWritableDatabase(); }
    public void close() { database.close(); }
    public void read(){ database = dbPois.getReadableDatabase(); }
    /*------------------------------- Public Methods for RC_USER----------------------------------*/

    //public void insertarPois (String Usuario, String Pass, String Sexo, String Motivo, String Origen, String Acompañantes, String Edad){
      //  SQLiteDatabase db;

     //   database.insert(TABLE_NAME, null,generarContentValues(Usuario,Pass, Sexo, Motivo, Origen, Acompañantes,Edad));
    //}

    public Cursor getDatos(String id){
        String[] args = new String[] {id};
        Cursor c = database.rawQuery(" SELECT * FROM pois WHERE id_poi=?",args);
        return c;
    }

    public Cursor getPois(){
        Cursor c =database.rawQuery("SELECT * FROM pois ", null);
        /*ArrayList<String> pois = new ArrayList<String>();
        while(!c.isAfterLast()) {
            pois.add(c.getString(c.getColumnIndex("name")));
            c.moveToNext();
        }*/
        //c.close();
        return c;
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
