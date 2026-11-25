package li.nexus.nexus.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import li.nexus.nexus.enums.EstadoSolicitacao;
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
    private Integer desconto;
    @NotBlank(message = "A justificativa é obrigatória.")
    private String justificativa;
    private EstadoSolicitacao estadoSolicitacao;
    private LocalDateTime dataDecisao;
    private Long pedidoId;
}
