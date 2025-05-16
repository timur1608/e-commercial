package shopping.productservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    private String description;


    @NonNull
    private String imageUrl;
    @NonNull
    private Integer price;
    @NonNull
    @ManyToOne
    private Category category;
}
