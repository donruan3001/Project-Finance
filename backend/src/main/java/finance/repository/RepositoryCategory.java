package finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import finance.domain.categories.Category;
import finance.domain.categories.CategoryType;

public interface RepositoryCategory extends JpaRepository<Category, Long> {
    List<Category> findByTypeAndIsActiveTrue(CategoryType type);
    List<Category> findByIsActiveTrue();
    
    @Query("SELECT c FROM Category c WHERE c.name LIKE %:name% AND c.isActive = true")
    List<Category> findByNameContainingAndIsActiveTrue(@Param("name") String name);
}