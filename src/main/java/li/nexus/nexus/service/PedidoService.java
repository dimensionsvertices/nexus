package li.nexus.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.PedidoGetDTO;
import li.nexus.nexus.dto.PedidoPostDTO;
import li.nexus.nexus.entity.Cliente;
import li.nexus.nexus.entity.Pagamento;
import li.nexus.nexus.entity.Pedido;
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
        Pedido pedido = pedidoMapper.toEntity(dto);
        Cliente cliente = clienteRepository.findByNome(dto.getClienteNome());
        Pagamento pagamento = pagamentoRepository.findById(dto.getPagamentoId()).get();
        pedido.setCliente(cliente);
        pedido.setPagamento(pagamento);
        return pedidoMapper.toGetDTO(pedidoRepository.save(pedido));
    }

    @Transactional
    public List<PedidoGetDTO> getPedidos() {
        return pedidoMapper.toGetDTOList(pedidoRepository.findAll());
    }

    @Transactional
    public PedidoGetDTO getPedidoById(Long id) {
        return pedidoMapper.toGetDTO(pedidoRepository.findById(id).get());
    }

    @Transactional
    public PedidoGetDTO patchPedido(Long id, PedidoPostDTO dto) {
        Pedido pedido = pedidoRepository.findById(id).get();
        pedidoMapper.updateEntity(pedido, dto);
        return pedidoMapper.toGetDTO(pedidoRepository.save(pedido));
    }

    @Transactional
    public void deletePedidoById(Long id) {
        pedidoRepository.delete(pedidoRepository.findById(id).get());
    }

}
