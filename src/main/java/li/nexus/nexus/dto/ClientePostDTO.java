package li.nexus.nexus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import li.nexus.nexus.enums.CategoriaCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientePostDTO {
    @NotBlank(message = "O nome do cliente não pode ser vazio.")
    private String nome;

    @NotBlank(message = "O email do cliente não pode ser vazio")
    @Email(message = "O email do cliente deve ser um email válido.")
    private String email;

    @NotBlank(message = "O telefone do cliente não pode ser vazio.")
    private String telefone;

    @NotNull(message = "O limite de crédito do cliente não pode ser nulo.")
    @Positive(message = "O limite de crédito do cliente deve ser maior que zero")
    private Double limite;

    @NotBlank(message = "O CNPJ do cliente não pode ser vazio.")
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter exatamente 14 números.")
    private String cnpj;

    @NotNull(message = "A categoria do cliente não pode ser nula.")
    private CategoriaCliente categoria;
}
