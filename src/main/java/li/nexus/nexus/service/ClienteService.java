package li.nexus.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.ClienteGetDTO;
import li.nexus.nexus.dto.ClientePostDTO;
import li.nexus.nexus.entity.Cliente;
import li.nexus.nexus.mapper.ClienteMapper;
import li.nexus.nexus.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Transactional
    public ClienteGetDTO saveCliente(ClientePostDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("O email do cliente já está em uso.");
        }
        if (clienteRepository.existsByCnpj(dto.getCnpj())) {
            throw new IllegalArgumentException("O CNPJ do cliente já está em uso.");
        }
        return clienteMapper.toGetDTO(clienteRepository.save(clienteMapper.toEntity(dto)));
    }

    @Transactional
    public List<ClienteGetDTO> getClientes() {
        return clienteMapper.toGetDTOList(clienteRepository.findAll());
    }

    @Transactional
    public ClienteGetDTO getClienteByCnpj(String cnpj) {
        if (!clienteRepository.existsByCnpj(cnpj)) {
            throw new IllegalArgumentException("Cliente não encontrado com o CNPJ: " + cnpj);
        }
        return clienteMapper.toGetDTO(clienteRepository.findByCnpj(cnpj));
    }

    @Transactional
    public ClienteGetDTO patchCliente(String cnpj, ClientePostDTO dto) {
        if (!clienteRepository.existsByCnpj(cnpj)) {
            throw new IllegalArgumentException("Cliente não encontrado com o CNPJ: " + cnpj);
        }

        Cliente cliente = clienteRepository.findByCnpj(cnpj);
        clienteMapper.updateEntity(cliente, dto);
        return clienteMapper.toGetDTO(clienteRepository.save(cliente));
    }

    @Transactional
    public void deleteClienteByCnpj(String cnpj) {
        if (!clienteRepository.existsByCnpj(cnpj)) {
            throw new IllegalArgumentException("Cliente não encontrado com o CNPJ: " + cnpj);
        }
        clienteRepository.delete(clienteRepository.findByCnpj(cnpj));
    }

}
