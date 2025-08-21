package finance.controllers;

import finance.dto.goals.GoalCreateDTO;
import finance.dto.goals.GoalResponseDTO;
import finance.services.ServiceGoal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/goals")
public class ControllerGoal {
    
    @Autowired
    private ServiceGoal serviceGoal;
    
    @PostMapping
    public ResponseEntity<GoalResponseDTO> createGoal(@Valid @RequestBody GoalCreateDTO data) {
        var goal = serviceGoal.createGoal(data);
        return ResponseEntity.ok(goal);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GoalResponseDTO>> getGoalsByUser(@PathVariable Long userId) {
        var goals = serviceGoal.getGoalsByUser(userId);
        return ResponseEntity.ok(goals);
    }
    
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<GoalResponseDTO>> getActiveGoals(@PathVariable Long userId) {
        var goals = serviceGoal.getActiveGoals(userId);
        return ResponseEntity.ok(goals);
    }
    
    @PatchMapping("/{goalId}/progress")
    public ResponseEntity<GoalResponseDTO> updateGoalProgress(
            @PathVariable Long goalId,
            @RequestParam BigDecimal amount) {
        var goal = serviceGoal.updateGoalProgress(goalId, amount);
        return ResponseEntity.ok(goal);
    }
}