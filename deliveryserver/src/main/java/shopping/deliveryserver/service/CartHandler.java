package shopping.deliveryserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.stereotype.Service;
import shopping.deliveryserver.model.Cart;
import shopping.deliveryserver.producer.CartSender;
import shopping.deliveryserver.producer.OrderStatusProducer;

import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class CartHandler {
    private final CartSender cartSender;
    private final OrderStatusProducer orderStatusProducer;
    @Autowired
    public CartHandler(OrderStatusProducer orderStatusProducer, CartSender cartSender) {
        this.cartSender = cartSender;
        this.orderStatusProducer = orderStatusProducer;
    }
    public void processCart(Cart userCart) {
        //... сборка корзины
//        log.info("userId: {}", );
        log.info("userId: {}", userCart.getUserId());
        orderStatusProducer.orderInReadyProducer(userCart.getUserId());
        cartSender.sendCartToBuildReceipt(userCart);
    }
}
