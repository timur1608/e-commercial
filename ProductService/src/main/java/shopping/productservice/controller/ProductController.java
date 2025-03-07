package shopping.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.productservice.model.Product;
import shopping.productservice.service.ProductService;


@RestController
@RequestMapping(value = "api/v1/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts(@RequestParam(value = "categoryId", required = false) Long categoryId) {
        if (categoryId != null) {
            return ResponseEntity.ok(productService.findProductsByCategoryId(categoryId));
        }
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        productService.save(product);
        return ResponseEntity.
                status(HttpStatus.ACCEPTED).build();
    }
}
