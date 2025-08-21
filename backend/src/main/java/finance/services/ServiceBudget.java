package finance.services;

import finance.domain.budgets.Budget;
import finance.dto.budgets.BudgetCreateDTO;
import finance.dto.budgets.BudgetResponseDTO;
import finance.repository.RepositoryBudget;
import finance.repository.RepositoryCategory;
import finance.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceBudget {
    
    @Autowired
    private RepositoryBudget repositoryBudget;
    
    @Autowired
    private RepositoryUser repositoryUser;
    
    @Autowired
    private RepositoryCategory repositoryCategory;
    
    @Transactional
    public BudgetResponseDTO createBudget(BudgetCreateDTO data) {
        var user = repositoryUser.findById(data.userId())
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        var category = repositoryCategory.findById(data.categoryId())
            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        
        var budget = new Budget(
            user,
            category,
            data.name().trim(),
            data.amount(),
            data.startDate(),
            data.endDate(),
            data.period()
        );
        
        var savedBudget = repositoryBudget.save(budget);
        return mapToResponseDTO(savedBudget);
    }
    
    public List<BudgetResponseDTO> getBudgetsByUser(Long userId) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        return repositoryBudget.findByUserAndIsActiveTrue(user)
            .stream()
            .map(this::mapToResponseDTO)
            .toList();
    }
    
    public List<BudgetResponseDTO> getActiveBudgetsForDate(Long userId, LocalDate date) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        return repositoryBudget.findActiveBudgetsForUserAndDate(user, date)
            .stream()
            .map(this::mapToResponseDTO)
            .toList();
    }
    
    @Transactional
    public void updateBudgetSpent(Long budgetId, BigDecimal amount) {
        var budget = repositoryBudget.findById(budgetId)
            .orElseThrow(() -> new IllegalArgumentException("Orçamento não encontrado"));
        
        budget.setSpent(budget.getSpent().add(amount));
        repositoryBudget.save(budget);
    }
    
    private BudgetResponseDTO mapToResponseDTO(Budget budget) {
        BigDecimal remaining = budget.getAmount().subtract(budget.getSpent());
        Double progressPercentage = budget.getSpent()
            .divide(budget.getAmount(), 4, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100))
            .doubleValue();
        
        return new BudgetResponseDTO(
            budget.getId(),
            budget.getUser().getId(),
            budget.getCategory().getId(),
            budget.getCategory().getName(),
            budget.getName(),
            budget.getAmount(),
            budget.getSpent(),
            remaining,
            progressPercentage,
            budget.getStartDate(),
            budget.getEndDate(),
            budget.getPeriod(),
            budget.getIsActive(),
            budget.getCreatedAt()
        );
    }
}