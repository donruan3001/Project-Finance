package finance.repository;

import finance.domain.reports.Report;
import finance.domain.reports.ReportType;
import finance.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryReport extends JpaRepository<Report, Long> {
    List<Report> findByUserOrderByCreatedAtDesc(User user);
    List<Report> findByUserAndType(User user, ReportType type);
}