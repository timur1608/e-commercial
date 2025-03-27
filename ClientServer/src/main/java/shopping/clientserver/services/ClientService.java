package shopping.clientserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.clientserver.DTO.Category;
import shopping.clientserver.DTO.Product;
import shopping.clientserver.feignclients.CategoryReceiver;
import shopping.clientserver.feignclients.ProductReceiver;


@Service
public class ClientService {
    private final CategoryReceiver categoryReceiver;
    private final ProductReceiver productReceiver;
    @Autowired
    public ClientService(CategoryReceiver categoryReceiver, ProductReceiver productReceiver) {
        this.categoryReceiver = categoryReceiver;
        this.productReceiver = productReceiver;
    }
    public Iterable<Category> getCategories() {
        return categoryReceiver.getCategories();
    }
    public Iterable<Product> getProductsByCategory(Long categoryId) {
        return productReceiver.getProductsByCategory(categoryId);
    }
    public Iterable<Product> getProductsByIds(Iterable<Long> productIds) {
        return productReceiver.getProductsByIds(productIds);
    }

}
