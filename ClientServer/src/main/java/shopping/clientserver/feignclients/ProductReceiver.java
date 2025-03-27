package shopping.clientserver.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import shopping.clientserver.DTO.Product;

import java.util.Collection;
import java.util.List;

@FeignClient(name = "product-client", url="http://localhost:8075/productservice/api/v1/products")
public interface ProductReceiver {
    @GetMapping(params="categoryId")
    Iterable<Product> getProductsByCategory(@RequestParam("categoryId") Long categoryId);

    @PostMapping(value = "/ids", consumes = "application/json")
    Iterable<Product> getProductsByIds(@RequestBody Iterable<Long> ids);
}
