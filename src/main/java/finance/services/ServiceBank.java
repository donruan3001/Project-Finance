package finance.services;

import finance.domain.banks.Bank;
import finance.dto.banks.BankDTO;
import finance.repository.RepositoryBank;
import finance.validators.transactions.ValidatorTransaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBank {
@Autowired
    RepositoryBank repositoryBank;
@Autowired
List<ValidatorTransaction> validators;

@Transactional

@PreAuthorize("hasRole('ADMIN')")
    public Bank createBank(BankDTO data){

        var bank = new Bank(data.name().trim());
        var savedBank = repositoryBank.save(bank);
        return savedBank;

    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Bank> getAllBanks(){
        return repositoryBank.findAll();
    }
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBank(Long id){
        if (!repositoryBank.existsById(id)) {
            throw new IllegalArgumentException("Banco não encontrado com o ID: " + id);
        }

        repositoryBank.deleteById(id);
        return "Banco com ID " + id + " foi deletado com sucesso.";
    }

@Transactional
@PreAuthorize("hasRole('ADMIN')")
    public void updateBank(Long id , BankDTO data) {
        var bank = repositoryBank.findById(id).orElseThrow(() -> new IllegalArgumentException("Banco não encontrado"));
        bank.setName(data.name());
         repositoryBank.save(bank);
    }

}
