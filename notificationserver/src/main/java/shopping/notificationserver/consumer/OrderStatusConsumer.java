package shopping.notificationserver.consumer;

import java.util.Map;
import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import shopping.notificationserver.service.NotificationService;

@Slf4j
@Service
public class OrderStatusConsumer {
    private final NotificationService notificationService;
    @Autowired
    public OrderStatusConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @Bean
    public Consumer<Message<Map<String, String>>> receiveSomeMessage() {
        return message -> {
            var map = message.getPayload();
            map.keySet().forEach(key -> {
                log.info("{}: {}", key, map.get(key));
                try {
                    notificationService.sendMessage(key, map.get(key));
                }
                catch (Exception e){
                    throw new RuntimeException(e);
                }
            });
        };
    }
}
