package com.parking.parkingapplication.PayModels;

public class CreatePaymentResponse {
    private String clientSecret;

    public CreatePaymentResponse(String clientSecret) {
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
