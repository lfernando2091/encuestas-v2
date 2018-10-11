package saganet.mx.com.cgf.DataBase.Catalogo;

/**
 * Created by LuisFernando on 15/05/2017.
 */

public enum AccionRespuestaDO {
    SIGUIENTE("SIGUIENTE"),
    IR_PREGUNTA("IR PREGUNTA"),
    TERMINAR("TERMINAR");

    private final String nombre;

    private AccionRespuestaDO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
