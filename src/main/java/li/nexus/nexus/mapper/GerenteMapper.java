package li.nexus.nexus.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import li.nexus.nexus.dto.GerenteGetDTO;
import li.nexus.nexus.dto.GerentePostDTO;
import li.nexus.nexus.entity.Gerente;

@Mapper(componentModel = "spring")
public interface GerenteMapper {
    @Mapping(target = "id", ignore = true)
    Gerente toEntity(GerentePostDTO dto);

    GerenteGetDTO toGetDto(Gerente entity);

    List<GerenteGetDTO> toGetDTOList(List<Gerente> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Gerente entity, GerentePostDTO dto);
}
