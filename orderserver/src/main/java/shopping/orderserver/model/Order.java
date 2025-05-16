package shopping.orderserver.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private final LocalDateTime createdAt=LocalDateTime.now();

}
