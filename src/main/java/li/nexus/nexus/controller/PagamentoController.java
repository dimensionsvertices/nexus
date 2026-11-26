package li.nexus.nexus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import li.nexus.nexus.dto.PagamentoGetDTO;
import li.nexus.nexus.dto.PagamentoPostDTO;
import li.nexus.nexus.service.PagamentoService;
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
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public PagamentoGetDTO postPagamento(@RequestBody @Valid PagamentoPostDTO dto) {
        return pagamentoService.savePagamento(dto);
    }

    @GetMapping("/{id}")
    public PagamentoGetDTO getPagamento(@PathVariable Long id) {
        return pagamentoService.getPagamentoById(id);
    }

    @GetMapping
    public List<PagamentoGetDTO> getPagamentos() {
        return pagamentoService.getPagamentos();
    }

    @PatchMapping("/{id}")
    public PagamentoGetDTO patchPagamento(@PathVariable Long id, @RequestBody PagamentoPostDTO dto) {
        return pagamentoService.patchPagamento(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePagamento(@PathVariable Long id) {
        pagamentoService.deletePagamentoById(id);
    }

}
