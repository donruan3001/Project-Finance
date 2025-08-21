package finance.controlers;


import finance.dto.transactions.TransactionCreateDTO;
import finance.dto.transactions.TransactionResponseDTO;
import finance.services.ServiceTransactions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class ControllerTransactions {
    @Autowired
    private ServiceTransactions serviceTransactions;


    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody @Valid TransactionCreateDTO data) {
        serviceTransactions.createTransaction(data);
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<TransactionResponseDTO>> getTransactionsByUser(
            @PathVariable Long userId,
            @PageableDefault(size = 20) Pageable pageable) {
        var transactions = serviceTransactions.getTransactionsByUser(userId, pageable);
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<TransactionResponseDTO>> getRecentTransactions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "10") int limit) {
        var transactions = serviceTransactions.getRecentTransactions(userId, limit);
        return ResponseEntity.ok(transactions);
    }
    
    @DeleteMapping("/{transactionId}/user/{userId}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable Long transactionId,
            @PathVariable Long userId) {
        serviceTransactions.deleteTransaction(transactionId, userId);
        return ResponseEntity.noContent().build();
    }
}