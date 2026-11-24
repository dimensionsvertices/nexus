package li.nexus.nexus.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import li.nexus.nexus.dto.MaterialGetDTO;
import li.nexus.nexus.dto.MaterialPostDTO;
import li.nexus.nexus.entity.Material;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    Material toEntity(MaterialPostDTO dto);

    MaterialGetDTO toGetDTO(Material entity);

    List<MaterialGetDTO> toGetDTOList(List<Material> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Material entity, MaterialPostDTO dto);
}
