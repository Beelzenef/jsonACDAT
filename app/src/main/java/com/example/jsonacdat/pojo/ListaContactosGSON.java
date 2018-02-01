package com.example.jsonacdat.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ListaContactosGSON {

    @SerializedName("contactos")
    @Expose
    private List<ContactoGSON> contacts = null;

    public List<ContactoGSON> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactoGSON> contacts) {
        this.contacts = contacts;
    }

}
