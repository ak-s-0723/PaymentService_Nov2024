package org.example.paymentservice_nov2024.paymentgateways;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements IPaymentGateway {

    @Override
    public String getPaymentLink(Long amount, String orderId, String phoneNumber, String customerName) {
        return null;
    }
}
