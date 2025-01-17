package org.example.paymentservice_nov2024.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InitiatePaymentDto {
    Long amount;
    String orderId;
    String phoneNumber;
    String customerName;
}
