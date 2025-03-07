package shopping.clientserver.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shopping.clientserver.DTO.Product;

@FeignClient(name = "product-client", url="http://localhost:8075/productservice/api/v1/products")
public interface ProductReceiver {
    @GetMapping(params="categoryId")
    Iterable<Product> getProductsByCategory(@RequestParam("categoryId") Long categoryId);
}
