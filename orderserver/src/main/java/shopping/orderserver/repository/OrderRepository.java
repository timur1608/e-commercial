package shopping.orderserver.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import shopping.orderserver.model.Order;


public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value="SELECT o from Order o WHERE o.userId=:userId AND o.status=:status")
    Order findByUserIdAndStatusContaining(String userId, String status);
}
