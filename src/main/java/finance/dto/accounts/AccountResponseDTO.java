// finance/api/dto/AccountResponseDTO.java
package finance.dto.accounts;

import java.math.BigDecimal;
import java.time.Instant;

public record AccountResponseDTO(
        Long id,
        Long userId,
        Long bankId,
        String name,
        String type,
        BigDecimal balance,
        Instant createdAt
) {}
