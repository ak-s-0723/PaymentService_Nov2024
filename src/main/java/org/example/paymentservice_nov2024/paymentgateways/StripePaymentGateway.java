package org.example.paymentservice_nov2024.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.stripe.param.PaymentLinkCreateParams;

@Component
public class StripePaymentGateway implements IPaymentGateway {

    @Value("${stripe.apikey}")
    private String stripeApiKey;

    @Override
    public String getPaymentLink(Long amount, String orderId, String phoneNumber, String customerName) {
       try {

           Stripe.apiKey = this.stripeApiKey;
           Price price = getPrice(amount);
           PaymentLinkCreateParams params =
                   PaymentLinkCreateParams.builder()
                           .addLineItem(
                                   PaymentLinkCreateParams.LineItem.builder()
                                           .setPrice(price.getId())
                                           .setQuantity(1L)
                                           .build()
                           ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                   .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                   .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                           .setUrl("https://scaler.com")
                                           .build())
                                   .build())
                           .build();
           PaymentLink paymentLink = PaymentLink.create(params);
           return paymentLink.getUrl();
       }
        catch(StripeException exception) {
           throw new RuntimeException(exception.getMessage());
        }
    }

    private Price getPrice(Long amount) {
        try {
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("usd")
                            .setUnitAmount(amount)
                            .setRecurring(
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Netflix Plan").build()
                            )
                            .build();
            Price price = Price.create(params);
            return price;
        }catch (StripeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
