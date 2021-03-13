package main.data;

enum Tema {
    FICCION,
    MISTERIO
}
public class Tematica {
    
    private Tema tema;

    public Tematica() {
    }

    public Tematica(Tema tema) {
        this.tema = tema;
    }

    public Tema getTema() {
        return this.tema;
    }

    public boolean setTema(Tema tema) {
        return this.tema = tema;
    }
}

