package li.nexus.nexus.service;

import java.util.List;

import li.nexus.nexus.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.ItemGetDTO;
import li.nexus.nexus.dto.ItemPostDTO;
import li.nexus.nexus.entity.Item;
import li.nexus.nexus.exception.ItemNaoEncontradoException;
import li.nexus.nexus.exception.MaterialNaoEncontradoException;
import li.nexus.nexus.exception.NenhumDadoEncontradoException;
import li.nexus.nexus.exception.PedidoNaoEncontradoException;
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
        if (!pedidoRepository.existsById(dto.getPedidoId())) {
            throw new PedidoNaoEncontradoException("Pedido não encontrado com o ID: " + dto.getPedidoId());
        }

        if (!materialRepository.existsByNome(dto.getNomeMaterial())) {
            throw new MaterialNaoEncontradoException("Material não encontrado com o nome: " + dto.getNomeMaterial());
        }

        Item item = itemMapper.toEntity(dto);
        item.setPedido(pedidoRepository.findById(dto.getPedidoId()).get());
        item.setMaterial(materialRepository.findByNome(dto.getNomeMaterial()));
        return itemMapper.toGetDTO(itemRepository.save(item));
    }

    @Transactional
    public List<ItemGetDTO> getItens() {
        if (itemRepository.findAll().isEmpty()) {
            throw new NenhumDadoEncontradoException("Nenhum dado encontrado ou cadastrado.");
        }

        return itemMapper.toGetDTOList(itemRepository.findAll());
    }

    @Transactional
    public ItemGetDTO getItemById(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNaoEncontradoException("Item não encontrado com o ID: " + id);
        }

        return itemMapper.toGetDTO(itemRepository.findById(id).get());
    }

    @Transactional
    public ItemGetDTO patchItem(Long id, ItemPostDTO dto) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNaoEncontradoException("Item não encontrado com o ID: " + id);
        }

        Item item = itemRepository.findById(id).get();
        itemMapper.updateEntity(item, dto);
        return itemMapper.toGetDTO(itemRepository.save(item));
    }

    @Transactional
    public void deleteItemById(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNaoEncontradoException("Item não encontrado com o ID: " + id);
        }

        itemRepository.delete(itemRepository.findById(id).get());
    }

}
