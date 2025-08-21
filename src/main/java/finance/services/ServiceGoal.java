package finance.services;

import finance.domain.goals.Goal;
import finance.domain.goals.GoalStatus;
import finance.dto.goals.GoalCreateDTO;
import finance.dto.goals.GoalResponseDTO;
import finance.repository.RepositoryGoal;
import finance.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ServiceGoal {
    
    @Autowired
    private RepositoryGoal repositoryGoal;
    
    @Autowired
    private RepositoryUser repositoryUser;
    
    @Transactional
    public GoalResponseDTO createGoal(GoalCreateDTO data) {
        var user = repositoryUser.findById(data.userId())
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        var goal = new Goal(
            user,
            data.name().trim(),
            data.description(),
            data.targetAmount(),
            data.targetDate(),
            data.priority(),
            data.icon(),
            data.color()
        );
        
        var savedGoal = repositoryGoal.save(goal);
        return mapToResponseDTO(savedGoal);
    }
    
    public List<GoalResponseDTO> getGoalsByUser(Long userId) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        return repositoryGoal.findByUser(user)
            .stream()
            .map(this::mapToResponseDTO)
            .toList();
    }
    
    public List<GoalResponseDTO> getActiveGoals(Long userId) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        return repositoryGoal.findByUserAndStatus(user, GoalStatus.ACTIVE)
            .stream()
            .map(this::mapToResponseDTO)
            .toList();
    }
    
    @Transactional
    public GoalResponseDTO updateGoalProgress(Long goalId, BigDecimal amount) {
        var goal = repositoryGoal.findById(goalId)
            .orElseThrow(() -> new IllegalArgumentException("Meta não encontrada"));
        
        goal.setCurrentAmount(goal.getCurrentAmount().add(amount));
        
        // Check if goal is completed
        if (goal.getCurrentAmount().compareTo(goal.getTargetAmount()) >= 0) {
            goal.setStatus(GoalStatus.COMPLETED);
        }
        
        var savedGoal = repositoryGoal.save(goal);
        return mapToResponseDTO(savedGoal);
    }
    
    private GoalResponseDTO mapToResponseDTO(Goal goal) {
        Double progressPercentage = goal.getCurrentAmount()
            .divide(goal.getTargetAmount(), 4, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100))
            .doubleValue();
        
        Long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), goal.getTargetDate());
        
        return new GoalResponseDTO(
            goal.getId(),
            goal.getUser().getId(),
            goal.getName(),
            goal.getDescription(),
            goal.getTargetAmount(),
            goal.getCurrentAmount(),
            progressPercentage,
            goal.getTargetDate(),
            goal.getStatus(),
            goal.getPriority(),
            goal.getIcon(),
            goal.getColor(),
            daysRemaining,
            goal.getCreatedAt()
        );
    }
}