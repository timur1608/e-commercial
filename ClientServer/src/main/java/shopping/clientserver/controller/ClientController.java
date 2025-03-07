package shopping.clientserver.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shopping.clientserver.DTO.Category;
import shopping.clientserver.DTO.Product;
import shopping.clientserver.services.ClientService;

import java.util.List;


@Slf4j
@RequestMapping("/home")
@Controller
public class ClientController {
    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @ModelAttribute(value = "categories")
    Iterable<Category> getCategories() {
        return clientService.getCategories();
    }

    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam("categoryId") Long categoryId, Model model, HttpSession session) {
        Iterable<Product> products = clientService.getProductsByCategory(categoryId);
        session.setAttribute("categoryId", categoryId);
        model.addAttribute("products", products);
        return "/home";
    }

    @GetMapping()
    public String homePage(){
        return "home";
    }
}
