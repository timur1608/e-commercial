package shopping.cartserver.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.cartserver.model.Cart;
import shopping.cartserver.service.CartService;
import brave.Tracer;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final Tracer tracer;
    @Autowired
    public CartController(CartService cartService, Tracer tracer) {
        this.cartService = cartService;
        this.tracer = tracer;
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
    @GetMapping
    public Cart getCart(){
//        log.info("traceId: {}", tracer.currentSpan().context().traceIdString());
//        log.info("getCart");
        return cartService.getCart();
    }
    @PostMapping("/clear")
    public void clearCart(){
//        tracer.currentSpan().
//        log.info("traceId: {}", tracer.currentSpan().context().traceIdString());
//        log.info("clearCart");
        cartService.clearCart();
    }
}
