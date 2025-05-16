package shopping.orderserver.repository;

import org.springframework.data.repository.CrudRepository;
import shopping.orderserver.model.OrderProducts;

public interface OrderProductsRepository extends CrudRepository<OrderProducts, Long> {
}
