package shopping.orderserver.service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ProductService")
public interface CategoryFeignClient {
    
}
