package com.example.emp354.linear.AutoComplete.POJO;

import java.util.List;

public class PlaceModel {

    private String status;
    private List<Predictions> predictions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Predictions> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Predictions> predictions) {
        this.predictions = predictions;
    }
}
