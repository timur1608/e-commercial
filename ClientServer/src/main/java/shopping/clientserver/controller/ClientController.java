package shopping.clientserver.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shopping.clientserver.DTO.Cart;
import shopping.clientserver.DTO.Category;
import shopping.clientserver.DTO.Product;
import shopping.clientserver.services.CartService;
import shopping.clientserver.services.ClientService;
import shopping.clientserver.services.OrderCreator;
import shopping.clientserver.services.TokenExtractor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;


@Slf4j
@RequestMapping("/home")
@Controller
public class ClientController {
    private final ClientService clientService;
    private final CartService cartService;
    private final TokenExtractor tokenExtractor;
    private final OrderCreator orderCreator;

    @Value("${STRIPE.PUBLIC.KEY}")
    private String stripePublicKey;

    @Autowired
    public ClientController(ClientService clientService, CartService cartService, TokenExtractor tokenExtractor,
                            OrderCreator orderCreator) {
        this.clientService = clientService;
        this.cartService = cartService;
        this.tokenExtractor = tokenExtractor;
        this.orderCreator = orderCreator;
    }

    @ModelAttribute(value = "categories")
    Iterable<Category> getCategories() {
        return clientService.getCategories();
    }

    @ModelAttribute(value="cart")
    Cart getCart(){
        return cartService.getCart();
    }

    @ModelAttribute(value="JwtToken")
    String getJwtToken(){
        return tokenExtractor.getAccessToken();
    }

    @ModelAttribute(value="username")
    String getUsername(Authentication authentication){
        return authentication.getName();
    }

    @PostMapping("/cart")
    public String addToCart(Long productId, Long categoryId){
        cartService.addToCart(productId);
        log.info("categoryID: {}", categoryId);
        return "redirect:/home/products?categoryId="+categoryId;
    }
    @GetMapping("/order")
    public String newOrder(Model model){
        if (((Cart) Objects.requireNonNull(model.getAttribute("cart"))).getTotalPrice() != 0) {
            orderCreator.createNewOrder();
        }
        return "redirect:/home/products";
    }


    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam(value = "categoryId", required = false, defaultValue = "0") Long categoryId, Model model, HttpSession session) {
        Iterable<Product> products = clientService.getProductsByCategory(categoryId);
        session.setAttribute("categoryId", categoryId);
        model.addAttribute("products", products);
        log.info("stripePublicKey: {}", stripePublicKey);
        model.addAttribute("stripePublicKey", stripePublicKey);
        return "home";
    }

    @GetMapping()
    public String homePage() {
        return "redirect:/home/products";
    }
}
