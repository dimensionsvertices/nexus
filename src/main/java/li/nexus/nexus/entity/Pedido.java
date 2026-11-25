package li.nexus.nexus.entity;

import java.time.LocalDate;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Future;
import li.nexus.nexus.enums.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SoftDelete(columnName = "inativo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEmissao;
    @Future
    private LocalDate dataVencimento;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;
    @ManyToOne(optional = false)
    private Cliente cliente;
    @OneToOne
    private Pagamento pagamento;
}