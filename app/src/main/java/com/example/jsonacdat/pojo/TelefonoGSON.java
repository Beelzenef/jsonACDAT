package com.example.jsonacdat.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usuario on 30/01/18.
 */

public class TelefonoGSON {
    @SerializedName("casa")
    @Expose
    private String home;
    @SerializedName("movil")
    @Expose
    private String mobile;
    @SerializedName("trabajo")
    @Expose
    private String work;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

}
