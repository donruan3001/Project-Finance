package finance.controlers;


import finance.dto.transactions.TransactionCreateDTO;
import finance.dto.transactions.TransactionResponseDTO;
import finance.services.ServiceTransactions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
public class ControllerTransactions {
    @Autowired
    private ServiceTransactions serviceTransactions;


    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody @Valid TransactionCreateDTO data) {
       TransactionResponseDTO response = serviceTransactions.createTransaction(data);
        return ResponseEntity.ok(response);
    }
}