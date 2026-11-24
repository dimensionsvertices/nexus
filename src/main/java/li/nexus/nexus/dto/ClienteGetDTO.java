package li.nexus.nexus.dto;

import li.nexus.nexus.enums.CategoriaCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteGetDTO {
    private String nome;
    private String email;
    private String telefone;
    private Double limite;
    private String cnpj;
    private CategoriaCliente categoria;
}
