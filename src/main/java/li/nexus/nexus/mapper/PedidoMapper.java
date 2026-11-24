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

@Mapper(componentModel = "spring", uses = MaterialMapper.class)
public interface PedidoMapper {

    @Mapping(source = "nomeCliente", target = "cliente.nome")
    Pedido toEntity(PedidoPostDTO dto);

    PedidoGetDTO toGetDTO(Pedido entity);

    List<PedidoGetDTO> toGetDTOList(List<Pedido> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Pedido entity, PedidoPostDTO dto);
}
