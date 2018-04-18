package com.example.win10.fbaseuno.objetos;

public class Alumno {
    String nombre, nControl;

    public Alumno(String nombre, String nControl) {
        this.nombre = nombre;
        this.nControl = nControl;
    }

    public Alumno() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getnControl() {
        return nControl;
    }

    public void setnControl(String nControl) {
        this.nControl = nControl;
    }
}
