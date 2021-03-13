package main.data;

import java.util.Date;

/**
 * Usuario
 */
public class Usuario {

    enum Rango {

    }

    private String email;
    private String nombre;
    private Date nacimiento;
    private Rango rang;
    private Date registro;
    private int edad;

    public Usuario(String email, String nombre, Date nacimiento, Rango rang, Date registro, int edad) {
        this.email = email;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.rang = rang;
        this.registro = registro;
        this.edad = edad;
    }

    public Usuario() {
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getNacimiento() {
        return this.nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Rango getRang() {
        return this.rang;
    }

    public void setRang(Rango rang) {
        this.rang = rang;
    }

    public Date getRegistro() {
        return this.registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}