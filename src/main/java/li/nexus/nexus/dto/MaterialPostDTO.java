package li.nexus.nexus.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialPostDTO {
    @NotBlank(message = "O nome do material não pode ser vazio.")
    private String nome;
    @NotNull(message = "A quantidade do material não pode ser nula.")
    @Positive(message = "A quantidade do material deve ser maior que zero.")
    private Integer quantidade;
    @NotNull(message = "O valor do material não pode ser vazio.")
    @Positive(message = "O valor do material deve ser maior que zero.")
    private BigDecimal valor;
    @NotBlank(message = "A descrição do material não pode ser vazio")
    private String descricao;
}
