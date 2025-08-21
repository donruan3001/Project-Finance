package finance.controllers;

import finance.dto.budgets.BudgetCreateDTO;
import finance.dto.budgets.BudgetResponseDTO;
import finance.services.ServiceBudget;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/budgets")
public class ControllerBudget {
    
    @Autowired
    private ServiceBudget serviceBudget;
    
    @PostMapping
    public ResponseEntity<BudgetResponseDTO> createBudget(@Valid @RequestBody BudgetCreateDTO data) {
        var budget = serviceBudget.createBudget(data);
        return ResponseEntity.ok(budget);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BudgetResponseDTO>> getBudgetsByUser(@PathVariable Long userId) {
        var budgets = serviceBudget.getBudgetsByUser(userId);
        return ResponseEntity.ok(budgets);
    }
    
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<BudgetResponseDTO>> getActiveBudgets(
            @PathVariable Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        LocalDate targetDate = date != null ? date : LocalDate.now();
        var budgets = serviceBudget.getActiveBudgetsForDate(userId, targetDate);
        return ResponseEntity.ok(budgets);
    }
}