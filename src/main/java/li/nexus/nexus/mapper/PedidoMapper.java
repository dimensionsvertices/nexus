package li.nexus.nexus.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import li.nexus.nexus.dto.PedidoGetDTO;
import li.nexus.nexus.dto.PedidoPostDTO;
import li.nexus.nexus.entity.Pedido;

@Mapper(componentModel = "spring", uses = { ClienteMapper.class, PagamentoMapper.class })
public interface PedidoMapper {
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "pagamento", ignore = true)
    Pedido toEntity(PedidoPostDTO dto);

    @Mapping(source = "cliente.nome", target = "clienteNome")
    PedidoGetDTO toGetDTO(Pedido entity);

    List<PedidoGetDTO> toGetDTOList(List<Pedido> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Pedido entity, PedidoPostDTO dto);
}