package com.example.letsgo.internet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dipak Kumar Mehta on 14/06/22.
 */
public class BusStopModel {

    @SerializedName("idbus")
    @Expose
    private String idbus;
    @SerializedName("busno")
    @Expose
    private String busno;
    @SerializedName("firststop")
    @Expose
    private String firststop;
    @SerializedName("laststop")
    @Expose
    private String laststop;

    public String getIdbus() {
        return idbus;
    }

    public void setIdbus(String idbus) {
        this.idbus = idbus;
    }

    public String getBusno() {
        return busno;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }

    public String getFirststop() {
        return firststop;
    }

    public void setFirststop(String firststop) {
        this.firststop = firststop;
    }

    public String getLaststop() {
        return laststop;
    }

    public void setLaststop(String laststop) {
        this.laststop = laststop;
    }
}
