package li.nexus.nexus.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import li.nexus.nexus.dto.SolicitacaoGetDTO;
import li.nexus.nexus.dto.SolicitacaoPostDTO;
import li.nexus.nexus.entity.Solicitacao;

@Mapper(componentModel = "spring", uses = { ClienteMapper.class, GerenteMapper.class, PedidoMapper.class })
public interface SolicitacaoMapper {

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "gerente", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    @Mapping (target = "dataDecisao", ignore = true)
    Solicitacao toEntity(SolicitacaoPostDTO dto);

    @Mapping(source = "cliente.nome", target = "clienteNome")
    @Mapping(source = "gerente.nome", target = "gerenteNome")
    @Mapping(source = "pedido.id", target = "pedidoId")
    SolicitacaoGetDTO toGetDTO(Solicitacao entity);

    List<SolicitacaoGetDTO> toGetDTOList(List<Solicitacao> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Solicitacao entity, SolicitacaoPostDTO dto);
}
