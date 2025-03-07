package shopping.orderserver.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    private Long productId;
    private Integer count;
}
