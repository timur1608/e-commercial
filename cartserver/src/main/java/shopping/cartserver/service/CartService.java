package shopping.cartserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.cartserver.model.Cart;
import shopping.cartserver.repository.CartRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final SecurityExtracter securityExtracter;
    @Autowired
    public CartService(CartRepository cartRepository, SecurityExtracter securityExtracter) {
        this.cartRepository = cartRepository;
        this.securityExtracter = securityExtracter;
    }
    public void addProduct(Long productId) {
        String userId = securityExtracter.extractUserId();
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart(userId, new HashMap<>());
        }
        if (cart.getProducts().containsKey(productId)) {
            cart.getProducts().put(productId, cart.getProducts().get(productId) + 1);
        }
        else{
            cart.getProducts().put(productId, 1);
        }
        cartRepository.save(cart);
    }

    public Map<Long, Integer> getCartProducts() {
        Cart cart = cartRepository.findByUserId(securityExtracter.extractUserId());
        if (cart != null) {
            return cart.getProducts();
        }
        else{
            return null;
        }
    }
}
