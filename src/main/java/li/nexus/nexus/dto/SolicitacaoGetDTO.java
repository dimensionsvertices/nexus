package li.nexus.nexus.dto;

import java.time.LocalDateTime;

import li.nexus.nexus.enums.EstadoSolicitacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoGetDTO {
    private String clienteNome;
    private String gerenteNome;
    private int desconto;
    private String justificativa;
    private EstadoSolicitacao estadoSolicitacao;
    private LocalDateTime dataDecisao;
}
