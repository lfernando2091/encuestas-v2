package saganet.mx.com.cgf.DataBase.Controler;

import android.provider.BaseColumns;

/**
 * Created by LuisFernando on 15/05/2017.
 */

public class Colums implements BaseColumns {
    private static final String CREATE="CREATE TABLE IF NOT EXISTS ";
    public static final String SEQ="seq";
    public static final String NAME="name";
    public static final String COLUMN_VERSION="version";

    /*TABLA PREGUNTA*/
    public static final String TABLE_PREGUNTA="preguntas";
    public static final String COLUMN_TIPO="tipo";
    public static final String COLUMN_ID_ENCUESTA="idEncuesta";
    public static final String COLUMN_ACCION="accion";
    public static final String COLUMN_PREGUNTA="pregunta";
    public static final String COLUMN_ORDEN="orden";
    public static final String CREATE_INDEX_PREGUNTA_ID=
            "CREATE INDEX index_"+
                    TABLE_PREGUNTA+
                    "_id ON "+
                    TABLE_PREGUNTA+
                    " (_id ASC);";
    public static final String CREATE_TABLE_PREGUNTA=
            CREATE + TABLE_PREGUNTA +
                    "(" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_TIPO + " TEXT NOT NULL," +
                    COLUMN_ID_ENCUESTA + " INTEGER NOT NULL," +
                    COLUMN_PREGUNTA + " TEXT NOT NULL," +
                    COLUMN_ACCION + " TEXT NOT NULL DEFAULT (0)," +
                    COLUMN_ORDEN + " INTEGER NOT NULL," +
                    COLUMN_VERSION + " INTEGER NOT NULL)" ;
    /*TABLA RESPUESTA*/
    public static final String TABLE_RESPUESTA="respuestas";
    public static final String COLUMN_ID_PREGUNTA="idPregunta";
    public static final String COLUMN_RESPUESTA="respuesta";
    public static final String COLUMN_VALOR="valor";
    public static final String COLUMN_ID_ENCUESTA_ESTADO="idEncuestaEstado";
    public static final String CREATE_INDEX_RESPUESTA_ID=
            "CREATE INDEX index_"+
                    TABLE_RESPUESTA+
                    "_id ON "+
                    TABLE_RESPUESTA+
                    " (_id ASC);";
    public static final String CREATE_TABLE_RESPUESTA=
            CREATE + TABLE_RESPUESTA +
                    "(" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_ID_PREGUNTA + " TEXT NOT NULL," +
                    COLUMN_RESPUESTA + " INTEGER NOT NULL," +
                    COLUMN_ACCION + " TEXT NOT NULL," +
                    COLUMN_VALOR + " TEXT NOT NULL," +
                    COLUMN_ID_ENCUESTA_ESTADO + " TEXT NOT NULL," +
                    COLUMN_ORDEN + " INTEGER NOT NULL," +
                    COLUMN_VERSION + " INTEGER NOT NULL)" ;
}
