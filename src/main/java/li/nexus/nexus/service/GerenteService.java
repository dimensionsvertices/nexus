package li.nexus.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.GerenteGetDTO;
import li.nexus.nexus.dto.GerentePostDTO;
import li.nexus.nexus.entity.Gerente;
import li.nexus.nexus.exception.CPFJaCadastradoException;
import li.nexus.nexus.exception.CPFNaoEncontradoException;
import li.nexus.nexus.exception.NenhumDadoEncontradoException;
import li.nexus.nexus.mapper.GerenteMapper;
import li.nexus.nexus.repository.GerenteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GerenteService {

    private final GerenteRepository gerenteRepository;
    private final GerenteMapper gerenteMapper;

    @Transactional
    public GerenteGetDTO saveGerente(GerentePostDTO dto) {
        if (gerenteRepository.existsByCpf(dto.getCpf())) {
            throw new CPFJaCadastradoException("Gerente já cadastrado com este CPF:" + dto.getCpf());
        }

        return gerenteMapper.toGetDto(gerenteRepository.save(gerenteMapper.toEntity(dto)));
    }

    @Transactional
    public List<GerenteGetDTO> getGerentes() {
        if (gerenteRepository.findAll().isEmpty()) {
            throw new NenhumDadoEncontradoException("Nenhum dado cadastrado ou encontrado.");
        }

        return gerenteMapper.toGetDTOList(gerenteRepository.findAll());
    }

    @Transactional
    public GerenteGetDTO getGerenteByCpf(String cpf) {
        if (!gerenteRepository.existsByCpf(cpf)) {
            throw new CPFNaoEncontradoException("Gerente não encontrado com este CPF: " + cpf);
        }

        return gerenteMapper.toGetDto(gerenteRepository.findByCpf(cpf));
    }

    @Transactional
    public GerenteGetDTO patchGerente(String cpf, GerentePostDTO dto) {
        if (!gerenteRepository.existsByCpf(cpf)) {
            throw new CPFNaoEncontradoException("Gerente não encontrado com este CPF: " + cpf);
        }

        Gerente gerente = gerenteRepository.findByCpf(cpf);
        gerenteMapper.updateEntity(gerente, dto);
        return gerenteMapper.toGetDto(gerenteRepository.save(gerente));
    }

    @Transactional
    public void deleteGerenteByCpf(String cpf) {
        if (!gerenteRepository.existsByCpf(cpf)) {
            throw new CPFNaoEncontradoException("Gerente não encontrado com este CPF: " + cpf);
        }

        gerenteRepository.delete(gerenteRepository.findByCpf(cpf));
    }

}
