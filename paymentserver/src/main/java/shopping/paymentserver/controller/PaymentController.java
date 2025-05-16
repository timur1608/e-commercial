package shopping.paymentserver.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class PaymentController {
    @Value("${STRIPE.SECRET.KEY}")
    private String stripeKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeKey;
        log.info("Stripe API Key: " + stripeKey);
    }

    @PostMapping("/create-payment")
    public ResponseEntity<String> createPayment(@RequestParam Long totalAmount) throws StripeException {
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:8080/home/order")
                        .setCancelUrl("http://localhost:8080/home/products")
                        .addLineItem(SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(SessionCreateParams.LineItem.PriceData.builder().
                                        setCurrency("rub").
                                        setUnitAmount(totalAmount)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Оплата корзины")
                                                                .build()
                                                ).build()
                                        ).build()
                        ).build();
        Session session = Session.create(params);
        log.info("Session created: {}", session.getId());
        return ResponseEntity.ok(session.getId());
    }
}
