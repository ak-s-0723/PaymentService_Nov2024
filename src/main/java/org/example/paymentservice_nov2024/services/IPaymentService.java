package org.example.paymentservice_nov2024.services;

public interface IPaymentService {

    public String initiatePayment(Long amount,String orderId,String phoneNumber,String customerName);
}
