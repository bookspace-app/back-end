package main.data;

import java.util.Date;

public class Comentario {

    private int id;
    private String contenido;
    private Date fecha;

    public Comentario() {
    }

    public Comentario(int id, String contenido, Date fecha) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return this.contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
