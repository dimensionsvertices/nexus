package li.nexus.nexus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import li.nexus.nexus.dto.ClienteGetDTO;
import li.nexus.nexus.dto.ClientePostDTO;
import li.nexus.nexus.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ClienteGetDTO postCliente(@RequestBody ClientePostDTO dto) {
        return clienteService.saveCliente(dto);
    }

    @GetMapping("/{cnpj}")
    public ClienteGetDTO getCliente(@PathVariable String cnpj) {
        return clienteService.getClienteByCnpj(cnpj);
    }

    @GetMapping
    public List<ClienteGetDTO> getClientes() {
        return clienteService.getClientes();
    }

    @PatchMapping("/{cnpj}")
    public ClienteGetDTO patchCliente(@PathVariable String cnpj, @RequestBody ClientePostDTO dto) {
        return clienteService.patchCliente(cnpj, dto);
    }

    @DeleteMapping("/{cnpj}")
    public void deleteCliente(@PathVariable String cnpj) {
        clienteService.deleteClienteByCnpj(cnpj);
    }

}
