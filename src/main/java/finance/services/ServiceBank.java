package finance.services;

import finance.domain.banks.Bank;
import finance.dto.banks.BankDTO;
import finance.repository.RepositoryBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBank {
@Autowired
    RepositoryBank repositoryBank;

    public void createBank(BankDTO data){
    var bank = new Bank(data.name());
            repositoryBank.save(bank);

    }
    public List<Bank> getAllBanks(){
        return repositoryBank.findAll();
    }
    public void deleteBank(Long id){
        repositoryBank.deleteById(id);
    }


    public void updateBank(Long id , BankDTO data) {
        var bank = repositoryBank.findById(id).orElseThrow(() -> new IllegalArgumentException("Banco não encontrado"));
        bank.setName(data.name());
         repositoryBank.save(bank);
    }
}
