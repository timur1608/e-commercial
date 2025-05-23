package shopping.deliveryserver.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import shopping.deliveryserver.model.Cart;

@Service
public class CartSender {
    private final StreamBridge streamBridge;
    @Autowired
    public CartSender(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendCartToBuildReceipt(Cart cart) {
        Message<Cart> message = MessageBuilder.withPayload(cart).build();
        streamBridge.send("finishedUserCart", message);
    }
}
