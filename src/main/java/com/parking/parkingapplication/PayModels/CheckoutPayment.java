package com.parking.parkingapplication.PayModels;

import lombok.Getter;

@Getter
public class CheckoutPayment {
    // simple getters and setters
    // the product name
    private String name;
    //  currency like usd, eur ...
    private String currency;
    // our success and cancel url stripe will redirect to this links
    private String successUrl;
    private String cancelUrl;
    private long amount;
    private long quantity;

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

}