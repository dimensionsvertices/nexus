package li.nexus.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.SolicitacaoGetDTO;
import li.nexus.nexus.dto.SolicitacaoPostDTO;
import li.nexus.nexus.entity.Solicitacao;
import li.nexus.nexus.mapper.SolicitacaoMapper;
import li.nexus.nexus.repository.ClienteRepository;
import li.nexus.nexus.repository.GerenteRepository;
import li.nexus.nexus.repository.PedidoRepository;
import li.nexus.nexus.repository.SolicitacaoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final ClienteRepository clienteRepository;
    private final GerenteRepository gerenteRepository;
    private final PedidoRepository pedidoRepository;
    private final SolicitacaoMapper solicitacaoMapper;

    @Transactional
    public SolicitacaoGetDTO saveSolicitacao(SolicitacaoPostDTO dto) {
        Solicitacao solicitacao = solicitacaoMapper.toEntity(dto);
        solicitacao.setCliente(clienteRepository.findByNome(dto.getClienteNome()));
        solicitacao.setGerente(gerenteRepository.findByNome(dto.getGerenteNome()));
        solicitacao.setPedido(pedidoRepository.findById(dto.getPedidoId()).get());
        return solicitacaoMapper.toGetDTO(solicitacaoRepository.save(solicitacao));
    }

    @Transactional
    public List<SolicitacaoGetDTO> getSolicitacaos() {
        return solicitacaoMapper.toGetDTOList(solicitacaoRepository.findAll());
    }

    @Transactional
    public SolicitacaoGetDTO getSolicitacaoById(Long id) {
        return solicitacaoMapper.toGetDTO(solicitacaoRepository.findById(id).get());
    }

    @Transactional
    public SolicitacaoGetDTO patchSolicitacao(Long id, SolicitacaoPostDTO dto) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id).get();
        solicitacaoMapper.updateEntity(solicitacao, dto);
        return solicitacaoMapper.toGetDTO(solicitacaoRepository.save(solicitacao));
    }

    @Transactional
    public void deleteSolicitacaoById(Long id) {
        solicitacaoRepository.delete(solicitacaoRepository.findById(id).get());
    }

}
