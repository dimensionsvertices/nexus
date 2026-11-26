package li.nexus.nexus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import li.nexus.nexus.dto.GerenteGetDTO;
import li.nexus.nexus.dto.GerentePostDTO;
import li.nexus.nexus.service.GerenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/gerentes")
@RequiredArgsConstructor
public class GerenteController {

    private final GerenteService gerenteService;

    @PostMapping
    public GerenteGetDTO postGerente(@RequestBody @Valid GerentePostDTO dto) {
        return gerenteService.saveGerente(dto);
    }

    @GetMapping("/{cpf}")
    public GerenteGetDTO getGerente(@PathVariable String cpf) {
        return gerenteService.getGerenteByCpf(cpf);
    }

    @GetMapping
    public List<GerenteGetDTO> getGerentes() {
        return gerenteService.getGerentes();
    }

    @PatchMapping("/{cpf}")
    public GerenteGetDTO patchGerente(@PathVariable String cpf, @RequestBody GerentePostDTO dto) {
        return gerenteService.patchGerente(cpf, dto);
    }

    @DeleteMapping("/{cpf}")
    public void deleteGerente(@PathVariable String cpf) {
        gerenteService.deleteGerenteByCpf(cpf);
    }

}
