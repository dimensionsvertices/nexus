package li.nexus.nexus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import li.nexus.nexus.dto.SolicitacaoGetDTO;
import li.nexus.nexus.dto.SolicitacaoPostDTO;
import li.nexus.nexus.service.SolicitacaoService;
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
@RequestMapping("/solicitacoes")
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    @PostMapping
    public SolicitacaoGetDTO postSolicitacao(@RequestBody @Valid SolicitacaoPostDTO dto) {
        return solicitacaoService.saveSolicitacao(dto);
    }

    @GetMapping("/{id}")
    public SolicitacaoGetDTO getSolicitacao(@PathVariable Long id) {
        return solicitacaoService.getSolicitacaoById(id);
    }

    @GetMapping
    public List<SolicitacaoGetDTO> getSolicitacaos() {
        return solicitacaoService.getSolicitacaos();
    }

    @PatchMapping("/{id}")
    public SolicitacaoGetDTO patchSolicitacao(@PathVariable Long id, @RequestBody SolicitacaoPostDTO dto) {
        return solicitacaoService.patchSolicitacao(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSolicitacao(@PathVariable Long id) {
        solicitacaoService.deleteSolicitacaoById(id);
    }

}
