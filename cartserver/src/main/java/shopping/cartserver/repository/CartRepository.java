package shopping.cartserver.repository;

import org.springframework.data.repository.CrudRepository;
import shopping.cartserver.model.Cart;
import shopping.cartserver.model.User;

public interface CartRepository extends CrudRepository<Cart, String> {
    Cart findByUserId(String userId);
}
