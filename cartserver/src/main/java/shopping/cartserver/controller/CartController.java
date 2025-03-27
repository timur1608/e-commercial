package shopping.cartserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.cartserver.service.CartService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/products/{id}")
    public void addProduct(@PathVariable(value = "id") Long productId) {
        cartService.addProduct(productId);
    }
    @GetMapping("/products")
    public Map<Long, Integer> getCartProducts() {
        log.info("getCartProducts");
        return cartService.getCartProducts();
    }
}
