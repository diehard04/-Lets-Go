package com.example.letsgo.internet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dipak Kumar Mehta on 14/06/22.
 */
public class BusStopsResult {

    @SerializedName("data")
    @Expose
    private List<BusStopModel> data = null;
    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;

    public List<BusStopModel> getData() {
        return data;
    }

    public void setData(List<BusStopModel> data) {
        this.data = data;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }
}
