package org.example.paymentservice_nov2024.controllers;

import org.example.paymentservice_nov2024.dtos.InitiatePaymentDto;
import org.example.paymentservice_nov2024.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;


    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentDto initiatePaymentDto) {
       return paymentService.initiatePayment(initiatePaymentDto.getAmount(),
               initiatePaymentDto.getOrderId(),
               initiatePaymentDto.getPhoneNumber(),
               initiatePaymentDto.getCustomerName());
    }


}
