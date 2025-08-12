package finance.domain.acounts;

import finance.domain.banks.Bank;
import finance.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accounts") // Uncomment if you want to map this class to a database
@Entity(name="accounts") // Uncomment if you want to map this class to a database
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false,foreignKey = @ForeignKey (name = "fk_user_account"))
    private User user_id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bank_id", nullable=false,foreignKey = @ForeignKey (name = "fk_bank_account"))
    private Bank bank_id;
    private String name;
    private String type;
    private boolean balance;
    private Date created_at;
}
