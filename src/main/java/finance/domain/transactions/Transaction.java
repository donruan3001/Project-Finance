package finance.domain.transactions;

import finance.domain.user.User;
import finance.domain.acounts.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    private CategoryTransactions category;

    private String name;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    private BigDecimal amount;

    private LocalDateTime created;
    private LocalDateTime updated;

    public Transaction(Account account, CategoryTransactions category, String name,
                       TypeTransaction type, BigDecimal amount) {
        this.account = account;
        this.category = category;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    // atalho conveniente
    public User getUser() {
        return account.getUser();
    }
}
