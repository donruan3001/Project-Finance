package finance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import finance.domain.goals.Goal;
import finance.domain.goals.GoalStatus;
import finance.domain.user.User;

public interface RepositoryGoal extends JpaRepository<Goal, Long> {
    List<Goal> findByUserAndStatus(User user, GoalStatus status);
    List<Goal> findByUser(User user);
    
    @Query("SELECT g FROM Goal g WHERE g.user = :user AND g.targetDate <= :date AND g.status = 'ACTIVE'")
    List<Goal> findGoalsNearDeadline(@Param("user") User user, @Param("date") LocalDate date);
    
    @Query("SELECT g FROM Goal g WHERE g.user = :user AND g.currentAmount >= g.targetAmount AND g.status = 'ACTIVE'")
    List<Goal> findCompletedGoals(@Param("user") User user);
}