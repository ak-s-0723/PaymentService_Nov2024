package org.example.paymentservice_nov2024.paymentgateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {

    @Autowired
    private RazorpayPaymentGateway razorpayPaymentGateway;

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public IPaymentGateway getBestPaymentGateway() {
        return razorpayPaymentGateway;
    }




}
