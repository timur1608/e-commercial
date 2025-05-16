package shopping.clientserver.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="order-client", url = "http://localhost:8075/orderserver/api/v1/orders")
public interface OrderClient {
    @PostMapping()
    void newOrder();
}
