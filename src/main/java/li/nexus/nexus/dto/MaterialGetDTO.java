package li.nexus.nexus.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialGetDTO {
    private String nome;
    private Integer quantidade;
    private BigDecimal valor;
    private String descricao;
}
