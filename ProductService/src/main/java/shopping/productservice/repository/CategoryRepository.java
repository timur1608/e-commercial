package shopping.productservice.repository;

import org.springframework.data.repository.CrudRepository;
import shopping.productservice.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
