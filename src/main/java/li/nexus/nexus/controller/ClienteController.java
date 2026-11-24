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

    @GetMapping("/{cpf}")
    public ClienteGetDTO getCliente(@PathVariable String cpf) {
        return clienteService.getClienteByCnpj(cpf);
    }

    @GetMapping
    public List<ClienteGetDTO> getClientes() {
        return clienteService.getClientes();
    }

    @PatchMapping("/{cpf}")
    public ClienteGetDTO patchCliente(@PathVariable String cpf, @RequestBody ClientePostDTO dto) {
        return clienteService.patchCliente(cpf, dto);
    }

    @DeleteMapping("/{cpf}")
    public void deleteCliente(@PathVariable String cpf) {
        clienteService.deleteClienteByCnpj(cpf);
    }

}
