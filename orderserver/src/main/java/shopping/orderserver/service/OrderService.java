package shopping.orderserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.orderserver.model.Order;
import shopping.orderserver.repository.OrderProductsRepository;
import shopping.orderserver.repository.OrderRepository;

@Slf4j
@Service
public class OrderService {
    private OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order setStatusCompleted(String userId, String status){
        Order order = orderRepository.findByUserIdAndStatusContaining(userId, "IN PROGRESS");
        log.info(order.toString());
        if (order != null) {
            order.setStatus("COMPLETED");
            orderRepository.save(order);
        }
        return null;
    }
}
