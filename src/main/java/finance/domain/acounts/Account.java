package finance.domain.acounts;

import finance.domain.banks.Bank;
import finance.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accounts")
@Entity(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bank_id")
    private Bank bank;
    private String name;
    private AccountType type;
    private  BigDecimal balance;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Account(User user, Bank bank, String name, AccountType type, BigDecimal balance) {
        this.user = user;
        this.bank = bank;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
    }
}

