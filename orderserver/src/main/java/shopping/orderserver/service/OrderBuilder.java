package shopping.orderserver.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import shopping.orderserver.feignclients.CartClient;
import shopping.orderserver.model.Cart;
import shopping.orderserver.model.Order;
import shopping.orderserver.model.OrderProducts;
import shopping.orderserver.producer.OrderStatusProducer;
import shopping.orderserver.repository.OrderProductsRepository;
import shopping.orderserver.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@EnableAsync
public class OrderBuilder {
    private final OrderStatusProducer orderStatusProducer;
    private final OrderRepository orderRepository;
    private final OrderProductsRepository orderProductsRepository;
    private final SecurityExtracter securityExtracter;
    private final CartClient cartClient;

    @Autowired
    public OrderBuilder(OrderStatusProducer orderStatusProducer, OrderRepository orderRepository,
                        OrderProductsRepository orderProductsRepository, SecurityExtracter securityExtracter,
                        CartClient cartClient) {
        this.orderStatusProducer = orderStatusProducer;
        this.orderRepository = orderRepository;
        this.orderProductsRepository = orderProductsRepository;
        this.securityExtracter = securityExtracter;
        this.cartClient = cartClient;
    }

    @Transactional
    public void createOrder() {
        Order order = Order.builder().
                userId(securityExtracter.extractUserId()).
                status("IN PROGRESS").
                build();
        log.info("Create order");
        List<OrderProducts> orderProductsList = new ArrayList<>();
        Cart cart = cartClient.getCartFromUserCart();
        cart.getProducts().forEach((productId, productQuantity) -> {
            orderProductsList.add(OrderProducts.builder()
                    .order(order)
                    .productId(productId)
                    .count(productQuantity)
                    .build());
        });
        orderRepository.save(order);
        orderProductsRepository.saveAll(orderProductsList);
        cartClient.clearCart();
        orderStatusProducer.orderInProgressProducer(securityExtracter.extractUserId());
        orderStatusProducer.sendCartToDeliveryProducer(cart);
    }

}
