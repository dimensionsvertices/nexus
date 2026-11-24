package li.nexus.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.GerenteGetDTO;
import li.nexus.nexus.dto.GerentePostDTO;
import li.nexus.nexus.entity.Gerente;
import li.nexus.nexus.exception.CPFNaoEncontradoException;
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
        return gerenteMapper.toGetDto(gerenteRepository.save(gerenteMapper.toEntity(dto)));
    }

    @Transactional
    public List<GerenteGetDTO> getGerentes() {
        return gerenteMapper.toGetDTOList(gerenteRepository.findAll());
    }

    @Transactional
    public GerenteGetDTO getGerenteByCpf(String cnpj) {
        if (!gerenteRepository.existsByCpf(cnpj)) {
            throw new CPFNaoEncontradoException("Gerente não encontrado.");
        }
        return gerenteMapper.toGetDto(gerenteRepository.findByCpf(cnpj));
    }

    @Transactional
    public GerenteGetDTO patchGerente(String cnpj, GerentePostDTO dto) {
        if (!gerenteRepository.existsByCpf(cnpj)) {
            throw new CPFNaoEncontradoException("Gerente não encontrado.");
        }

        Gerente gerente = gerenteRepository.findByCpf(cnpj);
        gerenteMapper.updateEntity(gerente, dto);
        return gerenteMapper.toGetDto(gerenteRepository.save(gerente));
    }

    @Transactional
    public void deleteGerenteByCpf(String cnpj) {
        if (!gerenteRepository.existsByCpf(cnpj)) {
            throw new CPFNaoEncontradoException("Gerente não encontrado.");
        }
        gerenteRepository.delete(gerenteRepository.findByCpf(cnpj));
    }

}
