package shopping.deliveryserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.stereotype.Service;
import shopping.deliveryserver.model.Cart;
import shopping.deliveryserver.producer.OrderStatusProducer;

import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class CartHandler {
    private final OrderStatusProducer orderStatusProducer;
//    private final SecurityExtracter securityExtracter;
    @Autowired
    public CartHandler(OrderStatusProducer orderStatusProducer) {
        this.orderStatusProducer = orderStatusProducer;
//        this.securityExtracter = securityExtracter;
    }
    public void processCart(Cart userCart) {
        //... сборка корзины
//        log.info("userId: {}", );
        log.info("userId: {}", userCart.getUserId());
        orderStatusProducer.orderInReadyProducer(userCart.getUserId());
    }
}
