package shopping.notificationserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String userId;
    private String userEmail;
    private Map<Long, Integer> products;
}
