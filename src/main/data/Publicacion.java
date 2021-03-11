package main.data;

import java.util.Date;

public class Publicacion {

    private int id;
    private String titulo;
    private String contenido;
    private Date publicacion;

    public Publicacion(int id, String titulo, String contenido, Date publicacion) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.publicacion = publicacion;
    }

    public Publicacion() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return this.contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getPublicacion() {
        return this.publicacion;
    }

    public void setPublicacion(Date publicacion) {
        this.publicacion = publicacion;
    }

}
