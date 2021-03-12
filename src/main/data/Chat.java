package main.data;

import java.util.Date;

enum Idioma {
    CATALAN,
    ESPAÑOL,
    INGLES
}

public class Chat {

    private final Usuario usuario1 = new Usuario();
    private final Usuario usuario2 = new Usuario();
    private final Date nacimiento;
    private Idioma idioma;

    public Chat() {   
    }

    public Chat(Date nacimiento, Idioma idioma, Usuario usuario1, Usuario usuario2) {
        this.nacimiento = nacimiento;
        this.idioma = idioma;
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
    }

    public Idioma getIdioma() {
        return this.idioma;
    }

    public Date getNacimiento() {
        return this.nacimiento;
    }

    public Usuario getUsuario1() {
        return this.usuario1;
    }

    public Usuario getUsuario2() {
        return this.usuario2;
    }

    public boolean setIdioma(Idioma idioma) {
        return this.idioma = idioma;
    }

    public boolean setNacimiento(Date nacimiento) {
        return this.nacimiento = nacimiento;
    }

    public boolean setUsuario1(Usuario usuario1) {
        return this.usuario1 = usuario1;
    }

    public boolean setUsuario2(Usuario usuario2) {
        return this.usuario2 = usuario2;
    }
}
