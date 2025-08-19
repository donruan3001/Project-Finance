package finance.services;

import finance.domain.banks.Bank;
import finance.dto.banks.BankDTO;
import finance.repository.RepositoryBank;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBank {
@Autowired
    RepositoryBank repositoryBank;

@Transactional
    public Bank createBank(BankDTO data){
        System.out.println("ServiceBank.createBank chamado com: " + data.name());
        var bank = new Bank(data.name().trim());
        System.out.println("Objeto Bank criado: " + bank.getName());
        var savedBank = repositoryBank.save(bank);
        System.out.println("Banco salvo com ID: " + savedBank.getId());
        return savedBank;

    }
    public List<Bank> getAllBanks(){
        return repositoryBank.findAll();
    }
    @Transactional
    public String deleteBank(Long id){
        if (!repositoryBank.existsById(id)) {
            throw new IllegalArgumentException("Banco não encontrado com o ID: " + id);
        }

        repositoryBank.deleteById(id);
        return "Banco com ID " + id + " foi deletado com sucesso.";
    }

@Transactional
    public void updateBank(Long id , BankDTO data) {
        var bank = repositoryBank.findById(id).orElseThrow(() -> new IllegalArgumentException("Banco não encontrado"));
        bank.setName(data.name());
         repositoryBank.save(bank);
    }

}
