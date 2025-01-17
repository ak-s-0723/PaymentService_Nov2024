package org.example.paymentservice_nov2024.paymentgateways;

public interface IPaymentGateway {

    String getPaymentLink(Long amount,String orderId,String phoneNumber,String customerName);
}
