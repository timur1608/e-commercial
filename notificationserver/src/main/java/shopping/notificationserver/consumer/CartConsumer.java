package shopping.notificationserver.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import shopping.notificationserver.model.Cart;
import shopping.notificationserver.service.NotificationService;

import java.util.function.Consumer;

@Slf4j
@Service
public class CartConsumer {
    private final NotificationService notificationService;
    @Autowired
    public CartConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @Bean
    public Consumer<Message<Cart>> getCartForReceipt() {
        return message -> {
            log.info("receipt: {}", message.getPayload());
        };
    }
}
