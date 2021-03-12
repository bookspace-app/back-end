package com.bookspaceapp.model;

public class Configuracion {
    
    private static boolean activo;
    private static boolean modo; //False --> Nocturno, True --> Diurno
    private static boolean privado;

    //Constructores
    public Configuracion()
    //Por defecto
    {
        activo = true;
        modo = true;
        privado = true;
    }

    //Consultores
    public boolean getActivo()
    {
        return activo;
    }

    public boolean getModo()
    {
        return modo;
    }

    public boolean getPrivado()
    {
        return privado;
    }

    //Modificadores
    public boolean setActivo(boolean act)
    {
        return activo = act;
    }

    public boolean setModo(boolean mdo)
    {
        return modo = mdo;
    }

    public boolean setPrivado(boolean pvd)
    {
        return privado = pvd;
    }
}
