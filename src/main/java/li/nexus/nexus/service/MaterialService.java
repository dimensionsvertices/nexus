package li.nexus.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.MaterialGetDTO;
import li.nexus.nexus.dto.MaterialPostDTO;
import li.nexus.nexus.entity.Material;
import li.nexus.nexus.exception.NomeNaoEncontradoException;
import li.nexus.nexus.mapper.MaterialMapper;
import li.nexus.nexus.repository.MaterialRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Transactional
    public MaterialGetDTO saveMaterial(MaterialPostDTO dto) {
        return materialMapper.toGetDTO(materialRepository.save(materialMapper.toEntity(dto)));
    }

    @Transactional
    public List<MaterialGetDTO> getMaterials() {
        return materialMapper.toGetDTOList(materialRepository.findAll());
    }

    @Transactional
    public MaterialGetDTO getMaterialByNome(String nome) {
        return materialMapper.toGetDTO(materialRepository.findByNome(nome));
    }

    @Transactional
    public MaterialGetDTO patchMaterial(String nome, MaterialPostDTO dto) {
        if (!materialRepository.existsByNome(nome)) {
            throw new NomeNaoEncontradoException("Material não encontrado.");
        }

        Material material = materialRepository.findByNome(nome);
        materialMapper.updateEntity(material, dto);
        return materialMapper.toGetDTO(materialRepository.save(material));
    }

    @Transactional
    public void deleteMaterialByNome(String nome) {
        if (!materialRepository.existsByNome(nome)) {
            throw new NomeNaoEncontradoException("Material não encontrado.");
        }
        materialRepository.delete(materialRepository.findByNome(nome));
    }

}
