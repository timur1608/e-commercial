package shopping.clientserver.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name="cartSender", url = "http://localhost:8075/cartserver/api/v1/cart")
public interface CartSender {
    @GetMapping("/products")
    Map<Long, Integer> getProducts();
    @PostMapping("/products/{id}")
    void addToCart(@PathVariable Long id);
}
