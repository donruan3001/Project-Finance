package finance.dto.banks;

import jakarta.validation.constraints.NotBlank;

public record BankDTO(@NotBlank  String name) {
}
