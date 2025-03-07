package shopping.productservice.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="imageReceiver", url="http://localhost:8081/api/v1/products/images")
public interface ImageUrlReceiver {
    @GetMapping
    String getImageUrl(@RequestParam("imageUrl") String imageUrl);
}
