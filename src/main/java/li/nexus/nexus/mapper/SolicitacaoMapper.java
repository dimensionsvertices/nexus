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

@Mapper(componentModel = "spring")
public interface SolicitacaoMapper {

    @Mapping(target = "gerente", ignore = true)
    @Mapping(target = "estadoSolicitacao", ignore = true)
    @Mapping(target = "dataSolicitacao", ignore = true)
    Solicitacao toEntity(SolicitacaoPostDTO dto);

    SolicitacaoGetDTO toGetDTO(Solicitacao entity);

    @Mapping(source = "gerente.nome", target = "gerenteNome")
    List<SolicitacaoGetDTO> toGetDTOList(List<Solicitacao> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Solicitacao entity, SolicitacaoPostDTO dto);
}
