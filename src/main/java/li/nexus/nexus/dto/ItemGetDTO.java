package li.nexus.nexus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemGetDTO {
    private Long pedidoId;
    private String nomeMaterial;
    private Integer quantidade;
}
