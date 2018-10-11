package saganet.mx.com.cgf.DataBase.Tablas;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import saganet.mx.com.cgf.DataBase.Catalogo.AccionRespuestaDO;
import saganet.mx.com.cgf.DataBase.Controler.Colums;
import saganet.mx.com.cgf.DataBase.Controler.Component;

/**
 * Created by LuisFernando on 15/05/2017.
 */

public class RespuestaEO extends Component {
    private int _id;
    private int idPregunta;
    private String respuesta;
    private AccionRespuestaDO accion;
    private int orden;
    private int valor;
    private int idEncuestaEstado;
    private int version;

    public RespuestaEO(int _id, int idPregunta, String respuesta, AccionRespuestaDO accion, int orden, int valor, int idEncuestaEstado, int version) {
        this._id = _id;
        this.idPregunta = idPregunta;
        this.respuesta = respuesta;
        this.accion = accion;
        this.orden = orden;
        this.valor = valor;
        this.idEncuestaEstado = idEncuestaEstado;
        this.version = version;
    }

    @Override
    public ContentValues contentValues() {
        ContentValues v=new ContentValues();
        v.put(Colums._ID,get_id());
        v.put(Colums.COLUMN_ID_PREGUNTA,getIdPregunta());
        v.put(Colums.COLUMN_RESPUESTA,getRespuesta());
        v.put(Colums.COLUMN_ACCION,getAccion().getNombre());
        v.put(Colums.COLUMN_ORDEN,getOrden());
        v.put(Colums.COLUMN_VALOR,getValor());
        v.put(Colums.COLUMN_ID_ENCUESTA_ESTADO,getIdEncuestaEstado());
        v.put(Colums.COLUMN_VERSION,getVersion());
        return v;
    }

    @Override
    public Component JSONValues(JSONObject jsonObject) throws JSONException {
        return null;
    }

    @Override
    public String tableName() {
        return Colums.TABLE_RESPUESTA;
    }

    @Override
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public AccionRespuestaDO getAccion() {
        return accion;
    }

    public void setAccion(AccionRespuestaDO accion) {
        this.accion = accion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getIdEncuestaEstado() {
        return idEncuestaEstado;
    }

    public void setIdEncuestaEstado(int idEncuestaEstado) {
        this.idEncuestaEstado = idEncuestaEstado;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
