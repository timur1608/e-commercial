package shopping.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.productservice.model.Category;
import shopping.productservice.model.Product;
import shopping.productservice.repository.ImageUrlReceiver;
import shopping.productservice.repository.ProductRepository;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ImageUrlReceiver imageUrlReceiver;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService,
                          ImageUrlReceiver imageUrlReceiver) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.imageUrlReceiver = imageUrlReceiver;
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Iterable<Product> findProductsByCategoryId(Long categoryId) {
        Category category = categoryService.findById(categoryId);
        Iterable<Product> products = productRepository.findProductsByCategory(category);
        products.forEach(product -> {product.setImageUrl(imageUrlReceiver.getImageUrl(product.getImageUrl()));});
        return products;
    }

    public Iterable<Product> findAll() {
        Iterable<Product> products = productRepository.findAll();
        products.forEach(product -> {product.setImageUrl(imageUrlReceiver.getImageUrl(product.getImageUrl()));});
        return products;
    }
    public Iterable<Product> findProductsByIds(Iterable<Long> ids) {
        Iterable<Product> products = productRepository.findAllById(ids);
        products.forEach(product -> {product.setImageUrl(imageUrlReceiver.getImageUrl(product.getImageUrl()));});
        return products;
    }
}
