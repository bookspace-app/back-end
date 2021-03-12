package main.data;

import java.util.Date;

public class Mensaje {

    private final String contenido;
    private final Date fecha;
    private final Usuario usuario;
    private final Chat chat;

    public Mensaje() {
    }

    public Mensaje(String contenido, Date fecha, Usuario usuario, Chat chat) {
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.chat = chat;
    }

    public String getContenido() {
        return this.contenido;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Chat getChat() {
        return this.chat;
    }

    public boolean setContenido(String contenido) {
        return this.contenido = contenido;
    }

    public boolean setFecha(Date fecha) {
        return this.fecha = fecha;
    }

    public boolean setUsuario(Usuario usuario) {
        return this.usuario = usuario;
    }

    public boolean setChat(Chat chat) {
        return this.chat = chat;
    }
}
