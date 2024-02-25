package com.parking.parkingapplication.PayModels;

import com.google.gson.annotations.SerializedName;

public class CreatePaymentItem {
    @SerializedName("id")
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
