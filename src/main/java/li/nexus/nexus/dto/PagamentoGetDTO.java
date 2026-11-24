package li.nexus.nexus.dto;

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
public class PagamentoGetDTO {
    private EstadoPagamento estadoPagamento;
    private FormaPagamento formaPagamento;
}
