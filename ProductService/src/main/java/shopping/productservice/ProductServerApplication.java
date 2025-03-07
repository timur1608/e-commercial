package shopping.productservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import shopping.productservice.model.Category;
import shopping.productservice.model.Product;
import shopping.productservice.repository.CategoryRepository;
import shopping.productservice.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(
        scanBasePackages = "shopping.productservice")
@EnableFeignClients
public class ProductServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServerApplication.class, args);
    }
    @Bean
    public CommandLineRunner init(ProductRepository productRepository, CategoryRepository categoryRepository) {
        return args-> {
            if (productRepository.count() == 0) {
                Category category = new Category("fruits");
                categoryRepository.save(category);
                List<Product> list = new ArrayList<>();
                Product product = new Product("Бананы", "fruits/banana.jpeg",
                        120, category);
                Product product2 = new Product("Яблоки",
                        "fruits/apple.jpg",
                        110, category);
                Product product3 = new Product("Мандарины", "fruits/orange.jpg",
                        130, category);
                Product product4 = new Product("Груша", "fruits/pear.jpeg", 200, category);
                Category category2 = new Category("dairy");
                categoryRepository.save(category2);
                Product product5 = new Product("Молоко", "dairy/milk.jpg", 60, category2);
                Product product6 = new Product("Сыр", "dairy/cheese.jpeg", 150, category2);
                Product product7 = new Product("Масло", "dairy/butter.jpeg", 200, category2);
                Product product8 = new Product("Йогурт", "dairy/yogurt.jpeg", 50, category2);
                list.add(product);
                list.add(product2);
                list.add(product3);
                list.add(product4);
                list.add(product5);
                list.add(product6);
                list.add(product7);
                list.add(product8);
                productRepository.saveAll(list);

            }
        };
    }
}
