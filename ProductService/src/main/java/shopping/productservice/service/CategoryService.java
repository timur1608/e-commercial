package shopping.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import shopping.productservice.model.Category;
import shopping.productservice.model.Product;
import shopping.productservice.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public void save(Category category){
        categoryRepository.save(category);
    }
    @Cacheable(value="categoryById", key="#id")
    public Category findById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }
    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
    @Cacheable("categories")
    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }
}
