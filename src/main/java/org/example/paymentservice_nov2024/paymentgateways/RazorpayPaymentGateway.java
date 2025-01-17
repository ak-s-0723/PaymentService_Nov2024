package org.example.paymentservice_nov2024.paymentgateways;

import com.razorpay.PaymentLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Component
public class RazorpayPaymentGateway implements IPaymentGateway {

    @Autowired
    private RazorpayClient razorpayClient;

    public String getPaymentLink(Long amount,String orderId,String phoneNumber,String customerName) {
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", true);
            paymentLinkRequest.put("first_min_partial_amount", 100);
            paymentLinkRequest.put("expire_by", 1737257442);
            paymentLinkRequest.put("reference_id", orderId);
            paymentLinkRequest.put("description", "Payment for policy no #23456");
            JSONObject customer = new JSONObject();
            customer.put("name", phoneNumber);
            customer.put("contact", customerName);
            customer.put("email", "gaurav.kumar@example.com");
            paymentLinkRequest.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("reminder_enable", true);
            JSONObject notes = new JSONObject();
            notes.put("policy_name", "Jeevan Bima");
            paymentLinkRequest.put("notes", notes);
            paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url").toString();
        }catch (RazorpayException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
