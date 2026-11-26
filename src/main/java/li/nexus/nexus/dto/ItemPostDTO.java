package li.nexus.nexus.dto;

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
public class ItemPostDTO {
    @NotNull(message = "O ID do pedido não pode ser vazio")
    @Positive(message = "O ID do pedido deve ser maior que 0")
    private Long pedidoId;
    @NotBlank(message = "O nome do material não pode ser vazio")
    private String nomeMaterial;
    @Positive(message = "A quantidade de itens deve ser maior que zero")
    private Integer quantidade;
}
