package li.nexus.nexus.dto;

import java.time.LocalDate;
import java.util.Map;

import li.nexus.nexus.enums.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoGetDTO {
    private LocalDate dataVencimento;
    private EstadoPedido estadoPedido;
    private String clienteNome;
    private Map<String, Integer> quantidadeMateriais;
}
