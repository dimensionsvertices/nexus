package li.nexus.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.PedidoGetDTO;
import li.nexus.nexus.dto.PedidoPostDTO;
import li.nexus.nexus.entity.Cliente;
import li.nexus.nexus.entity.Pagamento;
import li.nexus.nexus.entity.Pedido;
import li.nexus.nexus.exception.NenhumDadoEncontradoException;
import li.nexus.nexus.exception.NomeNaoEncontradoException;
import li.nexus.nexus.exception.PagamentoNaoEncontradoException;
import li.nexus.nexus.exception.PedidoNaoEncontradoException;
import li.nexus.nexus.mapper.PedidoMapper;
import li.nexus.nexus.repository.ClienteRepository;
import li.nexus.nexus.repository.PagamentoRepository;
import li.nexus.nexus.repository.PedidoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final PagamentoRepository pagamentoRepository;
    private final PedidoMapper pedidoMapper;

    @Transactional
    public PedidoGetDTO savePedido(PedidoPostDTO dto) {
        if (!clienteRepository.existsByNome(dto.getClienteNome())) {
            throw new NomeNaoEncontradoException("Cliente nao encontrado com este nome: " + dto.getClienteNome());
        }

        if (!pagamentoRepository.existsById(dto.getPagamentoId())) {
            throw new PagamentoNaoEncontradoException("Pagamento nao encontrado com o ID: " + dto.getPagamentoId());
        }

        Pedido pedido = pedidoMapper.toEntity(dto);
        Cliente cliente = clienteRepository.findByNome(dto.getClienteNome());
        Pagamento pagamento = pagamentoRepository.findById(dto.getPagamentoId()).get();
        pedido.setCliente(cliente);
        pedido.setPagamento(pagamento);
        return pedidoMapper.toGetDTO(pedidoRepository.save(pedido));
    }

    @Transactional
    public List<PedidoGetDTO> getPedidos() {
        if (pedidoRepository.findAll().isEmpty()) {
            throw new NenhumDadoEncontradoException("Nenhum dado encontrado ou cadastrado");
        }

        return pedidoMapper.toGetDTOList(pedidoRepository.findAll());
    }

    @Transactional
    public PedidoGetDTO getPedidoById(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new PedidoNaoEncontradoException("Pedido nao encontrado com o ID: " + id);
        }

        return pedidoMapper.toGetDTO(pedidoRepository.findById(id).get());
    }

    @Transactional
    public PedidoGetDTO patchPedido(Long id, PedidoPostDTO dto) {
        if (!pedidoRepository.existsById(id)) {
            throw new PedidoNaoEncontradoException("Pedido nao encontrado com o ID: " + id);
        }

        Pedido pedido = pedidoRepository.findById(id).get();
        pedidoMapper.updateEntity(pedido, dto);

        if (dto.getClienteNome() != null) {
            if (!clienteRepository.existsByNome(dto.getClienteNome())) {
                throw new NomeNaoEncontradoException(
                        "Cliente nao encontrado com este nome: " + dto.getClienteNome());
            }
            pedido.setCliente(clienteRepository.findByNome(dto.getClienteNome()));
        }

        if (dto.getPagamentoId() != null) {
            Pagamento pagamento = pagamentoRepository.findById(dto.getPagamentoId())
                    .orElseThrow(() -> new PagamentoNaoEncontradoException(
                            "Pagamento nao encontrado com o ID: " + dto.getPagamentoId()));
            pedido.setPagamento(pagamento);
        }

        return pedidoMapper.toGetDTO(pedidoRepository.save(pedido));
    }

    @Transactional
    public void deletePedidoById(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new PedidoNaoEncontradoException("Pedido nao encontrado com o ID: " + id);
        }

        pedidoRepository.delete(pedidoRepository.findById(id).get());
    }

}
