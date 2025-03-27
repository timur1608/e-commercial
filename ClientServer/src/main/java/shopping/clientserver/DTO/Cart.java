package shopping.clientserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Cart {
    private Map<Product, Integer> map;
    private Integer totalPrice;
}
