package shopping.orderserver.feignclients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shopping.orderserver.model.Cart;

import java.util.Map;

@FeignClient(name="cartSender", url = "http://localhost:8075/cartserver/api/v1/cart")
public interface CartClient {
    @PostMapping("/clear")
    void clearCart();
    @GetMapping("/products")
    Map<Long, Integer> getProductsFromUserCart();
    @GetMapping
    Cart getCartFromUserCart();
}
