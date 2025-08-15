package finance.controlers;


import finance.dto.transactions.TransactionCreateDTO;
import finance.services.ServiceTransactions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}