package li.nexus.nexus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import li.nexus.nexus.dto.MaterialGetDTO;
import li.nexus.nexus.dto.MaterialPostDTO;
import li.nexus.nexus.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/materiais")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping
    public MaterialGetDTO postMaterial(@RequestBody MaterialPostDTO dto) {
        return materialService.saveMaterial(dto);
    }

    @GetMapping("/{nome}")
    public MaterialGetDTO getMaterial(@PathVariable String nome) {
        return materialService.getMaterialByNome(nome);
    }

    @GetMapping
    public List<MaterialGetDTO> getMaterials() {
        return materialService.getMaterials();
    }

    @PatchMapping("/{nome}")
    public MaterialGetDTO patchMaterial(@PathVariable String nome, @RequestBody MaterialPostDTO dto) {
        return materialService.patchMaterial(nome, dto);
    }

    @DeleteMapping("/{nome}")
    public void deleteMaterial(@PathVariable String nome) {
        materialService.deleteMaterialByNome(nome);
    }

}
