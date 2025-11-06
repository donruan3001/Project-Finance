package finance.domain.banks;


import jakarta.persistence.*;



@Entity
@Table(name = "banks")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 🔹 Construtor vazio (obrigatório para o JPA)
    public Bank() {
    }

    // 🔹 Construtor completo
    public Bank(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 🔹 Construtor simples (caso queira criar só com nome)
    public Bank(String name) {
        this.name = name;
    }

    // ========== GETTERS E SETTERS ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
