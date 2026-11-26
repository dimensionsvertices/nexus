package li.nexus.nexus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import li.nexus.nexus.dto.PedidoGetDTO;
import li.nexus.nexus.dto.PedidoPostDTO;
import li.nexus.nexus.service.PedidoService;
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
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public PedidoGetDTO postPedido(@RequestBody @Valid PedidoPostDTO dto) {
        return pedidoService.savePedido(dto);
    }

    @GetMapping("/{id}")
    public PedidoGetDTO getPedido(@PathVariable Long id) {
        return pedidoService.getPedidoById(id);
    }

    @GetMapping
    public List<PedidoGetDTO> getPedidos() {
        return pedidoService.getPedidos();
    }

    @PatchMapping("/{id}")
    public PedidoGetDTO patchPedido(@PathVariable Long id, @RequestBody PedidoPostDTO dto) {
        return pedidoService.patchPedido(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        pedidoService.deletePedidoById(id);
    }

}
