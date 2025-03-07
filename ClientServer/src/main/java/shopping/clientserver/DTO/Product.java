package shopping.clientserver.DTO;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Integer price;
    private Long categoryId;
}
