package shopping.productservice.repository;

import org.springframework.data.repository.CrudRepository;
import shopping.productservice.model.Category;
import shopping.productservice.model.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findProductsByCategory(Category category);
}
