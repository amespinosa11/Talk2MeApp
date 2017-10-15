package com.talk2me.talk2me;

import java.io.Serializable;

/**
 * Created by Ana Maria on 15/10/2017.
 */

public class ContactoEntity implements Serializable
{
    private String nombre;

    private String numeroCelular;

    public ContactoEntity(String pNombre, String pNumeroCelular)
    {
        this.nombre = pNombre;
        this.numeroCelular = pNumeroCelular;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public String getNombre() {
        return nombre;
    }
}
