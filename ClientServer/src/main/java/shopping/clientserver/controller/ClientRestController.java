package shopping.clientserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shopping.clientserver.feignclients.PaymentClient;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
public class ClientRestController {
    private final PaymentClient paymentClient;
    @Autowired
    public ClientRestController(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @PostMapping("/create-payment")
    public ResponseEntity<Map<String, String>> createPayment(Model model, @RequestParam Integer totalPrice){
        log.info("Create payment request received");
        String sessionId = paymentClient.createPayment(Long.valueOf(totalPrice) * 100);
        log.info(sessionId);
        Map<String, String> body = Collections.singletonMap("sessionId", sessionId);
        return ResponseEntity.ok(body);
    }
}
