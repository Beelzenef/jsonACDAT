package com.example.jsonacdat.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class Person {

    @SerializedName("contactos")
    @Expose
    private List<Contacto> contacts = null;

    public List<Contacto> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacto> contacts) {
        this.contacts = contacts;
    }

}
