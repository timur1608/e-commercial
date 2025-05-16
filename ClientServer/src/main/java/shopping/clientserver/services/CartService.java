package shopping.clientserver.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.clientserver.DTO.Cart;
import shopping.clientserver.DTO.Product;
import shopping.clientserver.feignclients.CartSender;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CartService {
    private final CartSender cartSender;
    private final ClientService clientService;
    @Autowired
    public CartService(CartSender cartSender, ClientService clientService) {
        this.cartSender = cartSender;
        this.clientService = clientService;
    }
    public void addToCart(Long productId){
        cartSender.addToCart(productId);
    }
    public Cart getCart(){
        if (cartSender.getProducts() == null){
            return new Cart(new HashMap<>(), 0);
        }
        Map<Long, Integer> map = cartSender.getProducts();
        Map<Product, Integer> products = new HashMap<>();
        Iterable<Product> productList = clientService.getProductsByIds(map.keySet().stream().toList());
        int totalPrice = 0;
        for (Product product : productList) {
            products.put(product, map.get(product.getId()));
            totalPrice += product.getPrice() * map.get(product.getId());
        }

        return new Cart(products, totalPrice);
    }
}
