package finance.controlers;

import finance.dto.banks.BankDTO;
import finance.repository.RepositoryBank;
import finance.services.ServiceBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/bank")
public class ControllerBank {
    @Autowired
   private ServiceBank serviceBank;

    @PostMapping
    ResponseEntity<?> createBank(@RequestBody BankDTO bank) {
       serviceBank.createBank(bank);
        return ResponseEntity.created(null).build();
    }
    @GetMapping
    ResponseEntity<?> getAllBanks() {
        var banks = serviceBank.getAllBanks();
        return ResponseEntity.ok(banks);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBank(@PathVariable Long id) {
        serviceBank.deleteBank(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateBank(@PathVariable Long id, @RequestBody BankDTO bank) {
        serviceBank.updateBank(id, bank);
        return ResponseEntity.ok().build();
    }

}
