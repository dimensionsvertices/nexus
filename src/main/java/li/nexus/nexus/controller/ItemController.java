package li.nexus.nexus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import li.nexus.nexus.dto.ItemGetDTO;
import li.nexus.nexus.dto.ItemPostDTO;
import li.nexus.nexus.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemGetDTO postItem(@RequestBody ItemPostDTO dto) {
        return itemService.saveItem(dto);
    }

    @GetMapping("/{id}")
    public ItemGetDTO getItem(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @GetMapping
    public List<ItemGetDTO> getItems() {
        return itemService.getItens();
    }

    @PatchMapping("/{id}")
    public ItemGetDTO patchItem(@PathVariable Long id, @RequestBody ItemPostDTO dto) {
        return itemService.patchItem(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }

}
