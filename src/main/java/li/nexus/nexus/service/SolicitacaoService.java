package li.nexus.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.SolicitacaoGetDTO;
import li.nexus.nexus.dto.SolicitacaoPostDTO;
import li.nexus.nexus.entity.Solicitacao;
import li.nexus.nexus.exception.NenhumDadoEncontradoException;
import li.nexus.nexus.exception.NomeNaoEncontradoException;
import li.nexus.nexus.exception.PedidoNaoEncontradoException;
import li.nexus.nexus.exception.SolicitacaoNaoEncontradaException;
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
        if (!clienteRepository.existsByNome(dto.getClienteNome())) {
            throw new NomeNaoEncontradoException("Cliente nao encontrado com este nome: " + dto.getClienteNome());
        }

        if (!gerenteRepository.existsByNome(dto.getGerenteNome())) {
            throw new NomeNaoEncontradoException("Gerente nao encontrado com este nome: " + dto.getGerenteNome());
        }

        if (!pedidoRepository.existsById(dto.getPedidoId())) {
            throw new PedidoNaoEncontradoException("Pedido nao encontrado com o ID: " + dto.getPedidoId());
        }

        Solicitacao solicitacao = solicitacaoMapper.toEntity(dto);
        solicitacao.setCliente(clienteRepository.findByNome(dto.getClienteNome()));
        solicitacao.setGerente(gerenteRepository.findByNome(dto.getGerenteNome()));
        solicitacao.setPedido(pedidoRepository.findById(dto.getPedidoId()).get());
        return solicitacaoMapper.toGetDTO(solicitacaoRepository.save(solicitacao));
    }

    @Transactional
    public List<SolicitacaoGetDTO> getSolicitacaos() {
        if (solicitacaoRepository.findAll().isEmpty()) {
            throw new NenhumDadoEncontradoException("Nenhum dado encontrado ou cadastrado");
        }

        return solicitacaoMapper.toGetDTOList(solicitacaoRepository.findAll());
    }

    @Transactional
    public SolicitacaoGetDTO getSolicitacaoById(Long id) {
        if (!solicitacaoRepository.existsById(id)) {
            throw new SolicitacaoNaoEncontradaException("Solicitacao não encontrada com o ID: " + id);
        }

        return solicitacaoMapper.toGetDTO(solicitacaoRepository.findById(id).get());
    }

    @Transactional
    public SolicitacaoGetDTO patchSolicitacao(Long id, SolicitacaoPostDTO dto) {
        if (!solicitacaoRepository.existsById(id)) {
            throw new SolicitacaoNaoEncontradaException("Solicitacao não encontrada com o ID: " + id);
        }

        Solicitacao solicitacao = solicitacaoRepository.findById(id).get();
        solicitacaoMapper.updateEntity(solicitacao, dto);

        if (dto.getClienteNome() != null) {
            if (!clienteRepository.existsByNome(dto.getClienteNome())) {
                throw new NomeNaoEncontradoException(
                        "Cliente nao encontrado com este nome: " + dto.getClienteNome());
            }
            solicitacao.setCliente(clienteRepository.findByNome(dto.getClienteNome()));
        }

        if (dto.getGerenteNome() != null) {
            if (!gerenteRepository.existsByNome(dto.getGerenteNome())) {
                throw new NomeNaoEncontradoException(
                        "Gerente nao encontrado com este nome: " + dto.getGerenteNome());
            }
            solicitacao.setGerente(gerenteRepository.findByNome(dto.getGerenteNome()));
        }

        if (dto.getPedidoId() != null) {
            solicitacao.setPedido(pedidoRepository.findById(dto.getPedidoId())
                    .orElseThrow(() -> new PedidoNaoEncontradoException(
                            "Pedido nao encontrado com o ID: " + dto.getPedidoId())));
        }

        return solicitacaoMapper.toGetDTO(solicitacaoRepository.save(solicitacao));
    }

    @Transactional
    public void deleteSolicitacaoById(Long id) {
        if (!solicitacaoRepository.existsById(id)) {
            throw new SolicitacaoNaoEncontradaException("Solicitacao não encontrada com o ID: " + id);
        }

        solicitacaoRepository.delete(solicitacaoRepository.findById(id).get());
    }

}
