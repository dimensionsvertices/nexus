package li.nexus.nexus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GerenteGetDTO {
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
}
