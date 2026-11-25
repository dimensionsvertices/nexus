package li.nexus.nexus.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import li.nexus.nexus.dto.ItemGetDTO;
import li.nexus.nexus.dto.ItemPostDTO;
import li.nexus.nexus.entity.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "material", ignore = true)
    Item toEntity(ItemPostDTO dto);

    ItemGetDTO toGetDTO(Item entity);

    List<ItemGetDTO> toGetDTOList(List<Item> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Item entity, ItemPostDTO dto);
}
