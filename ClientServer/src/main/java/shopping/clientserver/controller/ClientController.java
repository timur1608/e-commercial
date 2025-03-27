package shopping.clientserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shopping.clientserver.DTO.Cart;
import shopping.clientserver.DTO.Category;
import shopping.clientserver.DTO.Product;
import shopping.clientserver.services.CartService;
import shopping.clientserver.services.ClientService;
import shopping.clientserver.services.TokenExtractor;

import java.util.Map;


@Slf4j
@RequestMapping("/home")
@Controller
public class ClientController {
    private final ClientService clientService;
    private final CartService cartService;
    private final TokenExtractor tokenExtractor;

    @Autowired
    public ClientController(ClientService clientService, CartService cartService, TokenExtractor tokenExtractor) {
        this.clientService = clientService;
        this.cartService = cartService;
        this.tokenExtractor = tokenExtractor;
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

    @PostMapping("/cart")
    public String addToCart(Long productId, Long categoryId){
        cartService.addToCart(productId);
        return "redirect:/home/products?categoryId="+categoryId;
    }

    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam(value = "categoryId", required = false) Long categoryId, Model model, HttpSession session) {
        Iterable<Product> products = clientService.getProductsByCategory(categoryId);
        session.setAttribute("categoryId", categoryId);
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping()
    public String homePage() {
        return "redirect:/home/products";
    }
}
