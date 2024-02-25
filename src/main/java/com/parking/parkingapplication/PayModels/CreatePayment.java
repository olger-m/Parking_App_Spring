package com.parking.parkingapplication.PayModels;

import com.google.gson.annotations.SerializedName;

public class CreatePayment {
    @SerializedName("items")
    CreatePaymentItem[] items;

    public CreatePaymentItem[] getItems() {
        return items;
    }

    public void setItems(CreatePaymentItem[] items) {
        this.items = items;
    }
}
