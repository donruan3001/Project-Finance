package finance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import finance.domain.budgets.Budget;
import finance.domain.user.User;

public interface RepositoryBudget extends JpaRepository<Budget, Long> {
    List<Budget> findByUserAndIsActiveTrue(User user);
    
    @Query("SELECT b FROM Budget b WHERE b.user = :user AND b.startDate <= :date AND b.endDate >= :date AND b.isActive = true")
    List<Budget> findActiveBudgetsForUserAndDate(@Param("user") User user, @Param("date") LocalDate date);
    
    @Query("SELECT b FROM Budget b WHERE b.user = :user AND b.category.id = :categoryId AND b.isActive = true")
    List<Budget> findByUserAndCategoryAndIsActiveTrue(@Param("user") User user, @Param("categoryId") Long categoryId);
}