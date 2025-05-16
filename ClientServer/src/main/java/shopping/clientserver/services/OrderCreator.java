package shopping.clientserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.clientserver.feignclients.OrderClient;

@Service
public class OrderCreator {
    private final OrderClient orderClient;

    @Autowired
    public OrderCreator(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    public void createNewOrder(){
        orderClient.newOrder();
    }
}
