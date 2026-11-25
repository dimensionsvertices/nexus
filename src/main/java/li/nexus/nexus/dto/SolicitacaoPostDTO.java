package li.nexus.nexus.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoPostDTO {
    private String clienteNome;
    private String gerenteNome;
    @NotNull(message = "O desconto solicitado é obrigatório.")
    @DecimalMax(value = "10.00", message = "O desconto máximo permitido é 10%.")
    private BigDecimal descontoSolicitado;
    @NotBlank(message = "A justificativa é obrigatória.")
    private String justificativa;
    private Long pedidoId;
}
