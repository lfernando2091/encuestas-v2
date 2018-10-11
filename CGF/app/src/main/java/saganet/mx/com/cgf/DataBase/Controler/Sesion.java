package saganet.mx.com.cgf.DataBase.Controler;

import android.content.Context;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import saganet.mx.com.cgf.DataBase.Catalogo.AccionRespuestaDO;
import saganet.mx.com.cgf.DataBase.Catalogo.TipoPreguntaDO;
import saganet.mx.com.cgf.DataBase.Tablas.PreguntaEO;
import saganet.mx.com.cgf.DataBase.Tablas.RespuestaEO;
import saganet.mx.com.cgf.Variables.CgfC;
import saganet.mx.com.cgf.logger.LoggerC;

/**
 * Created by LuisFernando on 15/05/2017.
 */

public class Sesion extends SQLiteOpenHelper {
    LoggerC log=new LoggerC(Sesion.class);
    private static Sesion instance;
    Context c=null;

    public static synchronized Sesion getHelper(Context context) {
        if (instance == null)
            instance = new Sesion(context);
        return instance;
    }

    public Sesion(Context context) {
        super(context, CgfC.DATA_BASE_NAME, null, CgfC.DATA_BASE_VERSION);
        c=context;
        SQLiteDatabase.loadLibs(context);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        CREATE_TABLES(sqLiteDatabase);
        SVE_DEFAULT_DATA(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Colums.TABLE_PREGUNTA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Colums.TABLE_RESPUESTA);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    SQLiteDatabase getReadableDatabase() {
        return(super.getReadableDatabase(CgfC.DATA_BASE_SECURITY));
    }

    SQLiteDatabase getWritableDatabase() {
        return(super.getWritableDatabase(CgfC.DATA_BASE_SECURITY));
    }

    private  void CREATE_TABLES(SQLiteDatabase db) {
        db.execSQL(Colums.CREATE_TABLE_PREGUNTA);
        db.execSQL(Colums.CREATE_INDEX_PREGUNTA_ID);
        db.execSQL(Colums.CREATE_TABLE_RESPUESTA);
        db.execSQL(Colums.CREATE_INDEX_RESPUESTA_ID);
    }

    private void SVE_DEFAULT_DATA(SQLiteDatabase db){
        PreguntaEO preguntaEO= new PreguntaEO(1, TipoPreguntaDO.ABIERTA_TEXTO, 1, "¿Pregunta 1?",1, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(2, TipoPreguntaDO.CERRADA_UNICA, 1, "¿Pregunta 2?",2, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(3, TipoPreguntaDO.CERRADA_MULTIPLE, 1, "¿Pregunta 3?",3, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(4, TipoPreguntaDO.ABIERTA_PARRAFO, 1, "¿Pregunta 4?",4, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(5, TipoPreguntaDO.ABIERTA_NUMERICO, 1, "¿Pregunta 5?",5, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(6, TipoPreguntaDO.ABIERTA_NUMERICO_RANGO, 1, "¿Pregunta 6?",6, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(7, TipoPreguntaDO.ABIERTA_TELEFONO, 1, "¿Pregunta 7?",7, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(8, TipoPreguntaDO.ABIERTA_EMAIL, 1, "¿Pregunta 8?",8, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(9, TipoPreguntaDO.ABIERTA_FECHA_RANGO, 1, "¿Pregunta 9?",9, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(10, TipoPreguntaDO.CERRADA_FECHA, 1, "¿Pregunta 10?",10, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(11, TipoPreguntaDO.CERRADA_FECHA_RANGO, 1, "¿Pregunta 11?",11, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(12, TipoPreguntaDO.CERRADA_HORA, 1, "¿Pregunta 12?",12, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(13, TipoPreguntaDO.CERRADA_HORA_RANGO, 1, "¿Pregunta 13?",13, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(14, TipoPreguntaDO.FACE_STATE, 1, "¿Pregunta 14?",14, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(15, TipoPreguntaDO.LIKE_DISLIKE, 1, "¿Pregunta 15?",15, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(16, TipoPreguntaDO.CERRADA_NUMERICO_RANGO, 1, "¿Pregunta 16?",16, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        //--------------------------------------------------------------------------------------------------
        preguntaEO= new PreguntaEO(17, TipoPreguntaDO.ITERABLE, 1, "¿Pregunta Fila 1?",1,17, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(18, TipoPreguntaDO.COMPONENTE, 1, "¿Pregunta Columna 1?",1,18, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(19, TipoPreguntaDO.COMPONENTE, 1, "¿Pregunta Columna 2?",1,19, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(20, TipoPreguntaDO.COMPONENTE, 1, "¿Pregunta Columna 3?",1,20, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(21, TipoPreguntaDO.ITERABLE, 1, "¿Pregunta Fila 2?",1,21, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        preguntaEO= new PreguntaEO(22, TipoPreguntaDO.ITERABLE, 1, "¿Pregunta Fila 3?",1,22, 1);
        db.insert(preguntaEO.tableName(), null, preguntaEO.contentValues());
        //---------------------------------------------------------------------------------------------------

        RespuestaEO respuestaEO= new RespuestaEO(1,2,"Respuesta 1", AccionRespuestaDO.SIGUIENTE,1, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(2,2,"Respuesta 2", AccionRespuestaDO.SIGUIENTE,2, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(3,2,"Respuesta 3", AccionRespuestaDO.SIGUIENTE,3, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());

        respuestaEO= new RespuestaEO(4,3,"Respuesta 1", AccionRespuestaDO.SIGUIENTE,1, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(5,3,"Respuesta 2", AccionRespuestaDO.SIGUIENTE,2, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(6,3,"Respuesta 3", AccionRespuestaDO.TERMINAR,3, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());

        respuestaEO= new RespuestaEO(7,14,"ALEGRE", AccionRespuestaDO.SIGUIENTE,1, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(8,14,"NEUTRAL", AccionRespuestaDO.SIGUIENTE,2, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(9,14,"TRIZTE", AccionRespuestaDO.SIGUIENTE,3, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(10,14,"ENOJADO", AccionRespuestaDO.SIGUIENTE,4, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(11,14,"SORPRENDIDO", AccionRespuestaDO.SIGUIENTE,5, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(12,14,"QUE", AccionRespuestaDO.SIGUIENTE,6, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(13,14,"MUYTRIZTE", AccionRespuestaDO.SIGUIENTE,7, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());

        respuestaEO= new RespuestaEO(14,15,"MEGUSTA", AccionRespuestaDO.SIGUIENTE,1, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(15,15,"NOMEGUSTA", AccionRespuestaDO.SIGUIENTE,2, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());

        respuestaEO= new RespuestaEO(16,16,"12345", AccionRespuestaDO.SIGUIENTE,1, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
        respuestaEO= new RespuestaEO(17,16,"123456789", AccionRespuestaDO.SIGUIENTE,2, 0,0,1);
        db.insert(respuestaEO.tableName(), null, respuestaEO.contentValues());
    }

    public List<PreguntaEO> getPreguntas(){
        List<PreguntaEO> p= new ArrayList<PreguntaEO>();
        String rawQuery = "SELECT "+
                Colums._ID+","+
                Colums.COLUMN_TIPO+","+
                Colums.COLUMN_ID_ENCUESTA+","+
                Colums.COLUMN_PREGUNTA+","+
                Colums.COLUMN_ORDEN+","+
                Colums.COLUMN_VERSION +
                " FROM " + Colums.TABLE_PREGUNTA+
                " WHERE " + Colums.COLUMN_ID_ENCUESTA+"=1" +
                " ORDER BY " +Colums.COLUMN_ORDEN;
        log.printf("SQL:: " + rawQuery);
        Cursor cursor = this.getReadableDatabase().rawQuery(rawQuery,null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    PreguntaEO t= new PreguntaEO(
                            Integer.valueOf(cursor.getString(cursor.getColumnIndex(Colums._ID))),
                            TipoPreguntaDO.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_TIPO))),
                            Integer.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_ID_ENCUESTA))),
                            String.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_PREGUNTA))),
                            Integer.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_ORDEN))),
                            Integer.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_VERSION)))
                    );
                    if(t.getTipo()!=TipoPreguntaDO.ITERABLE)t.setListado(getRespuesta(String.valueOf(t.get_id())));
                    p.add(t);
                }while (cursor.moveToNext());
            }
        }
        return  p;
    }

    private List<RespuestaEO> getRespuesta(String idPregunta){
        List<RespuestaEO> p= new ArrayList<RespuestaEO>();
        String rawQuery = "SELECT "+
                Colums._ID+","+
                Colums.COLUMN_ID_PREGUNTA+","+
                Colums.COLUMN_RESPUESTA+","+
                Colums.COLUMN_ACCION+","+
                Colums.COLUMN_VALOR+","+
                Colums.COLUMN_ORDEN+","+
                Colums.COLUMN_ID_ENCUESTA_ESTADO+","+
                Colums.COLUMN_VERSION +
                " FROM " + Colums.TABLE_RESPUESTA+
                " WHERE " + Colums.COLUMN_ID_PREGUNTA+"="+idPregunta+
                " ORDER BY " + Colums.COLUMN_ORDEN;
        log.printf("SQL:: " + rawQuery);
        Cursor cursor = this.getReadableDatabase().rawQuery(rawQuery,null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    RespuestaEO t= new RespuestaEO(
                            Integer.valueOf(cursor.getString(cursor.getColumnIndex(Colums._ID))),
                            Integer.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_ID_PREGUNTA))),
                            String.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_RESPUESTA))),
                            AccionRespuestaDO.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_ACCION))),
                            Integer.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_ORDEN))),
                            Integer.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_VALOR))),
                            Integer.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_ID_ENCUESTA_ESTADO))),
                            Integer.valueOf(cursor.getString(cursor.getColumnIndex(Colums.COLUMN_VERSION)))
                    );
                    p.add(t);
                }while (cursor.moveToNext());
            }
        }
        return  p;
    }
}
