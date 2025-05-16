package shopping.deliveryserver.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class OrderStatusProducer {
    private final StreamBridge streamBridge;
    @Autowired
    public OrderStatusProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }
    public void orderInReadyProducer(String username) {
        log.info("username :{}", username);
        Map<String, String> map = new HashMap<>();
        map.put(username, "order is ready");
        Message<Map<String, String>> message = MessageBuilder.withPayload(map).build();
        streamBridge.send("output-out-0", map);
    }
}
