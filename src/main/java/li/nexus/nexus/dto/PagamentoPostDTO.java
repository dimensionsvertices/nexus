package li.nexus.nexus.dto;

import jakarta.validation.constraints.NotNull;
import li.nexus.nexus.enums.EstadoPagamento;
import li.nexus.nexus.enums.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoPostDTO {
    @NotNull(message = "O estado do pagamento não pode ser vazio.")
    private EstadoPagamento estadoPagamento;
    @NotNull(message = "A forma de pagamento não pode ser vazia.")
    private FormaPagamento formaPagamento;
}
