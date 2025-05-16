package shopping.productservice.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="imageReceiver", url="http://localhost:8081/api/v1/products/images")
public interface ImageUrlReceiver {
    @GetMapping
//    String getImageUrl(@RequestParam("imageUrl") String imageUrl);
    List<String> getImagesUrl(@RequestParam("imagesUrl") List<String> imagesUrl);
}
