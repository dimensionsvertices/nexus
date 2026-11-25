package li.nexus.nexus.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import li.nexus.nexus.dto.PagamentoGetDTO;
import li.nexus.nexus.dto.PagamentoPostDTO;
import li.nexus.nexus.entity.Pagamento;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
    
    @Mapping(target = "id", ignore = true)
    Pagamento toEntity(PagamentoPostDTO dto);

    PagamentoGetDTO toGetDTO(Pagamento entity);

    List<PagamentoGetDTO> toGetDTOList(List<Pagamento> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Pagamento entity, PagamentoPostDTO dto);
}
