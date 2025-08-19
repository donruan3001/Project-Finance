package finance.controlers;

import finance.dto.banks.BankDTO;
import finance.dto.banks.BankResponseDTO;
import finance.repository.RepositoryBank;
import finance.services.ServiceBank;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/banks")
public class ControllerBank {

    @Autowired
   private ServiceBank serviceBank;                                 


    @PostMapping
    ResponseEntity<?> createBank(@RequestBody @Valid BankDTO bank) {

        var saved= serviceBank.createBank(bank);

        return ResponseEntity.ok(saved);
        }
    @GetMapping
    ResponseEntity<?> getAllBanks() {
        var banks = serviceBank.getAllBanks();
        return ResponseEntity.ok(banks);

    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBank(@PathVariable Long id) {
        serviceBank.deleteBank(id);
        String message = "Banco com ID " + id + " foi deletado com sucesso.";
        return ResponseEntity.ok().body(message);    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateBank(@PathVariable Long id, @RequestBody BankDTO bank) {
        serviceBank.updateBank(id, bank);
        return ResponseEntity.ok().body(bank);
    }

}
