package com.example.letsgo.internet.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dipak Kumar Mehta on 6/13/2022.
 */
public class SourceDestinationResult {

        @SerializedName("data")
        @Expose
        private List<SourceDestinationModel> sourceDestinationModel = null;
        @SerializedName("itemCount")
        @Expose
        private Integer itemCount;

    public List<SourceDestinationModel> getBusStopsModel() {
        return sourceDestinationModel;
    }

    public void setBusStopsModel(List<SourceDestinationModel> sourceDestinationModel) {
        this.sourceDestinationModel = sourceDestinationModel;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }
}
