package finance.domain.acounts;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AccountType {
    CONTA_CONJUNTA,CONTA_CORRENTE,CONTA_CREDITO,CONTA_DIGITAL,
            CONTA_EMPRESARIAL,CONTA_ESTUDANTIL,CONTA_FIDELIDADE,
            CONTA_INTERNACIONAL,CONTA_INVESTIMENTO,CONTA_POUPANCA,
            CONTA_SALARIO,CONTA_UNIVERSITARIA;
    @JsonCreator
    public static AccountType fromString(String value) {
        return AccountType.valueOf(value.toUpperCase());
    }
    }
