package com.ut3.ehg.turismotepic.db;

/**
 * Created by LAB-DES-05 on 27/09/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class db_usuarios extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="turismo.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "usuarios";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_USUARIO = "usuario";
    public static final String COLUMN_NAME_PASS = "pass";
    public static final String COLUMN_NAME_EDAD = "edad";
    public static final String COLUMN_NAME_SEXO = "sexo";
    public static final String COLUMN_NAME_ORIGEN = "origen";
    public static final String COLUMN_NAME_OTRO_ORIGEN = "otro_origen";
    public static final String COLUMN_NAME_MOTIVO = "motivo";
    public static final String COLUMN_NAME_ACCOMPANYING = "accompanying";

    //--------------------------------Insert Table Query
    public static final String SQL_INSERT_ENTRIES = "INSERT INTO `usuarios` (`id`, `usuario`, `pass`, `edad`, `sexo`, `origen`, `motivo`, `accompanying`) VALUES (1, 'edwin','123','24','Hombre','Nayarit','Placer','Familia')";
    //--------------------------------Delete Table Query
    public static final String SQL_DELETE_ENTRIES = " DROP TABLE IF EXIST " + TABLE_NAME;
    //--------------------------------Create Table Query
    public static final String CREATE_TABLE = " create table "
            + TABLE_NAME + " ( "
            + COLUMN_NAME_ID
            + " integer primary key, "
            + COLUMN_NAME_USUARIO
            + " text, "
            + COLUMN_NAME_PASS
            + " text, "
            + COLUMN_NAME_EDAD
            + " text, "
            + COLUMN_NAME_SEXO
            + " text, "
            + COLUMN_NAME_ORIGEN
            + " text, "
            + COLUMN_NAME_MOTIVO
            + " text, "
            + COLUMN_NAME_ACCOMPANYING
            + " text )";


    public db_usuarios(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(db_usuarios.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

}
