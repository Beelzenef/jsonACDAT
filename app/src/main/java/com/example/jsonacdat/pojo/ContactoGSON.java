package com.example.jsonacdat.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usuario on 30/01/18.
 */

public class ContactoGSON {

    @SerializedName("nombre")
    @Expose
    private String name;
    @SerializedName("direccion")
    @Expose
    private String address;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telefono")
    @Expose
    private Telefono phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Telefono getPhone() {
        return phone;
    }

    public void setPhone(Telefono phone) {
        this.phone = phone;
    }

}
