package li.nexus.nexus.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import li.nexus.nexus.enums.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoPostDTO {
    @NotNull(message = "A data de vencimento não pode ser vazia.")
    @FutureOrPresent(message = "O vencimento precisa ser uma data posterior a atual ou a data atual.")
    private LocalDate dataVencimento;

    @NotNull(message = "O estado do pedido não pode ser vazio.")
    private EstadoPedido estadoPedido;

    @NotBlank(message = "O nome do cliente não pode ser vazio.")
    private String clienteNome;

    @NotNull(message = "O pagamento do pedido não pode ser vazio.")
    private Long pagamentoId;

}
