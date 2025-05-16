package shopping.productservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import shopping.productservice.model.Category;
import shopping.productservice.model.Product;
import shopping.productservice.repository.ImageUrlReceiver;
import shopping.productservice.repository.ProductRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
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

    @Cacheable(value="productsByCategory", key="#categoryId")
    public Iterable<Product> findProductsByCategoryId(Long categoryId) {
        Category category = categoryService.findById(categoryId);
        return getImagesForProducts(productRepository.findProductsByCategory(category));
    }
    @Cacheable("products")
    public Iterable<Product> findAll() {
        Iterable<Product> products = productRepository.findAll();
        return getImagesForProducts(StreamSupport.stream(products.spliterator(), false).collect(Collectors.toList()));
    }
    @Cacheable(value="productsByIds", key="#ids")
    public Iterable<Product> findProductsByIds(List<Long> ids) {
        Iterable<Product> products = productRepository.findAllById(ids);
        return getImagesForProducts(StreamSupport.stream(products.spliterator(), false).collect(Collectors.toList()));
    }
    public List<Product> getImagesForProducts(List<Product> products) {
        List<String> imageUrls = imageUrlReceiver.getImagesUrl(products.stream()
                .map(Product::getImageUrl)
                .collect(Collectors.toList())
        );
        AtomicReference<Integer> index = new AtomicReference<>(0);
        products.forEach(product -> {
            product.setImageUrl(imageUrls.get(index.get()));
            index.getAndSet(index.get() + 1);
        });
        return products;
    }
}
