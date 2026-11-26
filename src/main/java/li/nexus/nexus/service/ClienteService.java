package li.nexus.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.ClienteGetDTO;
import li.nexus.nexus.dto.ClientePostDTO;
import li.nexus.nexus.entity.Cliente;
import li.nexus.nexus.exception.CNPJJaCadastradoException;
import li.nexus.nexus.exception.CNPJNaoEncontradoException;
import li.nexus.nexus.exception.NenhumDadoEncontradoException;
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
        if (clienteRepository.existsByCnpj(dto.getCnpj())) {
            throw new CNPJJaCadastradoException("Cliente ja cadastrado com este CNPJ: " + dto.getCnpj());
        }

        return clienteMapper.toGetDTO(clienteRepository.save(clienteMapper.toEntity(dto)));
    }

    @Transactional
    public List<ClienteGetDTO> getClientes() {
        if (clienteRepository.findAll().isEmpty()) {
            throw new NenhumDadoEncontradoException("Nenhum dado cadastrado ou encontrado.");
        }

        return clienteMapper.toGetDTOList(clienteRepository.findAll());
    }

    @Transactional
    public ClienteGetDTO getClienteByCnpj(String cnpj) {
        if (!clienteRepository.existsByCnpj(cnpj)) {
            throw new CNPJNaoEncontradoException("Cliente não encontrado com este CNPJ: " + cnpj);
        }

        return clienteMapper.toGetDTO(clienteRepository.findByCnpj(cnpj));
    }

    @Transactional
    public ClienteGetDTO patchCliente(String cnpj, ClientePostDTO dto) {
        if (!clienteRepository.existsByCnpj(cnpj)) {
            throw new CNPJNaoEncontradoException("Cliente não encontrado com este CNPJ: " + cnpj);
        }

        Cliente cliente = clienteRepository.findByCnpj(cnpj);
        clienteMapper.updateEntity(cliente, dto);
        return clienteMapper.toGetDTO(clienteRepository.save(cliente));
    }

    @Transactional
    public void deleteClienteByCnpj(String cnpj) {
        if (!clienteRepository.existsByCnpj(cnpj)) {
            throw new CNPJNaoEncontradoException("Cliente não encontrado com este CNPJ: " + cnpj);
        }

        clienteRepository.delete(clienteRepository.findByCnpj(cnpj));
    }

}
