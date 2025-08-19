// finance/api/dto/AccountResponseDTO.java
package finance.dto.accounts;

import finance.domain.acounts.AccountType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record AccountResponseDTO(
        Long id,
        Long userId,
        Long bankId,
        String name,
        AccountType type,
        BigDecimal balance,
        LocalDateTime createdAt
) {}
