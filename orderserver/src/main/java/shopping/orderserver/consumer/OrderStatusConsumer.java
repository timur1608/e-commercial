package shopping.orderserver.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import shopping.orderserver.service.OrderService;

import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@Service
public class OrderStatusConsumer {
    private final OrderService orderService;
    @Autowired
    public OrderStatusConsumer(OrderService orderService) {
        this.orderService = orderService;
    }
    @Bean
    public Consumer<Message<Map<String, String>>> receiveOrderStatus(){
        return message -> {
            var map = message.getPayload();
            map.keySet().forEach(key -> {
                if (map.get(key).equals("order is ready")) {
                    log.info("received order status: {}", map.get(key));
                    orderService.setStatusCompleted(key, map.get(key));
                }
            });
        };
    }
}