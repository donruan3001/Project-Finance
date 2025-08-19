package finance.domain.acounts;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import finance.domain.banks.Bank;
import finance.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private  BigDecimal balance;
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Account(User user, Bank bank, String name, AccountType type, BigDecimal balance) {
        this.user = user;
        this.bank = bank;
        this.name = name;
        this.type = AccountType.valueOf(String.valueOf(type));
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
    }
}

