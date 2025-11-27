package finance.controlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.dto.transactions.TransactionCreateDTO;
import finance.dto.transactions.TransactionResponseDTO;
import finance.services.ServiceTransactions;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class ControllerTransactions {
    @Autowired
    private ServiceTransactions serviceTransactions;


    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody @Valid TransactionCreateDTO data) {
       TransactionResponseDTO response = serviceTransactions.createTransaction(data);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<Page<TransactionResponseDTO>> getMyTransactions(
        @PageableDefault(size = 20, page = 0) Pageable pageable) {
        Page<TransactionResponseDTO> transactions = serviceTransactions.getMyTransactions(pageable);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id) {
        TransactionResponseDTO transaction = serviceTransactions.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }
}