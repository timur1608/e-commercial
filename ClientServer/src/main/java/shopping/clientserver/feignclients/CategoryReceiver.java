package shopping.clientserver.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import shopping.clientserver.DTO.Category;

@FeignClient(name="category-client", url = "http://localhost:8075/productservice/api/v1/categories/all")
public interface CategoryReceiver {
    @GetMapping
    Iterable<Category> getCategories();
}
