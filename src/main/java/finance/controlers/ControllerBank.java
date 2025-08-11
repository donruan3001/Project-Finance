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
    ResponseEntity<?> createBank(@RequestBody @Valid BankDTO bank, UriComponentsBuilder  uri) {

        var saved= serviceBank.createBank(bank);
        var urilocation=uri.path("/banks/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(urilocation).body(new BankResponseDTO(saved.getId(),saved.getName()));

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
        return ResponseEntity.ok().body(bank);
    }

}
