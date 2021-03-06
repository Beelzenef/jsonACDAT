package com.example.jsonacdat.pojo;

/**
 * Created by usuario on 25/01/18.
 */

public class Contacto {

    private String nombre;
    private String direccion;
    private String email;

    private Telefono telefonos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public Telefono getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Telefono telefonos) {
        this.telefonos = telefonos;
    }
}
