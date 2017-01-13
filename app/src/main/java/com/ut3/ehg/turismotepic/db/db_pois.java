package com.ut3.ehg.turismotepic.db;

/**
 * Created by LAB-DES-05 on 27/09/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class db_pois extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="turismo.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "pois";
    public static final String COLUMN_NAME_ID = "id_poi";
    public static final String COLUMN_NAME_ID_CATEGORIA = "id_categoria";
    public static final String COLUMN_NAME_POI = "nombre";
    public static final String COLUMN_NAME_SHORT_DESCRIPCION = "descripcion_corta";
    public static final String COLUMN_NAME_LONG_DESCRIPCION = "descripcion_larga";
    public static final String COLUMN_NAME_LAT = "latitud";
    public static final String COLUMN_NAME_LON = "longitud";
    public static final String COLUMN_NAME_HORARIO = "horario";
    public static final String COLUMN_NAME_CALF = "calificacion";
    public static final String COLUMN_NAME_COSTO = "costo";
    public static final String COLUMN_NAME_COSTOAPROX = "costo_aprox";
    public static final String COLUMN_NAME_FOTO = "foto";


    //--------------------------------Insert Table Query
    public static final String SQL_INSERT_ENTRIES = "INSERT INTO `pois` (`id_poi`, `id_categoria`, `nombre`,`descripcion_larga`,`latitud`, `longitud`, `horario`, `calificacion`, `costo`, `foto`) " +
            "VALUES " +
            "(01,3,'Catedral ','Templo católico sede de la diócesis de Tepic. Advocación Asunción de María.','21.512026','-104.891528','7:00 a 21:00 hrs','0','Entrada libre','f01_01'),"+
            "(02,1,'Hotel Fray Junípero Serra ','Hotel céntrico con restaurante, bar, gimnasio y aparcamiento gratuito.','21.511643','-104.89183','10:00-14:00 hrs, 16:00-19:00hrs','0','Variable','f02_01'),"+
            "(03,4,'Casa Museo de Juan Escutia ','Exposición permanente donde se representa la vida del cadete y su lucha por la defensa de nuestra bandera nacional.','21.510455','-104.890799','10:00-14:00 hrs, 16:00-19:00hrs','0','Entrada libre','f03_01'),"+
            "(04,4,'Casa Mueso de Amado Nervo ','Recinto dedicado al poeta mexicano, construido en la casa donde este nació.','21.512446','-104.890477','10:00-14:00 hrs, 16:00-19:00hrs','0','Entrada libre','f04_01'),"+
            "(05,3,'Pérgola (Plaza principal) ','Pérgola de estilo eclético del siglo XX.','21.512184','-104.892335','Libre acceso','0','Libre acceso','f05_01'),"+
            "(06,3,'Fuente de las ranas (Plaza principal) ','En 1952 se instaló la actual fuente de delfines y una segunda fuente, la Fuente de las Ranas.','21.512084','-104.892021','Libre acceso','0','Libre acceso','f06_01'),"+
            "(07,3,'Fuente de Delfines (Plaza principal) ','En 1952 se instaló la actual fuente de delfines y una segunda fuente, la Fuente de las Ranas.','21.512256','-104.892497','Libre acceso','0','Libre acceso','f07_01'),"+
            "(08,4,'Escuela Superior de Música ','Programa sustantivo del Consejo Estatal para le Cultura y las Artes de Nayarit.','21.510975','-104.890382','16:00-19:00','0','Variable','f08_01'),"+
            "(09,4,'Casa Fenelón ','Era una de las casa más hermosas y elegantes. Es de estilo neoclásico. ','21.511076','-104.890595','Sin acceso','0','Sin acceso','f09_01'),"+
            "(10,3,'Busto de Carranza (Plaza antigua) ','Busto de Venustiano Carranza ubicado en la Plaza Antigua.','21.510718','-104.891028','Libre acceso','0','Libre acceso','f10_01'),"+
            "(11,4,'Museo Regional INAH','Es un edificio del siglo XVIII alberga en su parte histórica siete salas de exposiciones.','21.509988','-104.892426','9:00 - 18:00','0','50','f11_01'),"+
            "(12,10,'Oficinas regionales del INAH ','Oficinas Estatales del Instituto Nacional de Antropología e Historia.','21.510878','-104.890568','9:00 - 18:00','0','Entrada libre','f12_01'),"+
            "(13,10,'CECUPI','Centro Estatal de Culturas Populares e Indígenas.','21.510285','-104.892396','10:00-14:00 hrs, 16:00-19:00hrs','0','Entrada libre','f13_01'),"+
            "(14,10,'Centro de Arte Contemporáneo del Bicentenario Emilia Ortíz ','Exhibe colecciones permanentes del acervo cultural del estado.','21.510912','-104.891872','10:00-14:00 hrs, 16:00-19:00hrs','0','Entrada libre','f14_01'),"+
            "(15,9,'El cerro de la Cruz ','Desde la cima se pueden observar paisajes de la ciudad de Tepic.','21.533983','-104.883625','6:00-20:00 hrs','0','Entrada libre','f15_01'),"+
            "(16,9,'Parque La Loma ','Excelente y económica opción para disfrutar el descanso y la diversión con la familia.','21.504015','-104.899448','Todo el día','0','Entrada libre','f16_01'),"+
            "(17,9,'La Alameda ','Ofrece una vista bonita a la hora de correr y también cuenta con juegos para ir con la familia.','21.510335','-104.900737','Todo el día','0','Entrada libre','f17_01'),"+
            "(18,10,'Estadio Arena Cora ',' Estadio multifuncional utilizado principalmente para partidos de fútbol y es sede del Club Deportivo Tepic.','21.492419','-104.801467','Por evento','0','Variable','f18_01'),"+
            "(19,10,'Palacio de Gobierno ','Construido entre 1870 y 1885 en un proyecto que pretendía ser la penitenciaría dela ciudad.','21.507507','-104.893879','9:00 - 15:00 hrs','0','Entrada libre','f19_01'),"+
            "(20,9,'Plaza Bicentenario ','Es el espacio que se localiza frente al Palacio de Gobierno.','21.507188','-104.893074','Todo el día','0','Entrada libre','f20_01'),"+
            "(21,8,'Plaza Forum ','Centro comercial cuenta con la presencia de grandes tiendas departamentales de prestigio.','21.491959','-104.865983','11:00 - 21:00 hrs','0','Entrada libre','f21_01'),"+
            "(22,10,'Central de Autobuses de Tepic ','Gran flujo de pasajeros de Sinaloa a Jalisco. Ofrece diversas líneas que operan en la terminal.','21.498444','-104.88677','Todo el día','0','Variable','f22_01'),"+
            "(23,5,'Banco Santander Catedral ','Banco Santander ubicado al lado de la Catedral de Tepic.','21.512399','-104.891343','8:30 - 16:00 hrs','0','Entrada libre','f23_01'),"+
            "(24,5,'Banco BBVA Bancomer ','Banco BBVA Bancomer ubicado en Av. México.','21.510618','-104.892312','8:30 - 16:00 hrs','0','Entrada libre','f24_01'),"+
            "(25,5,'Banco Banamex ','Banco Banamex ubicado en Av. México.','21.509944','-104.892264','9:00 - 16:00 hrs','0','Entrada libre','f25_01'),"+
            "(26,5,'Banco Banorte ','Banco Banorte ubicado en Av. México.','21.512819','-104.892119','8:30 - 16:00 hrs','0','Entrada libre','f26_01'),"+
            "(27,5,'Banco HSBC ','Banco HSBC ubicado en la plaza principal de Tepic.','21.512194','-104.892861','8:30 - 16:00 hrs','0','Entrada libre','f27_01'),"+
            "(28,5,'Scotiabank ','Banco Scotiabank ubicado en calle Hidalgo.','21.511046','-104.892325','8:30 - 16:00 hrs','0','Entrada libre','f28_01'),"+
            "(29,7,'Liverpool ','Tiendas departamentales enfocadas al consumidor de ingreso medio y alto.','21.491712','-104.865276','11:00 - 21:00 hrs','0','Entrada libre','f29_01'),"+
            "(30,7,'Oxxo ','Tienda de autoservicio.','21.511403','-104.892637','Todo el día','0','Entrada libre','f30_01'),"+
            "(31,7,'Salinas & Rocha ','Empresa dedicada a la venta de muebles y accesorios para el hogar.','21.511058','-104.892761','9:00 - 21:00 hrs','0','Entrada libre','f31_01'),"+
            "(32,7,'Modatelas ',' Telas para Vestir y Adornar tu Hogar así como una Gran Cantidad de Productos de Mercería, Confección.','21.511106','-104.892974','8:00 - 21:00 hrs','0','Entrada libre','f32_01'),"+
            "(33,7,'Coppel Canada México ','Es una cadena comercial de tiendas departamentales de ventas a través del otorgamiento de créditos.','21.510821','-104.892217','8:00 - 21:00 hrs','0','Entrada libre','f33_01'),"+
            "(34,6,'Farmacias Benavides ','Venta a detalle de productos de salud y bienestar.','21.510982','-104.892138','8:00 - 21:00 hrs','0','Entrada libre','f34_01'),"+
            "(35,6,'Farmacias El Fénix ','Venta a detalle de productos de salud y bienestar.','21.511281','-104.89175','8:00 - 21:00 hrs','0','Entrada libre','f35_01'),"+
            "(36,6,'Farmacias Sufacen ','Venta de medicinas de patente, medicamentos genéricos y medicamentos controlados.','21.512074','-104.895249','8:00 - 21:00 hrs','0','Entrada libre','f36_01'),"+
            "(37,2,'Tower Pizzas ','Cafetería con principal enfoque en pizzas.','21.509889','-104.892024','11:00 - 21:00 hrs','0','Entrada libre','f37_01'),"+
            "(38,2,'KFC ','Franquicia de restaurantes de comida rápida especializada en pollo frito.','21.511975','-104.892988','11:00 - 21:00 hrs','0','Entrada libre','f38_01'),"+
            "(39,2,'Fresh Salads ','Restaurante de ensaladas con un excelente y rápido servicio, ingredientes frescos y comida saludable.','21.511718','-104.892119','11:00 - 21:00 hrs','0','Entrada libre','f39_01'),"+
            "(40,2,'Casa Tigre ','Restaurante bufete mexicano para desayunar.','21.510665','-104.890114','8:00 - 13:00','0','115','f40_01'),"+
            "(41,10,'Sanitarios','Baños ubicados en la Presidencia Municipal.','21.512484','-104.893123','9:00 - 16:30 hrs','0','Entrada libre','f41_01'),"+
            "(42,10,'CICESE','Centro de Investigación Científica y de Educación Superior de Ensenada.','21.484181','-104.848551','8:00 - 16:30 hrs','0','Sin acceso','f42_01'),"+
            "(43,6,'Farmacia','Centro de Investigación Científica y de Educación Superior de Ensenada.','21.481896','-104.844634','8:00 - 16:30 hrs','1','Sin acceso','f42_01'),"+
            "(44,4,'Musero interactivo','Centro de Investigación Científica y de Educación Superior de Ensenada.','21.482005','-104.849473','8:00 - 16:30 hrs','2','Sin acceso','f42_01'),"+
            "(45,10,'Puerta de Hierro','Centro de Investigación Científica y de Educación Superior de Ensenada.','21.480352','-104.849279','8:00 - 16:30 hrs','3','Sin acceso','f42_01'),"+
            "(46,10,'INSA','Centro de Investigación Científica y de Educación Superior de Ensenada.','21.485444','-104.846511','8:00 - 16:30 hrs','4','Sin acceso','f42_01')";

















    //--------------------------------Delete Table Query
    public static final String SQL_DELETE_ENTRIES = " DROP TABLE IF EXIST " + TABLE_NAME;
    //--------------------------------Create Table Query
    public static final String CREATE_TABLE = " create table "
            + TABLE_NAME + " ( "
            + COLUMN_NAME_ID
            + " integer primary key, "
            + COLUMN_NAME_ID_CATEGORIA
            + " integer, "
            + COLUMN_NAME_POI
            + " text, "
            + COLUMN_NAME_SHORT_DESCRIPCION
            + " text, "
            + COLUMN_NAME_LONG_DESCRIPCION
            + " text, "
            + COLUMN_NAME_LAT
            + " text, "
            + COLUMN_NAME_LON
            + " text, "
            + COLUMN_NAME_HORARIO
            + " text, "
            + COLUMN_NAME_CALF
            + " text, "
            + COLUMN_NAME_COSTO
            + " text, "
            + COLUMN_NAME_COSTOAPROX
            + " text, "
            + COLUMN_NAME_FOTO
            + " text )";


    public db_pois(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(db_pois.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public ArrayList<String> obtenerPois(int cat){
        ArrayList<String> lista=new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM "+TABLE_NAME+" WHERE id_categoria="+cat;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_POI));
                    int id = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ID));
                    lista.add(name);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return lista;
    }

    public ArrayList<String> obtenerPoisId(int cat){
        ArrayList<String> lista=new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM "+TABLE_NAME+" WHERE id_categoria="+cat;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String id = Integer.toString(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ID)));
                    lista.add(id);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return lista;
    }

}
