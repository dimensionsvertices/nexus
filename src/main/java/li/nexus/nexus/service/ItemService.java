package li.nexus.nexus.service;

import java.util.List;

import li.nexus.nexus.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.ItemGetDTO;
import li.nexus.nexus.dto.ItemPostDTO;
import li.nexus.nexus.entity.Item;
import li.nexus.nexus.mapper.ItemMapper;
import li.nexus.nexus.repository.ItemRepository;
import li.nexus.nexus.repository.MaterialRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ItemService {

    private final PedidoRepository pedidoRepository;
    private final ItemRepository itemRepository;
    private final MaterialRepository materialRepository;
    private final ItemMapper itemMapper;

    @Transactional
    public ItemGetDTO saveItem(ItemPostDTO dto) {
        Item item = itemMapper.toEntity(dto);
        item.setPedido(pedidoRepository.findById(dto.getPedidoId()).get());
        item.setMaterial(materialRepository.findByNome(dto.getNomeMaterial()));
        return itemMapper.toGetDTO(itemRepository.save(item));
    }

    @Transactional
    public List<ItemGetDTO> getItens() {
        return itemMapper.toGetDTOList(itemRepository.findAll());
    }

    @Transactional
    public ItemGetDTO getItemById(Long id) {
        return itemMapper.toGetDTO(itemRepository.findById(id).get());
    }

    @Transactional
    public ItemGetDTO patchItem(Long id, ItemPostDTO dto) {
        Item item = itemRepository.findById(id).get();
        itemMapper.updateEntity(item, dto);
        return itemMapper.toGetDTO(itemRepository.save(item));
    }

    @Transactional
    public void deleteItemById(Long id) {
        itemRepository.delete(itemRepository.findById(id).get());
    }

}
