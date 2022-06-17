package com.example.letsgo.internet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dipak Kumar Mehta on 6/13/2022.
 */
public class SourceDestinationModel {
    @SerializedName("idstop")
    @Expose
    private String idstop;
    @SerializedName("stopname")
    @Expose
    private String stopname;

    public String getIdstop() {
        return idstop;
    }

    public void setIdstop(String idstop) {
        this.idstop = idstop;
    }

    public String getStopname() {
        return stopname;
    }

    public void setStopname(String stopname) {
        this.stopname = stopname;
    }
}
