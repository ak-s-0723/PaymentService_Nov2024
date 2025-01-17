package org.example.paymentservice_nov2024.services;

import org.example.paymentservice_nov2024.paymentgateways.IPaymentGateway;
import org.example.paymentservice_nov2024.paymentgateways.PaymentGatewayChooserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    @Override
    public String initiatePayment(Long amount, String orderId, String phoneNumber, String customerName) {
        IPaymentGateway paymentGateway = paymentGatewayChooserStrategy.getBestPaymentGateway();
        return paymentGateway.getPaymentLink(amount, orderId, phoneNumber, customerName);
    }
}
