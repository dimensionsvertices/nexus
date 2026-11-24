package li.nexus.nexus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GerentePostDTO {
    @NotBlank(message = "O nome do gerente não pode ser vazio.")
    private String nome;

    @NotBlank(message = "O e-mail do gerente não pode ser vazio.")
    @Email(message = "O email do gerente deve ser um email válido.")
    private String email;

    @NotBlank(message = "O telefone do gerente não pode ser vazio.")
    @Pattern(regexp = "^55[1-9]{2}9\\d{8}$", message = "O telefone deve conter o código do país, DDD e celular com 9 dígitos, apenas números.")
    private String telefone;

    @NotBlank(message = "O CPF do gerente não pode ser vazio")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 números.")
    private String cpf;
}
