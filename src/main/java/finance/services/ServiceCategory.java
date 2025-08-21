package finance.services;

import finance.domain.categories.Category;
import finance.domain.categories.CategoryType;
import finance.dto.categories.CategoryCreateDTO;
import finance.dto.categories.CategoryResponseDTO;
import finance.repository.RepositoryCategory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceCategory {
    
    @Autowired
    private RepositoryCategory repositoryCategory;
    
    @Transactional
    public CategoryResponseDTO createCategory(CategoryCreateDTO data) {
        var category = new Category(
            data.name().trim(),
            data.description(),
            data.icon(),
            data.color(),
            data.type()
        );
        
        var savedCategory = repositoryCategory.save(category);
        return mapToResponseDTO(savedCategory);
    }
    
    public List<CategoryResponseDTO> getAllCategories() {
        return repositoryCategory.findByIsActiveTrue()
            .stream()
            .map(this::mapToResponseDTO)
            .toList();
    }
    
    public List<CategoryResponseDTO> getCategoriesByType(CategoryType type) {
        return repositoryCategory.findByTypeAndIsActiveTrue(type)
            .stream()
            .map(this::mapToResponseDTO)
            .toList();
    }
    
    @Transactional
    public void deleteCategory(Long id) {
        var category = repositoryCategory.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        
        category.setIsActive(false);
        category.setUpdatedAt(LocalDateTime.now());
        repositoryCategory.save(category);
    }
    
    private CategoryResponseDTO mapToResponseDTO(Category category) {
        return new CategoryResponseDTO(
            category.getId(),
            category.getName(),
            category.getDescription(),
            category.getIcon(),
            category.getColor(),
            category.getType(),
            category.getIsActive(),
            category.getCreatedAt()
        );
    }
}