package li.nexus.nexus.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SoftDelete(columnName = "ativo")
@Getter
@Setter
@NoArgsConstructor
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Gerente gerente;
    @ManyToOne(optional = false)
    private Pedido pedido;
    private int desconto;
    @Size(max = 500)
    private String justificativa;
    @Enumerated(EnumType.STRING)
    private EstadoSolicitacao estadoSolicitacao = EstadoSolicitacao.PENDENTE;
    private LocalDateTime dataSolicitacao;
}