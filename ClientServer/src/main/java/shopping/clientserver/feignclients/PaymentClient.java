package shopping.clientserver.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="payment-client", url = "http://localhost:8075/paymentserver/api/v1/create-payment")
public interface PaymentClient {
    @PostMapping
    String createPayment(@RequestParam Long totalAmount);
}
