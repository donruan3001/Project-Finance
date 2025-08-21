package finance.domain.reports;

import finance.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    private String name;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private ReportType type;
    
    private LocalDate startDate;
    private LocalDate endDate;
    
    @Column(columnDefinition = "TEXT")
    private String reportData;
    
    private String filePath;
    private LocalDateTime createdAt;
    
    public Report(User user, String name, String description, ReportType type, 
                  LocalDate startDate, LocalDate endDate, String reportData) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reportData = reportData;
        this.createdAt = LocalDateTime.now();
    }
}