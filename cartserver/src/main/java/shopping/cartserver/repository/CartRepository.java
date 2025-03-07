package shopping.cartserver.repository;

import org.springframework.data.repository.CrudRepository;
import shopping.cartserver.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
