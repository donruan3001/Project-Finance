package finance.controllers;

import finance.domain.categories.CategoryType;
import finance.dto.categories.CategoryCreateDTO;
import finance.dto.categories.CategoryResponseDTO;
import finance.services.ServiceCategory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ControllerCategory {
    
    @Autowired
    private ServiceCategory serviceCategory;
    
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryCreateDTO data) {
        var category = serviceCategory.createCategory(data);
        return ResponseEntity.ok(category);
    }
    
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        var categories = serviceCategory.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoriesByType(@PathVariable CategoryType type) {
        var categories = serviceCategory.getCategoriesByType(type);
        return ResponseEntity.ok(categories);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        serviceCategory.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}