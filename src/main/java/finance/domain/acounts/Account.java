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
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    private String name;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private BigDecimal balance;

    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 🔹 Construtor vazio (necessário para o JPA)
    public Account() {}

    // 🔹 Construtor completo
    public Account(Long id, User user, Bank bank, String name, AccountType type, BigDecimal balance, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.bank = bank;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    // 🔹 Construtor usado no create
    public Account(User user, Bank bank, String name, AccountType type, BigDecimal balance) {
        this.user = user;
        this.bank = bank;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
    }

    // ========== GETTERS E SETTERS ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}