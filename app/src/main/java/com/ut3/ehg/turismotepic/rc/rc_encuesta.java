package com.ut3.ehg.turismotepic.rc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.ut3.ehg.turismotepic.db.db_encuesta;

import static com.ut3.ehg.turismotepic.db.db_encuesta.*;

/**
 * Created by LAB-DES-05 on 10/11/2016.
 */

public class rc_encuesta {
    private final db_encuesta dbEncuesta;
    private SQLiteDatabase database;
    private final String TAG = this.getClass().getName();

    public rc_encuesta(Context context) { dbEncuesta = new db_encuesta(context); }
    public void open() throws SQLiteException { database = dbEncuesta.getWritableDatabase(); }
    public void close() { database.close(); }
    public void read(){ database = dbEncuesta.getReadableDatabase(); }
    /*------------------------------- Public Methods for RC_USER----------------------------------*/

    //public void insertarPois (String Usuario, String Pass, String Sexo, String Motivo, String Origen, String Acompañantes, String Edad){
    //  SQLiteDatabase db;

    //   database.insert(TABLE_NAME, null,generarContentValues(Usuario,Pass, Sexo, Motivo, Origen, Acompañantes,Edad));
    //}


    public void enviarEncuesta (int idUser,float a,float b,float c,float d,float e){
        SQLiteDatabase db;

        database.insert(TABLE_NAME, null,generarContentValues(idUser,a,b,c,d,e));
    }

    public ContentValues generarContentValues(int idUser,float a,float b,float c,float d,float e){
        ContentValues valores = new ContentValues();
        valores.put(COLUMN_NAME_ID_USER,idUser);
        valores.put(COLUMN_NAME_Q1,a);
        valores.put(COLUMN_NAME_Q2,b);
        valores.put(COLUMN_NAME_Q3,c);
        valores.put(COLUMN_NAME_Q4,d);
        valores.put(COLUMN_NAME_Q5,e);
        return valores;
    }

}
