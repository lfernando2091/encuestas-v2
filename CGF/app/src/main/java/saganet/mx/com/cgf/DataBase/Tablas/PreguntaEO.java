package saganet.mx.com.cgf.DataBase.Tablas;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import saganet.mx.com.cgf.DataBase.Catalogo.TipoPreguntaDO;
import saganet.mx.com.cgf.DataBase.Controler.Colums;
import saganet.mx.com.cgf.DataBase.Controler.Component;

/**
 * Created by LuisFernando on 15/05/2017.
 */

public class PreguntaEO extends Component{
    private int _id;
    private TipoPreguntaDO tipo;
    private int idEncuesta;
    private String pregunta;
    private int accion;
    private int orden;
    private int version;
    private List<RespuestaEO> listado;

    @Override
    public String toString() {
        return "PreguntaEO{" +
                "_id=" + _id +
                ", tipo=" + tipo +
                ", idEncuesta=" + idEncuesta +
                ", pregunta='" + pregunta + '\'' +
                ", accion=" + accion +
                ", orden=" + orden +
                ", version=" + version +
                ", listado=" + listado +
                '}';
    }

    public PreguntaEO(int _id, TipoPreguntaDO tipo, int idEncuesta, String pregunta, int accion, int orden, int version) {
        this._id = _id;
        this.tipo = tipo;
        this.idEncuesta = idEncuesta;
        this.pregunta = pregunta;
        this.accion = accion;
        this.orden = orden;
        this.version = version;
    }

    public PreguntaEO(int _id, TipoPreguntaDO tipo, int idEncuesta, String pregunta, int orden, int version) {
        this._id = _id;
        this.tipo = tipo;
        this.idEncuesta = idEncuesta;
        this.pregunta = pregunta;
        this.orden = orden;
        this.version = version;
    }

    @Override
    public ContentValues contentValues() {
        ContentValues v=new ContentValues();
        v.put(Colums._ID,get_id());
        v.put(Colums.COLUMN_TIPO,getTipo().getNombre());
        v.put(Colums.COLUMN_ID_ENCUESTA,getIdEncuesta());
        v.put(Colums.COLUMN_PREGUNTA,getPregunta());
        v.put(Colums.COLUMN_ACCION,getAccion());
        v.put(Colums.COLUMN_ORDEN,getOrden());
        v.put(Colums.COLUMN_VERSION,getVersion());
        return v;
    }

    @Override
    public Component JSONValues(JSONObject jsonObject) throws JSONException {
        return null;
    }

    @Override
    public String tableName() {
        return Colums.TABLE_PREGUNTA;
    }

    public List<RespuestaEO> getListado() {
        return listado;
    }

    public void setListado(List<RespuestaEO> listado) {
        this.listado = listado;
    }

    @Override
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public TipoPreguntaDO getTipo() {
        return tipo;
    }

    public void setTipo(TipoPreguntaDO tipo) {
        this.tipo = tipo;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }
}
