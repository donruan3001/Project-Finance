package finance.domain.budgets;

import finance.domain.categories.Category;
import finance.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    
    private String name;
    private BigDecimal amount;
    private BigDecimal spent;
    private LocalDate startDate;
    private LocalDate endDate;
    
    @Enumerated(EnumType.STRING)
    private BudgetPeriod period;
    
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Budget(User user, Category category, String name, BigDecimal amount, 
                  LocalDate startDate, LocalDate endDate, BudgetPeriod period) {
        this.user = user;
        this.category = category;
        this.name = name;
        this.amount = amount;
        this.spent = BigDecimal.ZERO;
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = period;
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}