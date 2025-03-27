package shopping.cartserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Map;

@RedisHash("Cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private String userId;
    private Map<Long, Integer> products;
}
