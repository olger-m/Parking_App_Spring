package com.parking.parkingapplication.Controller;

import com.parking.parkingapplication.PayModels.CreatePaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api")
public class StripeController {
    @Value("${stripe.secretKey}")
    private String secretKey;

    @PostMapping("/create-payment-intent1")
    public ResponseEntity<Map<String, Object>> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {
        Stripe.apiKey = secretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentRequest.getAmount());
        params.put("currency", "usd");

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setAmount(paymentRequest.getAmount())
                .setCurrency("usd")
                .build();

        try {
            PaymentIntent paymentIntent = PaymentIntent.create(createParams);
            Map<String, Object> clientSecretResponse = new HashMap<>();
            clientSecretResponse.put("clientSecret", paymentIntent.getClientSecret());
            return ResponseEntity.ok(clientSecretResponse);
        } catch (StripeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Failed to create PaymentIntent"));
        }
    }

    @PostMapping("/confirm-payment1")
    public ResponseEntity<String> confirmPayment(@RequestBody CreatePaymentResponse confirmationRequest) {
        Stripe.apiKey = secretKey;

        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(confirmationRequest.getClientSecret());
            paymentIntent.confirm();
            return ResponseEntity.ok("Payment confirmed");
        } catch (StripeException e) {
            return ResponseEntity.status(500).body("Failed to confirm payment: " + e.getMessage());
        }
    }

    @PostMapping("/create-checkout-session-stripe1")
    public ResponseEntity<Map<String, Object>> createCheckoutSession(@RequestBody PaymentRequest paymentRequest) {
        Stripe.apiKey = secretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("payment_method_types", Arrays.asList("card"));
        params.put("line_items", Collections.singletonList(
                new HashMap<String, Object>() {{
                    put("price_data", new HashMap<String, Object>() {{
                        put("currency", "usd");
                        put("product_data", new HashMap<String, Object>() {{
                            put("name", "Your Product");
                        }});
                        put("unit_amount", paymentRequest.getAmount());
                    }});
                    put("quantity", 1);
                }}
        ));
        params.put("mode", "payment");
        params.put("success_url", "http://localhost:4200/success");
        params.put("cancel_url", "http://localhost:4200/cancel");

        try {
            Session session = Session.create(params);
            Map<String, Object> sessionResponse = new HashMap<>();
            sessionResponse.put("sessionId", session.getId());
            return ResponseEntity.ok(sessionResponse);
        } catch (StripeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Failed to create Checkout Session"));
        }
    }
}
